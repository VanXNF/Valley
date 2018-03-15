package com.vanxnf.photovalley.base;

import android.content.Context;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.vanxnf.photovalley.R;

/**
 * Created by YoKeyword on 16/2/3.
 */
public class BaseMainFragment extends BaseFragment {

    protected OnFragmentOpenDrawerListener mOpenDraweListener;

    protected void initToolbarNav(Toolbar toolbar) {
        initToolbarNav(toolbar, false);
    }

    protected void initToolbarNav(Toolbar toolbar, boolean isHome) {
        toolbar.setNavigationIcon(R.drawable.ic_menu_dark);
        toolbar.setOverflowIcon(getContext().getDrawable(R.drawable.ic_layout_manager_dark));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOpenDraweListener != null) {
                    mOpenDraweListener.onOpenDrawer();
                }
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentOpenDrawerListener) {
            mOpenDraweListener = (OnFragmentOpenDrawerListener) context;
        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentOpenDrawerListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mOpenDraweListener = null;
    }

    public interface OnFragmentOpenDrawerListener {
        void onOpenDrawer();
    }
}
