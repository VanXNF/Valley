package com.vanxnf.photovalley.features.UserProfile.UI;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;


import com.chad.library.adapter.base.BaseQuickAdapter;


import com.vanxnf.photovalley.MainActivity;
import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.base.BaseFragment;

import com.vanxnf.photovalley.features.Preview.UI.PreviewFragment;
import com.vanxnf.photovalley.features.UserProfile.Adapter.ProfileAdapter;

import com.vanxnf.photovalley.features.UserProfile.Adapter.SubscribeAdapter;
import com.vanxnf.photovalley.features.UserProfile.Entity.ProfileItem;

import com.vanxnf.photovalley.features.UserProfile.Entity.SubscribeItem;
import com.vanxnf.photovalley.features.UserProfile.Util.ItemUtil;
import com.vanxnf.photovalley.utils.SharedPreferences.SharedPreferencesUtil;
import com.vanxnf.photovalley.utils.SnackBar.SnackbarUtils;
import com.vanxnf.photovalley.utils.Utility;
import com.vanxnf.photovalley.widget.LovelyDialog.LovelyChoiceDialog;


import java.util.List;


public class UserFragment extends BaseFragment {

    private View view;
    private ProfileAdapter mPAdapter;
    private RecyclerView mRecycler;
    private List<ProfileItem> itemData;
    private DrawerLayout drawerLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_user_profile, container, false);
        drawerLayout = ((MainActivity) getActivity()).getDrawerLayout();
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
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
        mRecycler.getItemAnimator().setChangeDuration(0);
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
        List<SubscribeItem>  items = ItemUtil.getSubscribeItem();
        ArrayAdapter<SubscribeItem> adapter = new SubscribeAdapter(_mActivity, items);
        new LovelyChoiceDialog(_mActivity)
                .setTopColorRes(R.color.grey)
                .setTitle(R.string.subscribe)
                .setIcon(R.drawable.subscribe)
                .setMessage(R.string.subscribe_for)
                .setItems(adapter, new LovelyChoiceDialog.OnItemSelectedListener<SubscribeItem>() {
                    @Override
                    public void onItemSelected(int position, SubscribeItem item) {
                        if (!getMemberStatus()) {
                            setMemberStatus(true);
                            post(new Runnable() {
                                @Override
                                public void run() {
                                    mPAdapter.notifyItemChanged(0);
                                    drawerLayout.findViewById(R.id.member_nav)
                                            .setVisibility(View.VISIBLE);
                                }
                            });
                        }
                        SnackbarUtils.Short(view, getString(R.string.subscribe_success))
                                .info().show();
                    }
                })
                .show();

    }

    @Override
    public boolean onBackPressedSupport() {
        Utility.showStatusBar(getActivity().getWindow());
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        pop();
        return true;
    }
}
