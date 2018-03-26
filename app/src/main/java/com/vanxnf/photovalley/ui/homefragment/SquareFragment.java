package com.vanxnf.photovalley.ui.homefragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.adapter.Home.HomeSquareAdapter;
import com.vanxnf.photovalley.base.BaseFragment;
import com.vanxnf.photovalley.listener.OnItemClickListener;
import com.vanxnf.photovalley.ui.previewfragment.PreviewFragment;
import com.vanxnf.photovalley.widget.CircleImageView.CircleImageView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by VanXN on 2018/3/11.
 */

public class SquareFragment extends BaseFragment {

    private RecyclerView mRecycler;
    private HomeSquareAdapter mHSAdapter;
    private List<String> items = new ArrayList<>();
    private View view;


    public static SquareFragment newInstance() {
        return new SquareFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home_square, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mHSAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                if (view instanceof CircleImageView) {
                    // TODO: 2018/3/15 展示头像详情
                    Toast.makeText(getContext(), "暂无法查看头像详情", Toast.LENGTH_SHORT).show();
                } else if (view instanceof ImageView) {
                    ((HomeFragment) getParentFragment()).start(PreviewFragment.newInstance(items.get(position)));
                } else if (view instanceof ImageView) {
                    // TODO: 2018/3/17 收藏图片
                    Toast.makeText(getContext(), "你喜欢了第"+(position+1)+"张图片", Toast.LENGTH_SHORT).show();
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
                for (int i = 100; i <= 116; i++) {
                    if (i == 105) continue;
                    item = uri + i;
                    items.add(item);
                }
                mHSAdapter.setData(items);
            }
        });
    }
    //轻量级初始化
    private void initView(View view) {
        mHSAdapter = new HomeSquareAdapter(_mActivity);
        mRecycler = (RecyclerView) view.findViewById(R.id.recycler_view_square);
        LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
        mRecycler.setLayoutManager(manager);
        mRecycler.setAdapter(mHSAdapter);
    }

}
