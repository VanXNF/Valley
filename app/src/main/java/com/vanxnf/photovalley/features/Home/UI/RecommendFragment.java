package com.vanxnf.photovalley.features.Home.UI;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.features.Home.Adapter.HomeRecommendAdapter;
import com.vanxnf.photovalley.base.BaseFragment;
import com.vanxnf.photovalley.listener.OnItemClickListener;
import com.vanxnf.photovalley.features.Preview.UI.PreviewFragment;
import com.vanxnf.photovalley.utils.DataUtil;


import java.util.ArrayList;
import java.util.List;



/**
 * Created by VanXN on 2018/3/11.
 */

public class RecommendFragment extends BaseFragment {

    private View view;
    private List<String> items = new ArrayList<>();
    private RecyclerView mRecycler;
    private HomeRecommendAdapter mHRAdapter;

    public static RecommendFragment newInstance() {
        return new RecommendFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home_recommend, container, false);
        initView(view);
        return view;
    }

    //初始化
    private void initView(View view) {
        items.addAll(DataUtil.getImageUri(26, 35));
        mHRAdapter = new HomeRecommendAdapter(_mActivity);
        mRecycler = (RecyclerView) view.findViewById(R.id.recycler_view_recommend);
        LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
        mRecycler.setLayoutManager(manager);
        mRecycler.setAdapter(mHRAdapter);
        mHRAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                if (view instanceof SimpleDraweeView) {
                    ((HomeFragment) getParentFragment()).start(PreviewFragment.newInstance(items.get(position), false));
                }
            }
        });

        mRecycler.post(new Runnable() {
            @Override
            public void run() {
                // Init Datas
                mHRAdapter.setData(items);
            }
        });
    }
}