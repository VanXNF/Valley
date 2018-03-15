package com.vanxnf.photovalley.ui.homefragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.adapter.HomeSquareAdapter;
import com.vanxnf.photovalley.base.BaseFragment;
import com.vanxnf.photovalley.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by VanXN on 2018/3/11.
 */

public class SquareFragment extends BaseFragment {

    private static final String ARG_FROM = "arg_from";

    private int mFrom;
    private RecyclerView mRecycler;
    private HomeSquareAdapter mHSAdapter;

    public static SquareFragment newInstance(int from) {
        Bundle args = new Bundle();
        args.putInt(ARG_FROM, from);

        SquareFragment fragment = new SquareFragment();
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
        View view = inflater.inflate(R.layout.fragment_home_square, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        // TODO: 2018/3/14 初始化界面
        mRecycler = (RecyclerView) view.findViewById(R.id.recycler_view_square);
        mHSAdapter = new HomeSquareAdapter(_mActivity);
        LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
        mRecycler.setLayoutManager(manager);
        mRecycler.setAdapter(mHSAdapter);

        mHSAdapter.setOnItemClickListener(new OnItemClickListener() {
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
                List<String> items = new ArrayList<>();
                // TODO: 2018/3/14 调整获取图片数据方式
                switch (mFrom) {
                    case 0:
                        String uri = new String("https://picsum.photos/800/600/?image=");
                        String item;
                        for (int i = 75; i <= 100; i++) {
                            item = uri + i;
                            items.add(item);
                        }
                        break;
                    default:
                }
                mHSAdapter.setData(items);
            }
        });
    }

}
