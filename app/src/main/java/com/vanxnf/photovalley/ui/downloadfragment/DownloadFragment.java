package com.vanxnf.photovalley.ui.downloadfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.base.BaseMainFragment;

/**
 * Created by VanXN on 2018/3/17.
 */

public class DownloadFragment extends BaseMainFragment {

    public static DownloadFragment newInstance() {
        return new DownloadFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_download, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        Toolbar mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mToolbar.setTitle(R.string.download);
        getActivity().openOptionsMenu();
        initToolbarNav(mToolbar);
    }
}
