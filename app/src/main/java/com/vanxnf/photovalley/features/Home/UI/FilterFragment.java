package com.vanxnf.photovalley.features.Home.UI;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.features.Home.Adapter.HomeFilterAdapter;
import com.vanxnf.photovalley.base.BaseFragment;

import com.vanxnf.photovalley.features.Home.Gson.Filter;
import com.vanxnf.photovalley.features.Home.Util.FilterUtil;
import com.vanxnf.photovalley.features.Home.Util.HomeDataUtil;

import com.vanxnf.photovalley.features.Home.Util.PathUtils;
import com.vanxnf.photovalley.features.Preview.UI.PreviewFragment;
import com.vanxnf.photovalley.listener.OnItemClickListener;

import com.vanxnf.photovalley.utils.Luban.Luban;
import com.vanxnf.photovalley.utils.Luban.OnCompressListener;
import com.vanxnf.photovalley.utils.SharedPreferences.SharedPreferencesUtil;

import java.io.File;



import java.util.ArrayList;
import java.util.List;


/**
 * Created by VanXN on 2018/3/11.
 */

public class FilterFragment extends BaseFragment {

    private View view;
    private RecyclerView mRecycler;
    private List<String> items = new ArrayList<>();
    private HomeFilterAdapter mHFAdapter;
    private String json = null;
    private int filterId = -1;
    public static final int CHOOSE_PHOTO = 0;

    public static FilterFragment newInstance() {
        return new FilterFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home_filter, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        // Init Datas
        items.addAll(HomeDataUtil.getImageUri(60, 69));
        mRecycler = (RecyclerView) view.findViewById(R.id.recycler_view_filter);
        mHFAdapter = new HomeFilterAdapter(_mActivity);
        LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
        mRecycler.setLayoutManager(manager);
        mRecycler.setAdapter(mHFAdapter);
        mHFAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                if (view instanceof SimpleDraweeView) {
                    if (!getAccountStatus(_mActivity)) {
                        Toast.makeText(getContext(), getString(R.string.please_login_first), Toast.LENGTH_SHORT).show();
                    } else {
                        if (position == mHFAdapter.getItemCount()-2 || position == mHFAdapter.getItemCount()-1) {
                            Toast.makeText(getContext(), getString(R.string.members_only), Toast.LENGTH_SHORT).show();
                        }
                        checkPermissionAndOpenAlbum(position);
                    }
                }
            }
        });
        mRecycler.post(new Runnable() {
            @Override
            public void run() {
                mHFAdapter.setData(items);
            }
        });
    }

    private void checkPermissionAndOpenAlbum(int position) {
        filterId = position;
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            openAlbum();
        }
    }

    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openAlbum();
            } else {
                Toast.makeText(getContext(), "You denied the permission", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CHOOSE_PHOTO && resultCode == RESULT_OK) {
            Uri uri = data.getData();

            //裁剪图片
            Luban.with(_mActivity)
                .ignoreBy(100)
                .load(PathUtils.getPath(_mActivity, uri))
                .setTargetDir(getCompressedImagePath())
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {
                        //可播放loading动画
                    }

                    @Override
                    public void onSuccess(File file) {

                        //获取图片宽高(不加载)
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inJustDecodeBounds = true;
                        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), options);
                        int width = options.outWidth;
                        int height = options.outHeight;
                        String type = options.outMimeType;

                        //创建JSON数据
                        Gson filterRequest = new Gson();
                        Filter filter = new Filter();
                        filter.setFilterName(FilterUtil.getFilterNameByPosition(filterId));
                        filter.setUserName(SharedPreferencesUtil.getAccountName(getContext()));
                        filter.setImageWidth(String.valueOf(width));
                        filter.setImageHeight(String.valueOf(height));
                        filter.setImageBase64(FilterUtil.encode(file.getAbsolutePath(), type.substring(6, type.length())));

                        //删除压缩后的图片
                        json = filterRequest.toJson(filter);
                        file.delete();
                        ((HomeFragment) getParentFragment()).start(PreviewFragment.newInstance(json, true));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }).launch();
        }
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
