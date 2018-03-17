package com.vanxnf.photovalley.ui.homefragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.adapter.HomeRecommendAdapter;
import com.vanxnf.photovalley.base.BaseFragment;
import com.vanxnf.photovalley.listener.OnItemClickListener;


import java.util.ArrayList;
import java.util.List;



/**
 * Created by VanXN on 2018/3/11.
 */

public class RecommendFragment extends BaseFragment {

    private static final String ARG_FROM = "arg_from";

    private int mFrom;
    private View view;
    private List<String> items = new ArrayList<>();
    private RecyclerView mRecycler;
    private HomeRecommendAdapter mHRAdapter;

    public static RecommendFragment newInstance(int from) {
        Bundle args = new Bundle();
        args.putInt(ARG_FROM, from);

        RecommendFragment fragment = new RecommendFragment();
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
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        initLazyView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home_recommend, container, false);
        initView(view);
        return view;
    }

    //简单初始化
    private void initView(View view) {
        mHRAdapter = new HomeRecommendAdapter(_mActivity);
        mRecycler = (RecyclerView) view.findViewById(R.id.recycler_view_recommend);
        LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
        mRecycler.setLayoutManager(manager);
        mRecycler.setAdapter(mHRAdapter);
    }

    private void initLazyView() {
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
                switch (mFrom) {
                    case 0:
                        String uri = new String("https://picsum.photos/800/600/?image=");
                        String item;
                        for (int i = 26; i <= 56; i++) {
                            item = uri + i;
                            items.add(item);
                        }
                        break;
                    default:
                }
                mHRAdapter.setData(items);
            }
        });
    }

}