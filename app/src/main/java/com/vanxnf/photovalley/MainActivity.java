package com.vanxnf.photovalley;


import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.vanxnf.photovalley.widget.PageFragment.FilteerFragment;
import com.vanxnf.photovalley.features.adapter.PagerAdapter;
import com.vanxnf.photovalley.widget.PageFragment.RecommendFragment;
import com.vanxnf.photovalley.widget.PageFragment.SquareFragment;
import com.vanxnf.photovalley.widget.Tablayout.SlideTabLayout;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity {

    private static final int TAB_RECOMMEND = 0x01;

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private ViewPager viewPager;
    private SlideTabLayout tabLayout;
    private List<Fragment> fragments;
    private List<String> titles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setOverflowIcon(getDrawable(R.drawable.ic_layout_manager_dark));
        setSupportActionBar(mToolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_dark);
        }
        //侧边栏显示
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_home);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayout.closeDrawers();
                return true;
            }
        });

        //加载主页tab fragments
        initFragmentData();
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(fragments.size());
        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager(), titles, fragments));

        //绑定TabLayout 与 ViewPaper
        tabLayout = (SlideTabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(TAB_RECOMMEND).select();//默认显示推荐页


    }

    /**创建fragment数据*/
    private void initFragmentData() {

        titles = new ArrayList<>();
        String[] tabs = getResources().getStringArray(R.array.home_tabs);
        for (String tab : tabs) {
            titles.add(tab);
        }
        fragments = new ArrayList<>();
        fragments.add(new SquareFragment());
        fragments.add(new RecommendFragment());
        fragments.add(new FilteerFragment());
    }


    /** 显示 Toolbar Menu 图标*/
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (menu != null) {
            if (menu.getClass().getSimpleName().equalsIgnoreCase("MenuBuilder")) {
                try {
                    Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    method.setAccessible(true);
                    method.invoke(menu, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }

    /** 创建 Toolbar Menu*/
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    /** Toolbar Menu Item 点击事件*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.search:
                break;
            case R.id.flow_layout:
                break;
            case R.id.sg_layout:

                break;
            default:
        }
        return true;
    }

}
