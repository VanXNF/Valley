package com.vanxnf.photovalley.base;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.utils.SharedPreferences.SharedPreferencesUtil;
import com.vanxnf.photovalley.utils.Utility;

/**
 * Created by YoKeyword on 16/2/3.
 */
public class BaseMainFragment extends BaseFragment {

    protected OnFragmentOpenDrawerListener mOpenDrawerListener;

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

    protected void initToolbarNav(Toolbar toolbar) {
        initToolbarNav(toolbar, false);
    }

    protected void initToolbarNav(Toolbar toolbar, boolean isHome) {
        toolbar.setNavigationIcon(R.drawable.toolbar_menu);
        if (getThemeTag() == 0) {
            toolbar.getNavigationIcon().setTint(Color.BLACK);
        } else {
            toolbar.getNavigationIcon().setTint(Color.WHITE);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOpenDrawerListener != null) {
                    mOpenDrawerListener.onOpenDrawer();
                }
            }
        });
    }

    public interface OnFragmentOpenDrawerListener {
        void onOpenDrawer();
    }
}
