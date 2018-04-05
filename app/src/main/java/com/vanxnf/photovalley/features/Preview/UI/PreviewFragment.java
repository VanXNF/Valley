package com.vanxnf.photovalley.features.Preview.UI;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.shizhefei.view.largeimage.LargeImageView;
import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.base.BaseFragment;
import com.vanxnf.photovalley.utils.Utility;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created by VanXN on 2018/3/26.
 */

public class PreviewFragment extends BaseFragment {

    private static final String DATA_FORM = "data_form";
    private LargeImageView imageView;
    private View view;
    private String uri;

    public static PreviewFragment newInstance(String data) {
        Bundle args = new Bundle();
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
            uri = args.getString(DATA_FORM);
        }


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_preview, container, false);
        Utility.hideStatusBar(getActivity().getWindow());
        initView();
        return view;
    }



    private void initView() {
        imageView = view.findViewById(R.id.preview_image);
        loadPreviewImage(uri);
    }

    private void loadPreviewImage(String uri) {
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
        Utility.showStatusBar(getActivity().getWindow());
        pop();
        return true;
    }
}
