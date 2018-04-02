package com.vanxnf.photovalley.features.Preview.UI;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.gson.Gson;
import com.shizhefei.view.largeimage.LargeImageView;
import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.base.BaseFragment;
import com.vanxnf.photovalley.features.Preview.Gson.Download;
import com.vanxnf.photovalley.features.Preview.Util.HttpUtil;
import com.vanxnf.photovalley.utils.SnackBar.SnackbarUtils;
import com.vanxnf.photovalley.widget.LoadingView.LoadingView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created by VanXN on 2018/3/26.
 */

public class PreviewFragment extends BaseFragment implements View.OnClickListener{

    private static final String DATA_FORM = "data_form";
    private static final String IS_LOAD_FROM_JSON = "is_load_from_json";
    private boolean isLoadFromJson;
    private LoadingView loadingView;
    private Call call;
    private View view;
    private String uri;
    private String json;

    public static PreviewFragment newInstance(String data, boolean isLoadFromJson) {
        Bundle args = new Bundle();
        args.putBoolean(IS_LOAD_FROM_JSON, isLoadFromJson);
        args.putString(DATA_FORM, data);
        PreviewFragment fragment = new PreviewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            isLoadFromJson = args.getBoolean(IS_LOAD_FROM_JSON);
            if (isLoadFromJson) {
                json = args.getString(DATA_FORM);
            } else {
                uri = args.getString(DATA_FORM);
            }
        }


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_preview, container, false);
        initView();
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.preview_save:
                // TODO: 2018/4/1 保存原图到文件夹 
                break;
            case R.id.preview_share:
                if (uri != null) {
                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
                    shareIntent.setType("image/*");
                    startActivity(Intent.createChooser(shareIntent, "分享到"));
                } else {
                    SnackbarUtils.Short(view, "图片未加载").warning().show();
                }
                break;
        }
    }

    private void initView() {
        loadingView = (LoadingView) view.findViewById(R.id.loading_view_preview);
        FloatingActionButton btnSave = view.findViewById(R.id.preview_save);
        FloatingActionButton btnShare = view.findViewById(R.id.preview_share);
        btnSave.setOnClickListener(this);
        btnShare.setOnClickListener(this);

        if (isLoadFromJson && json != null) {
            btnSave.setVisibility(View.VISIBLE);
            btnShare.setVisibility(View.VISIBLE);

            post(new Runnable() {
                @Override
                public void run() {
                    loadingView.setColor(Color.WHITE);
                    loadingView.startAnim();
                }
            });
            call = HttpUtil.sendOkHttpRequest("http://192.168.4.7:8080/", json, new okhttp3.Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            loadingView.stopAnim();
                            SnackbarUtils.Long(view, getString(R.string.load_failed)).warning().show();
                        }
                    });
                    pop();
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String responseData = response.body().string();
                    getJsonByGSON(responseData);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            loadingView.stopAnim();
                            loadPreviewImage(uri);
                        }
                    });
                }
            });
        } else {
            loadPreviewImage(uri);
        }

    }

    private void getJsonByGSON(String data) {
        Gson gson = new Gson();
        Download download = gson.fromJson(data, Download.class);
        uri = download.getDownloadUri();
    }

    private void loadPreviewImage(String uri) {
        final LargeImageView imageView = view.findViewById(R.id.preview_image);
        Glide.with(view)
                .load(uri)
                .transition(withCrossFade(800))
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        imageView.setImage(resource);
                    }
                });
    }

    @Override
    public boolean onBackPressedSupport() {
        call.cancel();
        return true;
    }
}
