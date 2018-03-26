package com.vanxnf.photovalley.ui.previewfragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.base.BaseFragment;

/**
 * Created by VanXN on 2018/3/26.
 */

public class PreviewFragment extends BaseFragment {

    private static final String URI_FORM = "uri_form";
    private String uri;

    public static PreviewFragment newInstance(String uri) {
        Bundle uris = new Bundle();
        uris.putString(URI_FORM, uri);
        PreviewFragment fragment = new PreviewFragment();
        fragment.setArguments(uris);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            uri = args.getString(URI_FORM);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_preview, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        FloatingActionButton btnSave = view.findViewById(R.id.preview_save);
        FloatingActionButton btnShare = view.findViewById(R.id.preview_share);
        FloatingActionButton btnDelete = view.findViewById(R.id.preview_delete);
        // TODO: 2018/3/26 增加判断机制 当从服务器返回时显示button
        loadPreviewImage(view);
    }

    private void loadPreviewImage(View view) {
        ImageView imageView = view.findViewById(R.id.preview_image);
        Glide.with(view).load(uri).into(imageView);

    }
}
