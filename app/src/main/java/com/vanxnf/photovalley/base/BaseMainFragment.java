package com.vanxnf.photovalley.base;

import android.content.Context;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.utils.Utility;

/**
 * Created by YoKeyword on 16/2/3.
 */
public class BaseMainFragment extends BaseFragment {

    protected OnFragmentOpenDrawerListener mOpenDrawerListener;

    protected void initToolbarNav(Toolbar toolbar) {
        initToolbarNav(toolbar, false);
    }

    protected void initToolbarNav(Toolbar toolbar, boolean isHome) {
        if (Utility.getThemeTag(getContext()) == 1) {
            toolbar.setNavigationIcon(R.drawable.ic_menu_dark);
        } else {
            toolbar.setNavigationIcon(R.drawable.ic_menu_light);
        }
        // TODO: 2018/3/18 改变图标颜色
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOpenDrawerListener != null) {
                    mOpenDrawerListener.onOpenDrawer();
                }
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentOpenDrawerListener) {
            mOpenDrawerListener = (OnFragmentOpenDrawerListener) context;
        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentOpenDrawerListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mOpenDrawerListener = null;
    }

    public interface OnFragmentOpenDrawerListener {
        void onOpenDrawer();
    }
}
