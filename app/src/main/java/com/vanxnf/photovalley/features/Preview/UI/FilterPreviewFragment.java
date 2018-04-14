package com.vanxnf.photovalley.features.Preview.UI;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.FileProvider;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.shizhefei.view.largeimage.LargeImageView;
import com.vanxnf.photovalley.MainActivity;
import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.base.BaseFragment;
import com.vanxnf.photovalley.features.Home.Gson.Filter;
import com.vanxnf.photovalley.features.Preview.Adapter.FilterPreviewAdapter;
import com.vanxnf.photovalley.features.Preview.Entity.FilterPreviewItem;
import com.vanxnf.photovalley.features.Preview.Gson.Download;
import com.vanxnf.photovalley.features.Preview.Util.DataUtil;
import com.vanxnf.photovalley.features.Preview.Util.FileUtil;
import com.vanxnf.photovalley.features.Preview.Util.HttpUtil;
import com.vanxnf.photovalley.utils.SharedPreferences.SharedPreferencesUtil;
import com.vanxnf.photovalley.utils.SnackBar.SnackbarUtils;
import com.vanxnf.photovalley.utils.Utility;
import com.vanxnf.photovalley.widget.LoadingView.LoadingView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created by VanXN on 2018/3/26.
 */

public class FilterPreviewFragment extends BaseFragment implements View.OnClickListener {

    private static final String DATA_FORM = "data_form";
    private RecyclerView mRecycler;
    private LoadingView loadingView;
    private LargeImageView imageView;
    private Call call;
    private View view;
    private String fileUri;
    private String filterUri;
    private Filter filter;
    private FilterPreviewAdapter adapter;
    private List<FilterPreviewItem> filterData;
    private DrawerLayout parentDrawerLayout;
    private boolean isFirstInitJson;
    private File currentImage;
    private String json = null;
    private int filterId = 0;
    private Bitmap bitmap;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            fileUri = args.getString(DATA_FORM);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_filter_preview, container, false);
        Utility.hideStatusBar(getActivity().getWindow());
        parentDrawerLayout = ((MainActivity) getActivity()).getDrawerLayout();
        parentDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        isFirstInitJson = true;
        initView();
        return view;
    }

    public static FilterPreviewFragment newInstance(String data) {
        Bundle args = new Bundle();
        args.putString(DATA_FORM, data);
        FilterPreviewFragment fragment = new FilterPreviewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void initView() {
        imageView = view.findViewById(R.id.preview_filter_image);
        FloatingActionButton btnSave = view.findViewById(R.id.preview_filter_save);
        FloatingActionButton btnShare = view.findViewById(R.id.preview_filter_share);
        loadingView = (LoadingView) view.findViewById(R.id.loading_view_preview_filter);
        mRecycler = (RecyclerView) view.findViewById(R.id.recycler_view_preview_filter);
        btnSave.setOnClickListener(this);
        btnShare.setOnClickListener(this);
        //recyclerView横向滚动
        LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecycler.setLayoutManager(manager);
        //获取数据
        filterData = DataUtil.getItemData();
        filterData.get(0).setFilterUri(fileUri);
        filterData.get(0).setBgUri(fileUri);
        String path;
        if (Build.VERSION.SDK_INT >= 24) {
            path = DataUtil.changeUriToPathForFileProvider(Uri.parse(fileUri));
        } else {
            path = FileUtil.getFilePathByUri(_mActivity, Uri.parse(fileUri));
        }
        if (path != null) {
            currentImage = new File(path);
        }
        adapter = new FilterPreviewAdapter(_mActivity, filterData);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String uri = filterData.get(position).getFilterUri();
                if (call == null) {
                    if (uri != null) {
                        loadPreviewImage(uri);
                    } else {
                        if (position != 0) {
                            filterId = position;
                            json = initJson();
                            if (json != null) {
                                loadFilterImage();
                            }
                        }
                    }
                }
            }
        });
        mRecycler.setAdapter(adapter);
        loadPreviewImage(fileUri);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.preview_filter_save:
                if (bitmap != null) {
                    saveIMG(bitmap);
                    SnackbarUtils.Short(view,getString(R.string.save_success)).info().show();
                    bitmap = null;
                } else {
                    SnackbarUtils.Short(view,getString(R.string.already_saved)).info().show();
                }
                break;
            case R.id.preview_filter_share:
                if (fileUri != null) {
                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_STREAM, fileUri);
                    shareIntent.setType("image/*");
                    startActivity(Intent.createChooser(shareIntent, getString(R.string.share_to)));
                } else {
                    SnackbarUtils.Short(view, getString(R.string.pic_not_load)).warning().show();
                }
                break;
        }
    }

    /**获取服务器所需要的json数据*/
    private String initJson() {
        Gson filterRequest = new Gson();
        if (currentImage == null) {
            String path;
            if (Build.VERSION.SDK_INT >= 24) {
                path = DataUtil.changeUriToPathForFileProvider(Uri.parse(fileUri));
            } else {
                path = FileUtil.getFilePathByUri(_mActivity, Uri.parse(fileUri));
            }
            if (path != null) {
                currentImage = new File(path);
            }
        }
        if (isFirstInitJson) {
            //获取图片宽高(不加载)
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            Bitmap bitmap = BitmapFactory.decodeFile(currentImage.getAbsolutePath(), options);
            int width = options.outWidth;
            int height = options.outHeight;
            String type = options.outMimeType;
            //创建JSON数据
            filter = new Filter();
            filter.setFilterName(DataUtil.getFilterNameByPosition(filterId));
            filter.setUserName(SharedPreferencesUtil.getAccountName(getContext()));
            filter.setImageWidth(String.valueOf(width));
            filter.setImageHeight(String.valueOf(height));
            filter.setImageBase64(DataUtil.encode(currentImage.getAbsolutePath(), type.substring(6, type.length())));
            isFirstInitJson = false;
        } else {
            filter.setFilterName(DataUtil.getFilterNameByPosition(filterId));
        }
        json = filterRequest.toJson(filter);
        return json;
    }

    /**加载滤镜*/
    private void loadFilterImage() {

        post(new Runnable() {
            @Override
            public void run() {
                loadingView.setColor(Color.WHITE);
                loadingView.startAnim();
            }
        });

        //Ali yun "http://120.79.162.134:80/api" 本地 "http://192.168.4.73:80/api"
        call = HttpUtil.sendOkHttpRequest("http://192.168.43.5:80/api", json, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callFailed();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                //获取服务器返回的Json数据
                getJsonByGSON(responseData);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        loadingView.stopAnim();
                        loadPreviewImage(filterUri);
                    }
                });
            }
        });
    }

    private void callFailed (){
        call = null;
        loadingView.stopAnim();
        SnackbarUtils.Short(view, getString(R.string.load_failed)).danger().show();
    }

    private void getJsonByGSON(String data) {
        Gson gson = new Gson();
        Download download = gson.fromJson(data, Download.class);
        filterUri = download.getDownloadUri();
        filterData.get(filterId).setFilterUri(filterUri);
        post(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
        call = null;
    }

    private void loadPreviewImage(String uri) {
        Glide.with(view)
                .asBitmap()
                .load(uri)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        imageView.setImage(resource);
                        bitmap = resource;
                    }
                });
    }

    /**
     * 保存图片到本地
     * @param bitmap
     */
    private void saveIMG(Bitmap bitmap) {
        //可访问的图片文件夹
        File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsoluteFile();
        //在图片文件夹下新建自己的文件夹，保存图片
        String dirName = "PhotoValley";
        File appDir = new File(file ,dirName);
        if (!appDir.exists()) {
            appDir.mkdirs();
        }
        //命名图片并保存
        String picName = System.currentTimeMillis() + ".jpg";
        File currentFile = new File(appDir, picName);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(currentFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG,100 , fos);//质量为100表示设置压缩率为0
            fos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 通知图库更新
        _mActivity.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                Uri.fromFile(new File(currentFile.getPath()))));
    }

    @Override
    public boolean onBackPressedSupport() {
        if (call != null) {
            call.cancel();
            call = null;
        }  else {
            post(new Runnable() {
                @Override
                public void run() {
                    //清除压缩后图片文件
                    imageView.setImage(R.color.black);
                    if (currentImage.exists()) {
                        currentImage.delete();
                    }
                }
            });
            Utility.showStatusBar(getActivity().getWindow());
            parentDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            pop();
        }
        return true;
    }
}
