package com.vanxnf.photovalley;


import android.support.v7.app.AppCompatActivity;

import com.vanxnf.photovalley.utils.StatusBarUtil;
import com.vanxnf.photovalley.utils.Utility;


/**
 * Created by VanXN on 2018/3/11.
 */

public class BaseActivity extends AppCompatActivity {


    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        setStatusBar();
    }

    protected void setStatusBar() {
        Utility.setStatusBarTransparent(getWindow());
    }


}
