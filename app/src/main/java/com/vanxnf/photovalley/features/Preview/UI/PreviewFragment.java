package com.vanxnf.photovalley.features.Preview.UI;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.shizhefei.view.largeimage.LargeImageView;
import com.vanxnf.photovalley.MainActivity;
import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.base.BaseFragment;
import com.vanxnf.photovalley.utils.Utility;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created by VanXN on 2018/3/26.
 * Edited by VanXN on 2018/4/6.
 * Edited by VanXN on 2018/4/7.
 */

public class PreviewFragment extends BaseFragment {

    private static final String DATA_FORM = "data_form";
    private LargeImageView imageView;
    private DrawerLayout parentDrawerLayout;
    private View view;
    private String uri;

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
        parentDrawerLayout = ((MainActivity) getActivity()).getDrawerLayout();
        parentDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        initView();
        return view;
    }

    public static PreviewFragment newInstance(String data) {
        Bundle args = new Bundle();
        args.putString(DATA_FORM, data);
        PreviewFragment fragment = new PreviewFragment();
        fragment.setArguments(args);
        return fragment;
    }


    private void initView() {
        imageView = view.findViewById(R.id.preview_image);
        loadPreviewImage(uri);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressedSupport();
            }
        });
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
        parentDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        pop();
        return true;
    }
}
