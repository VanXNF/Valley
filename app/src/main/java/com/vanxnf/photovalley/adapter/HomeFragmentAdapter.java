package com.vanxnf.photovalley.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.vanxnf.photovalley.ui.homefragment.FilterFragment;
import com.vanxnf.photovalley.ui.homefragment.RecommendFragment;
import com.vanxnf.photovalley.ui.homefragment.SquareFragment;

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
            return SquareFragment.newInstance(0);
        } else if (position == 1) {
            return RecommendFragment.newInstance(0);
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
