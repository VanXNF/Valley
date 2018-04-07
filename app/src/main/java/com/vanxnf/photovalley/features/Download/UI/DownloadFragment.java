package com.vanxnf.photovalley.features.Download.UI;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.base.BaseMainFragment;
import com.vanxnf.photovalley.features.Download.Adapter.DownloadAdapter;
import com.vanxnf.photovalley.features.Download.Entity.DownloadItem;
import com.vanxnf.photovalley.features.Preview.UI.PreviewFragment;
import com.vanxnf.photovalley.utils.SnackBar.SnackbarUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by VanXN on 2018/3/17.
 * Edited by VanXN on 2018/4/7.
 */

public class DownloadFragment extends BaseMainFragment {

    private static final long WAIT_TIME = 5000L;
    private long TOUCH_TIME = 0;
    private View view;
    private List<DownloadItem> downloadItems;
    private DownloadAdapter mDAdapter;
    private RecyclerView mRecycler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_download, container, false);
        initView();
        return view;
    }

    public static DownloadFragment newInstance() {
        return new DownloadFragment();
    }

    private void initView() {
        Toolbar mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mToolbar.setTitle(R.string.download);
        mToolbar.inflateMenu(R.menu.download_toolbar_menu);
        mToolbar.getMenu().findItem(R.id.action_refresh_download)
                .getIcon().setTint(getThemeTag() == 1 ? Color.WHITE : Color.BLACK);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_refresh_download) {
                    if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
                        if (mDAdapter != null) {
                            post(new Runnable() {
                                @Override
                                public void run() {
                                    downloadItems = getImagePathFromDir();
                                    mDAdapter.setNewData(downloadItems);
                                    mDAdapter.notifyDataSetChanged();
                                    SnackbarUtils.Short(view, getString(R.string.refresh_success))
                                            .info().show();
                                }
                            });
                        }
                    } else {
                        TOUCH_TIME = System.currentTimeMillis();
                    }
                }
                return true;
            }
        });
        initToolbarNav(mToolbar);

        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            downloadItems = getImagePathFromDir();
        }
        mDAdapter = new DownloadAdapter(downloadItems);
        mRecycler = view.findViewById(R.id.recycler_view_download);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);
//        GridLayoutManager manager = new GridLayoutManager(_mActivity, 2);
        mRecycler.setLayoutManager(manager);
        mRecycler.getItemAnimator().setChangeDuration(0);
        mDAdapter.setDuration(800);
        mDAdapter.openLoadAnimation();
        mDAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                DownloadItem item = downloadItems.get(position);
                start(PreviewFragment.newInstance(item.getUri()));
            }
        });
        mRecycler.setAdapter(mDAdapter);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                downloadItems = getImagePathFromDir();
            } else {
                SnackbarUtils.Short(view, getString(R.string.please_grant_permission)).info().show();
            }
        }
    }

    /**
     * 从sd卡获取图片资源
     * @return
     */
    private List<DownloadItem> getImagePathFromDir() {
        // 图片列表
        List<DownloadItem> itemList = new ArrayList<>();
        // 得到sd卡内image文件夹的路径   File.separator(/)
        String filePath = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES).getAbsolutePath() + "/PhotoValley/";
        // 得到该路径文件夹下所有的文件
        File fileAll = new File(filePath);
        File[] files = fileAll.listFiles();
        // 将所有的文件存入ArrayList中,并过滤所有图片格式的文件
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            if (checkIsImageFile(file.getPath())) {
                itemList.add(new DownloadItem(file.getPath()));
            }
        }
        // 返回得到的图片列表
        return itemList;
    }

    /**
     * 检查扩展名，得到图片格式的文件
     * @param fName  文件名
     * @return
     */
    @SuppressLint("DefaultLocale")
    private boolean checkIsImageFile(String fName) {
        boolean isImageFile = false;
        // 获取扩展名
        String FileEnd = fName.substring(fName.lastIndexOf(".") + 1,
                fName.length()).toLowerCase();
//         || FileEnd.equals("png") || FileEnd.equals("gif")
//                || FileEnd.equals("jpeg")|| FileEnd.equals("bmp")
        //保存的文件仅有jpg格式忽略其余格式
        if (FileEnd.equals("jpg")) {
            isImageFile = true;
        } else {
            isImageFile = false;
        }
        return isImageFile;
    }
}
