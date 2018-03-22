package com.vanxnf.photovalley.ui.homefragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.adapter.Home.HomeFilterAdapter;
import com.vanxnf.photovalley.base.BaseFragment;
import com.vanxnf.photovalley.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by VanXN on 2018/3/11.
 */

public class FilterFragment extends BaseFragment {

    private View view;
    private RecyclerView mRecycler;
    private List<String> items = new ArrayList<>();
    private HomeFilterAdapter mHFAdapter;

    public static FilterFragment newInstance() {
        return new FilterFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home_filter, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mRecycler = (RecyclerView) view.findViewById(R.id.recycler_view_filter);
        mHFAdapter = new HomeFilterAdapter(_mActivity);
        LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
        mRecycler.setLayoutManager(manager);
        mRecycler.setAdapter(mHFAdapter);
        mHFAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                if (view instanceof SimpleDraweeView) {
                    if (!getAccountStatus(_mActivity)) {
                        Toast.makeText(getContext(), "请登录后使用", Toast.LENGTH_SHORT).show();
                    } else {
                        if (position == mHFAdapter.getItemCount()-2 || position == mHFAdapter.getItemCount()-1) {
                            Toast.makeText(getContext(), "本滤镜由会员专享", Toast.LENGTH_SHORT).show();
                        }
                    }
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
                for (int i = 60; i < 70; i++) {
                    item = uri + i;
                    items.add(item);
                }
                mHFAdapter.setData(items);
            }
        });
    }

}
