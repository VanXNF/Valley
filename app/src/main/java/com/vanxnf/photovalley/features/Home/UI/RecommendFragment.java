package com.vanxnf.photovalley.features.Home.UI;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.features.Home.Adapter.HomeRecommendAdapter;
import com.vanxnf.photovalley.base.BaseFragment;
import com.vanxnf.photovalley.features.Home.Entity.RecommendItem;
import com.vanxnf.photovalley.features.Home.Util.ItemUtil;

import com.vanxnf.photovalley.features.Preview.UI.PreviewFragment;

import com.vanxnf.photovalley.utils.SnackBar.SnackbarUtils;

import java.util.List;



/**
 * Created by VanXN on 2018/3/11.
 * Edited by VanXN on 2018/4/6.
 */

public class RecommendFragment extends BaseFragment {

    private View view;
    private RecyclerView mRecycler;
    private HomeRecommendAdapter mHRAdapter;
    private List<RecommendItem> itemData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home_recommend, container, false);
        initView(view);
        return view;
    }

    public static RecommendFragment newInstance() {
        return new RecommendFragment();
    }

    //初始化
    private void initView(View view) {
        itemData = ItemUtil.getRecommendItemData();
        mHRAdapter = new HomeRecommendAdapter(_mActivity, itemData);
        mRecycler = view.findViewById(R.id.recycler_view_recommend);
        mRecycler.getItemAnimator().setChangeDuration(0);
        LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
        mRecycler.setLayoutManager(manager);
        mHRAdapter.openLoadAnimation();
        mHRAdapter.setDuration(800);
        mHRAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(final BaseQuickAdapter adapter, View view, final int position) {
                Integer id = view.getId();
                RecommendItem item = itemData.get(position);
                if (id == R.id.recommend_image) {
                    ((HomeFragment)getParentFragment()).start(PreviewFragment.newInstance(item.getPictureUri()));
                } else {
                    boolean isNeedUpdate = false;
                    if (id == R.id.action_like_recommend) {
                        if (item.isLiked()) {
                            item.setLiked(false);
                        } else {
                            item.setLiked(true);
                        }
                        isNeedUpdate = true;
                    } else if (id == R.id.action_download_recommend) {
                        if (item.isDownload()) {
                            SnackbarUtils.Short(view, getString(R.string.download_finish)).info().show();
                        } else {
                            SnackbarUtils.Short(view, getString(R.string.already_download)).info().show();
                            item.setDownload(true);
                            isNeedUpdate = true;
                        }
                    }
                    if (isNeedUpdate) {
                        post(new Runnable() {
                            @Override
                            public void run() {
                                mHRAdapter.notifyItemChanged(position);
                            }
                        });
                    }
                }
            }
        });
        mRecycler.setAdapter(mHRAdapter);
    }
}