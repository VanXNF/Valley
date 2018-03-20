package com.vanxnf.photovalley.ui.homefragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.adapter.Home.HomeRecommendAdapter;
import com.vanxnf.photovalley.base.BaseFragment;
import com.vanxnf.photovalley.listener.OnItemClickListener;


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
        mHRAdapter = new HomeRecommendAdapter(_mActivity);
        mRecycler = (RecyclerView) view.findViewById(R.id.recycler_view_recommend);
        LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
        mRecycler.setLayoutManager(manager);
        mRecycler.setAdapter(mHRAdapter);
        mHRAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                if (getParentFragment() instanceof HomeFragment) {
                    // TODO: 2018/3/14 展示图片详情
                    Toast.makeText(getContext(), "暂无法查看图片详情", Toast.LENGTH_SHORT).show();
//                    ((HomeFragment) getParentFragment()).start(CycleFragment.newInstance(1));
                }
            }
        });

        mRecycler.post(new Runnable() {
            @Override
            public void run() {
                // Init Datas
                // TODO: 2018/3/14 调整获取图片数据方式
                String uri = new String("https://picsum.photos/800/600/?image=");
                String item;
                for (int i = 26; i <= 35; i++) {
                    item = uri + i;
                    items.add(item);
                }
                mHRAdapter.setData(items);
            }
        });
    }
}