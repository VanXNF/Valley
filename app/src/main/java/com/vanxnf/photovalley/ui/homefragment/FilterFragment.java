package com.vanxnf.photovalley.ui.homefragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.base.BaseFragment;


/**
 * Created by VanXN on 2018/3/11.
 */

public class FilterFragment extends BaseFragment {

    private static final String ARG_FROM = "arg_from";

    private int mFrom;

    public static FilterFragment newInstance(int from) {
        Bundle args = new Bundle();
        args.putInt(ARG_FROM, from);

        FilterFragment fragment = new FilterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            mFrom = args.getInt(ARG_FROM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_filter, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        // TODO: 2018/3/14 初始化界面
    }
}
