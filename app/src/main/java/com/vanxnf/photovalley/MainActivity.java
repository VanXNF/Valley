package com.vanxnf.photovalley;


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
import com.vanxnf.photovalley.features.login.LoginFragment;
import com.vanxnf.photovalley.ui.homefragment.HomeFragment;
import com.vanxnf.photovalley.widget.CircleImageView.CircleImageView;

import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, BaseMainFragment.OnFragmentOpenDrawerListener
        , LoginFragment.OnLoginSuccessListener {

    public static final String TAG = MainActivity.class.getSimpleName();

    // 再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;

    private DrawerLayout mDrawer;
    private NavigationView mNavigationView;
    private TextView mTvName;   // NavigationView上的名字
    private CircleImageView mImgNav;  // NavigationView上的头像

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BaseFragment fragment = findFragment(HomeFragment.class);
        if (fragment == null) {
            loadRootFragment(R.id.fl_container, HomeFragment.newInstance());
        }
        initView();
    }

    /**
     * 设置动画，也可以使用setFragmentAnimator()设置
     */
    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        // 设置默认Fragment动画  默认竖向(和安卓5.0以上的动画相同)
        return super.onCreateFragmentAnimator();
        // 设置横向(和安卓4.x动画相同)
//        return new DefaultHorizontalAnimator();
        // 设置自定义动画
//        return new FragmentAnimator(enter,exit,popEnter,popExit);
    }

    private void initView() {
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        mDrawer.setDrawerListener(toggle);
        toggle.syncState();

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);
        mNavigationView.setCheckedItem(R.id.nav_home);

        RelativeLayout rlNavHeader = (RelativeLayout) mNavigationView.getHeaderView(0);
        mTvName = (TextView) rlNavHeader.findViewById(R.id.tv_username);
        mImgNav = (CircleImageView) rlNavHeader.findViewById(R.id.civ_nav_avatar);
        rlNavHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawer.closeDrawer(GravityCompat.START);
                mDrawer.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // TODO: 2018/3/15 增加登录判断
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

        mDrawer.postDelayed(new Runnable() {
            @Override
            public void run() {
                int id = item.getItemId();

                final ISupportFragment topFragment = getTopFragment();
                BaseFragment myHome = (BaseFragment) topFragment;

                if (id == R.id.nav_home) {
                    HomeFragment fragment = findFragment(HomeFragment.class);
                    Bundle newBundle = new Bundle();
                    newBundle.putString("from", "From:" + topFragment.getClass().getSimpleName());
                    fragment.putNewBundle(newBundle);

                    myHome.start(fragment, BaseFragment.SINGLETASK);
                } else if (id == R.id.nav_collection) {
                    // TODO: 2018/3/15 收藏界面
//                    DiscoverFragment fragment = findFragment(DiscoverFragment.class);
//                    if (fragment == null) {
//                        myHome.startWithPopTo(DiscoverFragment.newInstance(), HomeFragment.class, false);
//                    } else {
//                        // 如果已经在栈内,则以SingleTask模式start
//                        myHome.start(fragment, SupportFragment.SINGLETASK);
//                    }
                } else if (id == R.id.nav_download) {
                    // TODO: 2018/3/15 下载界面
//                    ShopFragment fragment = findFragment(ShopFragment.class);
//                    if (fragment == null) {
//                        myHome.startWithPopTo(ShopFragment.newInstance(), HomeFragment.class, false);
//                    } else {
//                        // 如果已经在栈内,则以SingleTask模式start,也可以用popTo
////                        start(fragment, SupportFragment.SINGLETASK);
//                        myHome.popTo(ShopFragment.class, false);
//                    }
                } else if (id == R.id.nav_setting) {
                    // TODO: 2018/3/15 设置界面
                } else if (id == R.id.nav_share) {
                    // TODO: 2018/3/15 分享本应用
                } else if (id == R.id.nav_feedback) {
                    // TODO: 2018/3/15 反馈界面
                }
            }
        }, 300);

        return true;
    }

    private void goLogin() {
        start(LoginFragment.newInstance());
    }

    @Override
    public void onLoginSuccess(String account) {
        mTvName.setText(account);
        mImgNav.setImageResource(R.drawable.pic1);
        Toast.makeText(this, R.string.sign_in_success, Toast.LENGTH_SHORT).show();
    }

}
