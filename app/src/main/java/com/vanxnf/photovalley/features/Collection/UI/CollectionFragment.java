package com.vanxnf.photovalley.features.Collection.UI;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.features.Collection.Adapter.CollectionAdapter;
import com.vanxnf.photovalley.base.BaseMainFragment;
import com.vanxnf.photovalley.listener.OnItemClickListener;
import com.vanxnf.photovalley.features.Preview.UI.PreviewFragment;
import com.vanxnf.photovalley.features.Collection.Util.CollectionDataUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by VanXN on 2018/3/17.
 */

public class CollectionFragment extends BaseMainFragment {

    private View view;
    private RecyclerView mRecycler;
    private List<String> items = new ArrayList<>();
    private CollectionAdapter mCAdapter;

    public static CollectionFragment newInstance() {
        return new CollectionFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_collection, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        Toolbar mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mRecycler = (RecyclerView) view.findViewById(R.id.recycler_view_collection);
        mToolbar.setTitle(R.string.collection);
        getActivity().openOptionsMenu();
        initToolbarNav(mToolbar);
        mCAdapter = new CollectionAdapter(_mActivity);
        LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
        mRecycler.setLayoutManager(manager);
        mRecycler.setAdapter(mCAdapter);

        mCAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                Toast.makeText(getContext(), CollectionDataUtil.getRandomPlace(), Toast.LENGTH_SHORT).show();
                start(PreviewFragment.newInstance(items.get(position)));
            }
        });

        mRecycler.post(new Runnable() {
            @Override
            public void run() {
                Collections.addAll(items, CollectionDataUtil.urls);
                mCAdapter.setData(items);
            }
        });
    }
}
