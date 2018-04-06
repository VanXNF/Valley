package com.vanxnf.photovalley.features.Home.UI;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.base.BaseFragment;

import com.vanxnf.photovalley.features.Home.Adapter.HomeFilterAdapter;
import com.vanxnf.photovalley.features.Home.Entity.FilterItem;
import com.vanxnf.photovalley.features.Home.Util.FileUtil;
import com.vanxnf.photovalley.features.Home.Util.ItemUtil;
import com.vanxnf.photovalley.features.Home.Util.PathUtils;
import com.vanxnf.photovalley.features.Preview.UI.FilterPreviewFragment;
import com.vanxnf.photovalley.utils.Luban.Luban;
import com.vanxnf.photovalley.utils.Luban.OnCompressListener;

import java.io.File;
import java.util.List;


/**
 * Created by VanXN on 2018/3/11.
 */

public class FilterFragment extends BaseFragment {

    private View view;
    private RecyclerView mRecycler;
    public static final int TAKE_PHOTO = 0x10;
    public static final int CHOOSE_PHOTO = 0x11;
    private File mCameraFile = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home_filter, container, false);
        initView();
        return view;
    }

    public static FilterFragment newInstance() {
        return new FilterFragment();
    }

    private void initView() {
        mRecycler = (RecyclerView) view.findViewById(R.id.recycler_view_filter);
        final List<FilterItem> data = ItemUtil.getFilterItemData();
        HomeFilterAdapter adapter = new HomeFilterAdapter(_mActivity, data);
        final GridLayoutManager manager = new GridLayoutManager(_mActivity, 2);
        mRecycler.setLayoutManager(manager);
        //设置Item占据的空间
        adapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                return data.get(position).getSpanSize();
            }
        });
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position == 0) {
                    checkPermissionAndOpenCamera();
                } else if (position == 1) {
                    checkPermissionAndOpenAlbum();
                }
            }
        });
        mRecycler.setAdapter(adapter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera();
            } else {
                Toast.makeText(getContext(), "You denied the permission", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openAlbum();
            } else {
                Toast.makeText(getContext(), "You denied the permission", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        String path = null;
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    notifyMediaUpdate(mCameraFile);
                    path = mCameraFile.getAbsolutePath();
                }
                break;
            case CHOOSE_PHOTO:
                //从相册选择图片
                if (resultCode == RESULT_OK && data != null) {
                    path = PathUtils.getPath(_mActivity, data.getData());
                }
                break;
        }
        if (path != null) {
            Luban.with(_mActivity)
                    .ignoreBy(100)
                    .load(path)
                    .setTargetDir(getCompressedImagePath())
                    .setCompressListener(new OnCompressListener() {
                        @Override
                        public void onStart() {
                            //可播放loading动画
                        }

                        @Override
                        public void onSuccess(File file) {
                            Uri uri = FileUtil.getFileUri(_mActivity, "com.vanxnf.photovalley.fileProvider", file);
                            ((HomeFragment) getParentFragment()).start(FilterPreviewFragment.newInstance(uri.toString()));
                        }
                        @Override
                        public void onError(Throwable e) {

                        }
                    }).launch();
        }

    }

    private void checkPermissionAndOpenCamera() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 0);
        } else {
            openCamera();
        }
    }

    private void checkPermissionAndOpenAlbum() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            openAlbum();
        }
    }

    private void openCamera() {
        mCameraFile = FileUtil.createImageFile();
        Uri imageUri = FileUtil.getFileUri(_mActivity,
                "com.vanxnf.photovalley.fileProvider", mCameraFile);
        // 启动相机程序
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, TAKE_PHOTO);
    }

    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO);
    }

    private void notifyMediaUpdate(File file) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri contentUri = Uri.fromFile(file);
        mediaScanIntent.setData(contentUri);
        getActivity().sendBroadcast(mediaScanIntent);
    }

    private String getCompressedImagePath() {
        String path = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES).getAbsolutePath() + "/PhotoValley/";
        File file = new File(path);
        if (file.mkdirs()) {
            return path;
        }
        return path;
    }

}
