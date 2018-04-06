package com.vanxnf.photovalley.features.About.UI;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.features.About.Adapter.AboutListAdapter;
import com.vanxnf.photovalley.base.BaseMainFragment;
import com.vanxnf.photovalley.features.About.Entity.AboutCardItem;
import com.vanxnf.photovalley.features.About.Util.ItemUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by VanXN on 2018/3/27.
 * Edited by VanXN on 2018/4/6.
 */

public class AboutFragment extends BaseMainFragment {

    private View view;
    private RecyclerView mListRecycler;
    private AboutListAdapter mALAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_about, container, false);
        initView();
        return view;
    }

    public static AboutFragment newInstance() {
        return new AboutFragment();
    }

    private void initView() {
        Toolbar mToolbar = view.findViewById(R.id.toolbar);
        mListRecycler = view.findViewById(R.id.about_recycler_view);
        //toolbar
        mToolbar.setTitle(R.string.about);
        initToolbarNav(mToolbar);

        //List view item
        List<AboutCardItem> itemData = ItemUtil.getAboutCardItem();
        LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
        mALAdapter = new AboutListAdapter(_mActivity, itemData);
        mListRecycler.setLayoutManager(manager);
        mListRecycler.setAdapter(mALAdapter);
    }
}
