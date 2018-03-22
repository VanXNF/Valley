package com.vanxnf.photovalley;


import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;

import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.vanxnf.photovalley.base.BaseActivity;
import com.vanxnf.photovalley.base.BaseFragment;
import com.vanxnf.photovalley.base.BaseMainFragment;
import com.vanxnf.photovalley.ui.accountfragment.LoginFragment;
import com.vanxnf.photovalley.ui.collectionfragment.CollectionFragment;
import com.vanxnf.photovalley.ui.downloadfragment.DownloadFragment;
import com.vanxnf.photovalley.ui.homefragment.HomeFragment;
import com.vanxnf.photovalley.ui.settingfragment.SettingFragment;
import com.vanxnf.photovalley.utils.Utility;
import com.vanxnf.photovalley.widget.CircleImageView.CircleImageView;

import me.yokeyword.fragmentation.ISupportFragment;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, BaseMainFragment.OnFragmentOpenDrawerListener
        , LoginFragment.OnLoginSuccessListener {

    // 再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;

    private DrawerLayout mDrawer;
    private NavigationView mNavigationView;
    private TextView mTvName;   // NavigationView上的名字
    private CircleImageView mImgNav;  // NavigationView上的头像
    private boolean isLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utility.setStatusBarTransparent(getWindow());
        setContentView(R.layout.activity_main);
        isLogin = getAccountStatus();
        HomeFragment fragment = findFragment(HomeFragment.class);
        if (fragment == null) {
            loadRootFragment(R.id.fl_container, HomeFragment.newInstance());
        }
        initView();
    }

    private void initView() {
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);
        mNavigationView.setCheckedItem(R.id.nav_home);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        /**设置MenuItem的字体颜色**/
        Resources resource = (Resources)getBaseContext().getResources();
        ColorStateList csl;
        if (getThemeTag() == 1) {
            csl = (ColorStateList) resource.getColorStateList(R.color.navigation_menu_text_color_day);
        } else {
            csl = (ColorStateList) resource.getColorStateList(R.color.navigation_menu_text_color_night);
        }
        navigationView.setItemTextColor(csl);
        navigationView.setItemIconTintList(csl);

        RelativeLayout rlNavHeader = (RelativeLayout) mNavigationView.getHeaderView(0);
        mTvName = (TextView) rlNavHeader.findViewById(R.id.tv_username);
        mImgNav = (CircleImageView) rlNavHeader.findViewById(R.id.civ_nav_avatar);
        if (isLogin) {
            mTvName.setText(getAccountName());
        }
        mImgNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawer.closeDrawer(GravityCompat.START);
                mDrawer.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        goLogin();
                    }
                }, 250);
            }
        });
    }

    @Override
    public void onBackPressedSupport() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            ISupportFragment topFragment = getTopFragment();

            // 主页的Fragment
            if (topFragment instanceof BaseMainFragment) {
                mNavigationView.setCheckedItem(R.id.nav_home);
            }

            if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
                pop();
            } else {
                if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
                    finish();
                } else {
                    TOUCH_TIME = System.currentTimeMillis();
                    Toast.makeText(this, R.string.press_again_exit, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    /**
     * 打开抽屉
     */
    @Override
    public void onOpenDrawer() {
        if (!mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.openDrawer(GravityCompat.START);
        }
    }

    @Override
    public boolean onNavigationItemSelected(final MenuItem item) {
        mDrawer.closeDrawer(GravityCompat.START);

        mDrawer.post(new Runnable() {
            @Override
            public void run() {
                int id = item.getItemId();

                final ISupportFragment topFragment = getTopFragment();
                final BaseFragment myHome = (BaseFragment) topFragment;

                if (id == R.id.nav_home) {
                    HomeFragment fragment = findFragment(HomeFragment.class);
                    myHome.start(fragment, BaseFragment.SINGLETASK);
                } else if (id == R.id.nav_collection) {
                    // TODO: 2018/3/15 收藏界面
                    CollectionFragment fragment = findFragment(CollectionFragment.class);
                    if (fragment == null) {
                        myHome.startWithPopTo(CollectionFragment.newInstance(), HomeFragment.class, false);
                    } else {
                        myHome.start(fragment, BaseFragment.SINGLETASK);
                    }
                } else if (id == R.id.nav_download) {
                    // TODO: 2018/3/15 下载界面
                    DownloadFragment fragment = findFragment(DownloadFragment.class);
                    if (fragment == null) {
                        myHome.startWithPopTo(DownloadFragment.newInstance(), HomeFragment.class, false);
                    } else {
                        start(fragment, BaseFragment.SINGLETASK);
                    }
                } else if (id == R.id.nav_setting) {
                    // TODO: 2018/3/15 设置界面
                    SettingFragment fragment = findFragment(SettingFragment.class);
                    if (fragment == null) {
                        myHome.startWithPopTo(SettingFragment.newInstance(), HomeFragment.class, false);
                    } else {
                        start(fragment, BaseFragment.SINGLETASK);
                    }
                } else if (id == R.id.nav_share) {
                    // TODO: 2018/3/15 分享本应用
                } else if (id == R.id.nav_about) {
                    // TODO: 2018/3/15 关于界面
                } else if (id == R.id.nav_quit_login) {
                    setAccountStatus(false);
                    setAccountName(getString(R.string.app_name));
                    mTvName.setText(R.string.app_name);
                    mNavigationView.setCheckedItem(R.id.nav_home);
                    Toast.makeText(MainActivity.this, getString(R.string.already_log_out), Toast.LENGTH_SHORT).show();
                }
            }
        });
        return true;
    }

    private void goLogin() {
        // TODO: 2018/3/22 调整配色方案
        if (getAccountStatus()) {
            Toast.makeText(this, getString(R.string.already_login), Toast.LENGTH_SHORT).show();
        } else {
            start(LoginFragment.newInstance());
        }
    }

    @Override
    public void onLoginSuccess(String account) {
        mTvName.setText(account);
        setAccountName(account);
//        mImgNav.setImageResource(R.drawable.pic1);
        Toast.makeText(this, account + " " + getString(R.string.sign_in_success), Toast.LENGTH_SHORT).show();
    }

}
