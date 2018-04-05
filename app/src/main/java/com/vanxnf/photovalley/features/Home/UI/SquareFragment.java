package com.vanxnf.photovalley.features.Home.UI;

import android.content.Intent;
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
import com.vanxnf.photovalley.features.Home.Adapter.HomeSquareAdapter;
import com.vanxnf.photovalley.base.BaseFragment;
import com.vanxnf.photovalley.listener.OnItemClickListener;
import com.vanxnf.photovalley.features.Preview.UI.PreviewFragment;
import com.vanxnf.photovalley.utils.DataUtil;

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
        initView();
        return view;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        //init data
        items.addAll(DataUtil.getImageUri(100, 116));
        mHSAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                if (view.getId() == R.id.display_image_square) {
                    ((HomeFragment) getParentFragment()).start(PreviewFragment.newInstance(items.get(position)));
                } else if (view.getId() == R.id.action_like_square) {
                    // TODO: 2018/3/17 收藏图片
                    ((ImageView) view).setImageResource(R.drawable.square_like_red);
                } else if (view.getId() == R.id.action_comment_square) {

                } else if (view.getId() == R.id.action_share_square) {
                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_STREAM, items.get(position));
                    shareIntent.setType("image/*");
                    startActivity(Intent.createChooser(shareIntent, "分享到"));
                }
            }
        });
        mRecycler.post(new Runnable() {
            @Override
            public void run() {
                mHSAdapter.setData(items);
            }
        });
    }
    //轻量级初始化
    private void initView() {
        mHSAdapter = new HomeSquareAdapter(_mActivity);
        mRecycler = (RecyclerView) view.findViewById(R.id.recycler_view_square);
        LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
        mRecycler.setLayoutManager(manager);
        mRecycler.setAdapter(mHSAdapter);
    }
}
