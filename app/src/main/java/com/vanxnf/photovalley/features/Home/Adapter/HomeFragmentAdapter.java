package com.vanxnf.photovalley.features.Home.Adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.vanxnf.photovalley.features.Home.UI.FilterFragment;
import com.vanxnf.photovalley.features.Home.UI.RecommendFragment;
import com.vanxnf.photovalley.features.Home.UI.SquareFragment;

/**
 * Created by VanXN on 2018/3/14.
 */

public class HomeFragmentAdapter extends FragmentPagerAdapter {

    String[] mTitles;

    public HomeFragmentAdapter(FragmentManager fm, String... titles) {
        super(fm);
        mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return SquareFragment.newInstance();
        } else if (position == 1) {
            return RecommendFragment.newInstance();
        } else {
            return FilterFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
