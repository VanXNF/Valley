package com.vanxnf.photovalley.ui.homefragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.adapter.Home.HomeFragmentAdapter;
import com.vanxnf.photovalley.base.BaseMainFragment;
import com.vanxnf.photovalley.widget.Loading.LoadingView;
import com.vanxnf.photovalley.widget.ParallaxViewPager.ParallaxViewPager;
import com.vanxnf.photovalley.widget.SlideTablayout.SlideTabLayout;

/**
 * Created by VanXN on 2018/3/14.
 */

public class HomeFragment extends BaseMainFragment {

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        final LoadingView mLoadingView = (LoadingView) view.findViewById(R.id.load_view);
        Toolbar mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        SlideTabLayout mTabLayout = (SlideTabLayout) view.findViewById(R.id.tab_layout);
        ParallaxViewPager mViewPager = (ParallaxViewPager) view.findViewById(R.id.view_pager);

        mToolbar.setTitle(R.string.home);
//        mToolbar.inflateMenu(R.menu.toolbar_menu);
//        /** Toolbar Menu Item 点击事件*/
//        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.search:
//                        Toast.makeText(getContext(), "暂不支持搜索", Toast.LENGTH_SHORT).show();
//                        break;
//                    default:
//                }
//                return true;
//            }
//        });
        getActivity().openOptionsMenu();
        initToolbarNav(mToolbar);

        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.square));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.recommend));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.filter));
        mViewPager.setAdapter(new HomeFragmentAdapter(getChildFragmentManager(),
                getString(R.string.square),
                getString(R.string.recommend),
                getString(R.string.filter)));
        mViewPager.setOffscreenPageLimit(2);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.getTabAt(1).select();//默认显示推荐页
    }

}
