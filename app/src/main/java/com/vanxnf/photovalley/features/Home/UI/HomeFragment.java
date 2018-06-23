package com.vanxnf.photovalley.features.Home.UI;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.vanxnf.photovalley.MainActivity;
import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.features.Home.Adapter.HomeFragmentAdapter;
import com.vanxnf.photovalley.base.BaseMainFragment;
import com.vanxnf.photovalley.features.Home.Adapter.HomeSquareAdapter;
import com.vanxnf.photovalley.features.Home.Entity.SquareItem;
import com.vanxnf.photovalley.features.Home.Gson.Message;
import com.vanxnf.photovalley.features.Home.Util.HttpUtil;
import com.vanxnf.photovalley.utils.SnackBar.SnackbarUtils;
import com.vanxnf.photovalley.widget.ParallaxViewPager.ParallaxViewPager;
import com.vanxnf.photovalley.widget.SlideTablayout.SlideTabLayout;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by VanXN on 2018/3/14.
 */

public class HomeFragment extends BaseMainFragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        Toolbar mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        SlideTabLayout mTabLayout = (SlideTabLayout) view.findViewById(R.id.tab_layout);
        ParallaxViewPager mViewPager = (ParallaxViewPager) view.findViewById(R.id.view_pager);
        mToolbar.setTitle(R.string.app_name);
        mToolbar.inflateMenu(R.menu.home_toolbar_menu);
        mToolbar.getMenu().findItem(R.id.action_switch_light).getIcon().setTint(getThemeTag() == 1 ? Color.WHITE : Color.BLACK);
        /** Toolbar Menu Item 点击事件*/
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_switch_light:
                        if (getThemeTag() == 0) {
                            setThemeTag(1);
                        } else {
                            setThemeTag(0);
                        }
                        //重启Activity
                        Intent intent = getActivity().getIntent();
                        getActivity().overridePendingTransition(0, 0);
                        getActivity().finish();
                        getActivity().overridePendingTransition(0, 0);
                        startActivity(intent);
                        break;
                    default:
                }
                return true;
            }
        });
        initToolbarNav(mToolbar);

        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.square));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.recommend));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.filter));
        mViewPager.setAdapter(new HomeFragmentAdapter(getChildFragmentManager(),
                getString(R.string.square),
                getString(R.string.recommend),
                getString(R.string.filter)));
        mViewPager.setOffscreenPageLimit(1);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.getTabAt(getStartPageTag()).select();//默认显示推荐页
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        if (requestCode == SquareFragment.REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            final String uri = data.getString("PhotoUri");
            final String desc = data.getString("PhotoDescription");
            HttpUtil.sendPostRequest("message", getToken(), new File(uri), desc, new okhttp3.Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    post(new Runnable() {
                        @Override
                        public void run() {
                            SnackbarUtils.Short(view, getString(R.string.publish_failed)).info().show();
                        }
                    });
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String responseData = response.body().string();
                    //获取服务器返回的Json数据
                    Gson gson = new Gson();
                    String status = null;
                    try {
                        Map<String, String> map = gson.fromJson(responseData, HashMap.class);
                        for (String key : map.keySet()) {
                            if (key.equals("status")) {
                                status = new String(map.get(key));
                                break;
                            }
                        }
                        if (status.equals("OK")) {
                            post(new Runnable() {
                                @Override
                                public void run() {
                                    SnackbarUtils.Short(view, getString(R.string.publish_success)).info().show();
                                }
                            });
                            SquareFragment fragment = findChildFragment(SquareFragment.class);
                            List<Message> itemData = new ArrayList<>();
                            itemData.add(new Message( getAccountName(),"https://s1.ax1x.com/2018/04/08/CPq9xg.png", desc, uri));
                            itemData.addAll(fragment.getItemData());
                            fragment.setItemData(itemData);
                        } else {
                            post(new Runnable() {
                                @Override
                                public void run() {
                                    SnackbarUtils.Short(view, getString(R.string.publish_failed)).info().show();
                                }
                            });
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        post(new Runnable() {
                            @Override
                            public void run() {
                                SnackbarUtils.Short(view, getString(R.string.publish_failed)).info().show();
                            }
                        });
                    }
                }
            });
        }
    }
}
