package com.vanxnf.photovalley;


import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import com.vanxnf.photovalley.widget.PageFragment.FilteerFragment;
import com.vanxnf.photovalley.widget.PageFragment.RecommendFragment;
import com.vanxnf.photovalley.widget.PageFragment.SquareFragment;
import com.vanxnf.photovalley.widget.Tablayout.SlideTabLayout;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

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
        initData();
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(fragments.size());
        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager(), titles, fragments));

        tabLayout = (SlideTabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(TAB_RECOMMEND).select();

    }

    private void initData() {

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

    /** Toolbar 右上角 Menu Item 点击事件*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
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



//    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
//        private VerticalMovingStyle verticalMovingStyle = new VerticalMovingStyle();
//        @Override
//        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//            View view = inflater.inflate(R.layout.recycler_item, parent, false);
//            return new ViewHolder(view);
//        }
//
//        @Override
//        public void onBindViewHolder(ViewHolder holder, int position) {
//            holder.iv.setParallaxStyles(verticalMovingStyle);

//        }
//
//        @Override
//        public int getItemCount() {
//            return 25;
//        }
//
//        class ViewHolder extends RecyclerView.ViewHolder {
//            ScrollParallaxImageView iv;
//            ViewHolder(View itemView) {
//                super(itemView);
//                iv = (ScrollParallaxImageView) itemView.findViewById(R.id.img);
//            }
//        }
//    }

}
