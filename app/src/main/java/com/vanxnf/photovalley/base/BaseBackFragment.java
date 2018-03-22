package com.vanxnf.photovalley.base;

import android.support.v7.widget.Toolbar;
import android.view.View;

import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.utils.Utility;

/**
 * Created by YoKeyword on 16/2/7.
 */
public class BaseBackFragment extends BaseFragment {

    protected void initToolbarNav(Toolbar toolbar) {
        if (Utility.getThemeTag(getContext()) == -1) {
            toolbar.setNavigationIcon(R.drawable.ic_menu_light);
        } else {
            toolbar.setNavigationIcon(R.drawable.ic_menu_dark);
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop();
            }
        });
    }
}
