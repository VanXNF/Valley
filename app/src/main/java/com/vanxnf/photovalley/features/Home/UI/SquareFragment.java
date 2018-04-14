package com.vanxnf.photovalley.features.Home.UI;

import android.content.Intent;

import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import com.chad.library.adapter.base.BaseQuickAdapter;


import com.vanxnf.photovalley.R;

import com.vanxnf.photovalley.features.Home.Adapter.HomeSquareAdapter;
import com.vanxnf.photovalley.base.BaseFragment;
import com.vanxnf.photovalley.features.Home.Entity.SquareItem;
import com.vanxnf.photovalley.features.Home.Util.ItemUtil;

import com.vanxnf.photovalley.features.Preview.UI.PreviewFragment;
import com.vanxnf.photovalley.utils.SnackBar.SnackbarUtils;


import java.util.ArrayList;
import java.util.List;


/**
 * Created by VanXN on 2018/3/11.
 * Edited by VanXN on 2018/4/6.
 */

public class SquareFragment extends BaseFragment {

    public static final int REQUEST_CODE = 0x11;
    private RecyclerView mRecycler;
    private HomeSquareAdapter mHSAdapter;
    private List<SquareItem> itemData = new ArrayList<>();
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home_square, container, false);
        initView();
        return view;
    }

    public static SquareFragment newInstance() {
        return new SquareFragment();
    }

    //轻量级初始化
    private void initView() {
        FloatingActionButton btnCreate = (FloatingActionButton) view.findViewById(R.id.action_create_square);
        mRecycler = (RecyclerView) view.findViewById(R.id.recycler_view_square);
        LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
        mRecycler.setLayoutManager(manager);
        mRecycler.getItemAnimator().setChangeDuration(0);
        itemData = ItemUtil.getSquareItemData();
        mHSAdapter = new HomeSquareAdapter(_mActivity, itemData);
        mHSAdapter.openLoadAnimation();
        mHSAdapter.setDuration(800);
        mHSAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
                Integer id = view.getId();
                SquareItem item = itemData.get(position);
                boolean isNeedUpdate = false;
                if (id == R.id.display_image_square) {
                    ((HomeFragment) getParentFragment()).start(PreviewFragment.newInstance(item.getPicUri()));
                } else if (id == R.id.action_like_square) {
                    if (item.isLiked()) {
                        item.setLiked(false);
                        item.setLikeNum(item.getLikeNum() - 1);
                    } else {
                        item.setLiked(true);
                        item.setLikeNum(item.getLikeNum() + 1);
                    }
                    isNeedUpdate = true;
                } else if (id == R.id.action_comment_square) {
                    ((HomeFragment) getParentFragment()).start(CommentFragment.newInstance(item.getPicUri()));
                } else if (id == R.id.action_share_square) {
                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_STREAM, item.getPicUri());
                    shareIntent.setType("image/*");
                    startActivity(Intent.createChooser(shareIntent, getString(R.string.share_to)));
                }
                if (isNeedUpdate) {
                    post(new Runnable() {
                        @Override
                        public void run() {
                            mHSAdapter.notifyItemChanged(position);
                        }
                    });
                }
            }
        });
        mRecycler.setAdapter(mHSAdapter);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getAccountStatus()) {
                    ((HomeFragment) getParentFragment()).startForResult(PublishFragment.newInstance(), REQUEST_CODE);
                } else {
                    SnackbarUtils.Short(view, getString(R.string.please_login_first)).info().show();
                }
            }
        });
    }

    public List<SquareItem> getItemData() {
        return itemData;
    }

    public void setItemData(final List<SquareItem> itemData) {
        this.itemData = itemData;
        post(new Runnable() {
            @Override
            public void run() {
                mHSAdapter.setNewData(itemData);
                mHSAdapter.notifyDataSetChanged();
            }
        });
    }
}
