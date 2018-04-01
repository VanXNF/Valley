package com.vanxnf.photovalley.features.Preview.UI;

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

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created by VanXN on 2018/3/26.
 */

public class PreviewFragment extends BaseFragment {

    private static final String DATA_FORM = "data_form";
    private static final String IS_LOAD_FROM_JSON = "is_load_from_json";
    private boolean isLoadFromJson;
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
        View view = inflater.inflate(R.layout.fragment_preview, container, false);
        initView(view);
        return view;
    }

    private void initView(final View view) {
        FloatingActionButton btnSave = view.findViewById(R.id.preview_save);
        FloatingActionButton btnShare = view.findViewById(R.id.preview_share);
        FloatingActionButton btnDelete = view.findViewById(R.id.preview_delete);

        if (isLoadFromJson && json != null) {
            btnSave.setVisibility(View.VISIBLE);
            btnShare.setVisibility(View.VISIBLE);
            btnDelete.setVisibility(View.VISIBLE);
            post(new Runnable() {
                @Override
                public void run() {
                    HttpUtil.sendOkHttpRequest("http://192.168.4.7:8080/", json, new okhttp3.Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String responseData = new String(response.body().string());
                            Gson gson = new Gson();
                            Download download = gson.fromJson(responseData, Download.class);
                            loadPreviewImage(view, download.getDownloadUri());
                        }
                    });
                }
            });
        } else {
            //直接加载图片
            loadPreviewImage(view, uri);
        }
    }

    private void loadPreviewImage(View view, String uri) {
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
}
