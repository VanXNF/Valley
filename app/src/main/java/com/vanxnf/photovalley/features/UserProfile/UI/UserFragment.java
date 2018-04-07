package com.vanxnf.photovalley.features.UserProfile.UI;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.chad.library.adapter.base.BaseQuickAdapter;


import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.base.BaseFragment;

import com.vanxnf.photovalley.features.Preview.UI.PreviewFragment;
import com.vanxnf.photovalley.features.UserProfile.Adapter.ProfileAdapter;

import com.vanxnf.photovalley.features.UserProfile.Entity.ProfileItem;

import com.vanxnf.photovalley.features.UserProfile.Util.ItemUtil;
import com.vanxnf.photovalley.utils.SharedPreferences.SharedPreferencesUtil;
import com.vanxnf.photovalley.utils.SnackBar.SnackbarUtils;



import java.util.List;


public class UserFragment extends BaseFragment {

    private View view;
    private ProfileAdapter mPAdapter;
    private RecyclerView mRecycler;
    private List<ProfileItem> itemData;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_user_profile, container, false);
        initView();
        return view;
    }

    public static UserFragment newInstance() {
        return new UserFragment();
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setTitle(SharedPreferencesUtil.getAccountName(_mActivity));
        toolbar.setNavigationIcon(R.drawable.user_profile_arrow_back);
        toolbar.getNavigationIcon().setTint(getThemeTag() == 0 ? Color.BLACK : Color.WHITE);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop();
            }
        });
        mRecycler = (RecyclerView) view.findViewById(R.id.recycler_view_user_profile);
        GridLayoutManager manager = new GridLayoutManager(_mActivity, 2);
        mRecycler.setLayoutManager(manager);
        itemData = ItemUtil.getProfileItem();
        mPAdapter = new ProfileAdapter(_mActivity, itemData);
        mPAdapter.openLoadAnimation();
        mPAdapter.setDuration(800);
        mPAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                return itemData.get(position).getSpanSize();
            }
        });
        mPAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ProfileItem item = itemData.get(position);
                Integer id = view.getId();
                if (position == 0) {
                    if (id == R.id.user_bg_image) {
                        // TODO: 2018/4/6 选择背景
                        SnackbarUtils.Short(view, "选择背景").info().show();
                    } else if (id == R.id.user_avatar_image) {
                        // TODO: 2018/4/6 选择头像
                        SnackbarUtils.Short(view, "选择头像").info().show();
                    } else if (id == R.id.user_declaration_text) {
                        // TODO: 2018/4/6 编辑个性签名
                        SnackbarUtils.Short(view, "编辑个性签名").info().show();
                    }
                } else {
                    if (id == R.id.user_profile_image) {
                        start(PreviewFragment.newInstance(item.getPicUri()));
                    }
                }
            }
        });
        mRecycler.setAdapter(mPAdapter);
        FloatingActionButton faBtnMember = view.findViewById(R.id.btnMember);
        faBtnMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSubscribeDialog(v);
            }
        });

    }
    private void showSubscribeDialog(View view) {
//Please subscribe for HD-processing and Remove ads



        SnackbarUtils.Short(view, "充值失败").info().show();
    }
}
