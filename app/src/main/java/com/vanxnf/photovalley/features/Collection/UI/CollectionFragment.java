package com.vanxnf.photovalley.features.Collection.UI;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.features.Collection.Adapter.CollectionAdapter;
import com.vanxnf.photovalley.base.BaseMainFragment;
import com.vanxnf.photovalley.features.Collection.Entity.CollectionItem;
import com.vanxnf.photovalley.features.Collection.Util.ItemUtil;
import com.vanxnf.photovalley.features.Preview.UI.PreviewFragment;


import java.util.List;

/**
 * Created by VanXN on 2018/3/17.
 * Edited by VanXN on 2018/4/5
 */

public class CollectionFragment extends BaseMainFragment {

    private View view;
    private RecyclerView mRecycler;
    private CollectionAdapter mCAdapter;
    private List<CollectionItem> ItemData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_collection, container, false);
        initView();
        return view;
    }

    public static CollectionFragment newInstance() {
        return new CollectionFragment();
    }

    private void initView() {
        Toolbar mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mRecycler = (RecyclerView) view.findViewById(R.id.recycler_view_collection);
        mToolbar.setTitle(R.string.collection);
        initToolbarNav(mToolbar);
        LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
        mRecycler.setLayoutManager(manager);

        ItemData = ItemUtil.getCollectionItemData();
        mCAdapter = new CollectionAdapter(_mActivity, ItemData);
        mCAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                start(PreviewFragment.newInstance(ItemData.get(position).getImageUri()));
            }
        });
        mCAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        mRecycler.setAdapter(mCAdapter);
    }
}
