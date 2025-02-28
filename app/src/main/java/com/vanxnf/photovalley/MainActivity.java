package com.vanxnf.photovalley;


import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;

import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vanxnf.photovalley.base.BaseActivity;
import com.vanxnf.photovalley.base.BaseFragment;
import com.vanxnf.photovalley.base.BaseMainFragment;
import com.vanxnf.photovalley.features.About.UI.AboutFragment;
import com.vanxnf.photovalley.features.Account.UI.LoginFragment;
import com.vanxnf.photovalley.features.Collection.UI.CollectionFragment;
import com.vanxnf.photovalley.features.Download.UI.DownloadFragment;
import com.vanxnf.photovalley.features.Home.UI.HomeFragment;
import com.vanxnf.photovalley.features.Setting.UI.SettingFragment;
import com.vanxnf.photovalley.features.UserProfile.UI.UserFragment;
import com.vanxnf.photovalley.utils.SnackBar.SnackbarUtils;
import com.vanxnf.photovalley.utils.Utility;
import com.vanxnf.photovalley.widget.CircleImageView.CircleImageView;

import me.yokeyword.fragmentation.ISupportFragment;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, BaseMainFragment.OnFragmentOpenDrawerListener
        , LoginFragment.OnLoginSuccessListener {

    // 再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;
    private int ThemeTag = 0;

    private DrawerLayout mDrawer;
    private NavigationView mNavigationView;
    private TextView mTvName;   // NavigationView上的名字
    private CircleImageView mImgNav;  // NavigationView上的头像
    private boolean isLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utility.setStatusBar(getWindow(), getThemeTag());
        setContentView(R.layout.activity_main);
        isLogin = getAccountStatus();
        ThemeTag = getThemeTag();
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
        /**设置MenuItem的字体颜色**/
        Resources resource = (Resources)getBaseContext().getResources();
        ColorStateList csl = ThemeTag == 0 ? (ColorStateList) resource.getColorStateList(R.color.navigation_menu_text_color_day)
                : (ColorStateList) resource.getColorStateList(R.color.navigation_menu_text_color_night);
        mNavigationView.setItemTextColor(csl);
        mNavigationView.setItemIconTintList(csl);
        mNavigationView.getMenu().getItem(5).setVisible(isLogin);

        RelativeLayout rlNavHeader = (RelativeLayout) mNavigationView.getHeaderView(0);
        mTvName = (TextView) rlNavHeader.findViewById(R.id.tv_username);
        mImgNav = (CircleImageView) rlNavHeader.findViewById(R.id.civ_nav_avatar);
        if (getMemberStatus()) {
            rlNavHeader.findViewById(R.id.member_nav).setVisibility(View.VISIBLE);
        }
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
                    SnackbarUtils.Short(mDrawer, getString(R.string.press_again_exit))
                            .messageCenter().backColor(ThemeTag == 0 ? Color.WHITE : Color.BLACK)
                            .messageColor(ThemeTag == 0 ? Color.BLACK : Color.WHITE).show();
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
                    CollectionFragment fragment = findFragment(CollectionFragment.class);
                    if (fragment == null) {
                        myHome.startWithPopTo(CollectionFragment.newInstance(), HomeFragment.class, false);
                    } else {
                        myHome.start(fragment, BaseFragment.SINGLETASK);
                    }
                } else if (id == R.id.nav_download) {
                    DownloadFragment fragment = findFragment(DownloadFragment.class);
                    if (fragment == null) {
                        myHome.startWithPopTo(DownloadFragment.newInstance(), HomeFragment.class, false);
                    } else {
                        start(fragment, BaseFragment.SINGLETASK);
                    }
                } else if (id == R.id.nav_setting) {
                    SettingFragment fragment = findFragment(SettingFragment.class);
                    if (fragment == null) {
                        myHome.startWithPopTo(SettingFragment.newInstance(), HomeFragment.class, false);
                    } else {
                        start(fragment, BaseFragment.SINGLETASK);
                    }
                } else if (id == R.id.nav_about) {
                    AboutFragment fragment = findFragment(AboutFragment.class);
                    if (fragment == null) {
                        myHome.startWithPopTo(AboutFragment.newInstance(), HomeFragment.class, false);
                    } else {
                        start(fragment, BaseFragment.SINGLETASK);
                    }
                } else if (id == R.id.nav_quit_login) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            goLogout();
                            if (topFragment instanceof HomeFragment) {
                                mNavigationView.setCheckedItem(R.id.nav_home);
                            } else if (topFragment instanceof CollectionFragment) {
                                mNavigationView.setCheckedItem(R.id.nav_collection);
                            } else if (topFragment instanceof DownloadFragment) {
                                mNavigationView.setCheckedItem(R.id.nav_download);
                            } else if (topFragment instanceof SettingFragment) {
                                mNavigationView.setCheckedItem(R.id.nav_setting);
                            } else if (topFragment instanceof AboutFragment) {
                                mNavigationView.setCheckedItem(R.id.nav_about);
                            }
                            item.setVisible(false);
                        }
                    });
                }
            }
        });
        return true;
    }

    private void goLogin() {
        if (getAccountStatus()) {
            start(UserFragment.newInstance());
        } else {
            start(LoginFragment.newInstance());
        }
    }

    private void goLogout() {
        if (getAccountStatus()) {
            setAccountStatus(false);
            setMemberStatus(false);
            setAccountName(getString(R.string.app_name));
            setToken(null);
            mTvName.setText(R.string.app_name);
            post(new Runnable() {
                @Override
                public void run() {
                    mNavigationView.findViewById(R.id.member_nav).setVisibility(View.GONE);
                }
            });
            SnackbarUtils.Short(mDrawer, getString(R.string.already_log_out))
                    .messageCenter().backColor(ThemeTag == 0 ? Color.WHITE : Color.BLACK)
                    .messageColor(ThemeTag == 0 ? Color.BLACK : Color.WHITE).show();
        }
    }

    @Override
    public void onLoginSuccess(String account) {
        mTvName.setText(account);
        setAccountName(account);

        SnackbarUtils.Short(mDrawer, account +  " " + getString(R.string.sign_in_success))
                .messageCenter().backColor(ThemeTag == 0 ? Color.WHITE : Color.BLACK)
                .messageColor(ThemeTag == 0 ? Color.BLACK : Color.WHITE).show();

        //使Sign out 可见
        mNavigationView.getMenu().getItem(5).setVisible(true);
    }

    public DrawerLayout getDrawerLayout() {
        return mDrawer;
    }
}
