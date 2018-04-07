package com.vanxnf.photovalley.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.utils.Utility;

import java.util.Locale;

import me.yokeyword.fragmentation.ExtraTransaction;
import me.yokeyword.fragmentation.ISupportActivity;
import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.SupportActivityDelegate;
import me.yokeyword.fragmentation.SupportHelper;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * 展示自定制的SupportActivity，不继承SupportActivity
 */
public class BaseActivity extends AppCompatActivity implements ISupportActivity{

    final SupportActivityDelegate mDelegate = new SupportActivityDelegate(this);
    private final String KEY_VALLEY_CACHE_THEME_TAG = "ValleyCache_themeTag";
    private final String KEY_VALLEY_CACHE_ACCOUNT_TAG = "AccountCache_statusTag";
    private final String KEY_VALLEY_CACHE_MEMBER_TAG = "AccountCache_memberTag";
    private final String KEY_VALLEY_CACHE_ACCOUNT_NAME_TAG = "AccountCache_nameTag";
    private final String KEY_VALLEY_CACHE_LANGUAGE_TAG = "ValleyCache_languageTag";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        loadingCurrentTheme();
        loadingCurrentLanguage();
        super.onCreate(savedInstanceState);
        mDelegate.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        mDelegate.onDestroy();
        super.onDestroy();
    }

    @Override
    public SupportActivityDelegate getSupportDelegate() {
        return mDelegate;
    }

    /**
     * Perform some extra transactions.
     * 额外的事务：自定义Tag，添加SharedElement动画，操作非回退栈Fragment
     */
    @Override
    public ExtraTransaction extraTransaction() {
        return mDelegate.extraTransaction();
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        return super.onCreateView(name, context, attrs);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDelegate.onPostCreate(savedInstanceState);
    }

    /**
     * Note： return mDelegate.dispatchTouchEvent(ev) || super.dispatchTouchEvent(ev);
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return mDelegate.dispatchTouchEvent(ev) || super.dispatchTouchEvent(ev);
    }

    /**
     * 不建议复写该方法,请使用 {@link #onBackPressedSupport} 代替
     */
    @Override
    final public void onBackPressed() {
        mDelegate.onBackPressed();
    }

    /**
     * 该方法回调时机为,Activity回退栈内Fragment的数量 小于等于1 时,默认finish Activity
     * 请尽量复写该方法,避免复写onBackPress(),以保证SupportFragment内的onBackPressedSupport()回退事件正常执行
     */
    @Override
    public void onBackPressedSupport() {
        mDelegate.onBackPressedSupport();
    }

    /**
     * 获取设置的全局动画 copy
     *
     * @return FragmentAnimator
     */
    @Override
    public FragmentAnimator getFragmentAnimator() {
        return mDelegate.getFragmentAnimator();
    }

    /**
     * Set all fragments animation.
     * 设置Fragment内的全局动画
     */
    @Override
    public void setFragmentAnimator(FragmentAnimator fragmentAnimator) {
        mDelegate.setFragmentAnimator(fragmentAnimator);
    }

    /**
     * Set all fragments animation.
     * 构建Fragment转场动画
     * <p/>
     * 如果是在Activity内实现,则构建的是Activity内所有Fragment的转场动画,
     * 如果是在Fragment内实现,则构建的是该Fragment的转场动画,此时优先级 > Activity的onCreateFragmentAnimator()
     *
     * @return FragmentAnimator对象
     */
    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return mDelegate.onCreateFragmentAnimator();
    }

    /**
     * Causes the Runnable r to be added to the action queue.
     * <p>
     * The runnable will be run after all the previous action has been run.
     * <p>
     * 前面的事务全部执行后 执行该Action
     */
    @Override
    public void post(Runnable runnable) {
        mDelegate.post(runnable);
    }

    /****************************************以下为可选方法(Optional methods)******************************************************/

    // 选择性拓展其他方法

    public void loadRootFragment(int containerId, @NonNull ISupportFragment toFragment) {
        mDelegate.loadRootFragment(containerId, toFragment);
    }

    public void start(ISupportFragment toFragment) {
        mDelegate.start(toFragment);
    }

    /**
     * @param launchMode Same as Activity's LaunchMode.
     */
    public void start(ISupportFragment toFragment, @ISupportFragment.LaunchMode int launchMode) {
        mDelegate.start(toFragment, launchMode);
    }

    /**
     * It is recommended to use {@link BaseFragment#startWithPopTo(ISupportFragment, Class, boolean)}.
     *
     * @see #popTo(Class, boolean)
     * +
     * @see #start(ISupportFragment)
     */
    public void startWithPopTo(ISupportFragment toFragment, Class<?> targetFragmentClass, boolean includeTargetFragment) {
        mDelegate.startWithPopTo(toFragment, targetFragmentClass, includeTargetFragment);
    }

    /**
     * Pop the fragment.
     */
    public void pop() {
        mDelegate.pop();
    }

    /**
     * Pop the last fragment transition from the manager's fragment
     * back stack.
     */
    public void popTo(Class<?> targetFragmentClass, boolean includeTargetFragment) {
        mDelegate.popTo(targetFragmentClass, includeTargetFragment);
    }

    /**
     * If you want to begin another FragmentTransaction immediately after popTo(), use this method.
     * 如果你想在出栈后, 立刻进行FragmentTransaction操作，请使用该方法
     */
    public void popTo(Class<?> targetFragmentClass, boolean includeTargetFragment, Runnable afterPopTransactionRunnable) {
        mDelegate.popTo(targetFragmentClass, includeTargetFragment, afterPopTransactionRunnable);
    }

    public void popTo(Class<?> targetFragmentClass, boolean includeTargetFragment, Runnable afterPopTransactionRunnable, int popAnim) {
        mDelegate.popTo(targetFragmentClass, includeTargetFragment, afterPopTransactionRunnable, popAnim);
    }

    /**
     * 得到位于栈顶Fragment
     */
    public ISupportFragment getTopFragment() {
        return SupportHelper.getTopFragment(getSupportFragmentManager());
    }

    /**
     * 获取栈内的fragment对象
     */
    public <T extends ISupportFragment> T findFragment(Class<T> fragmentClass) {
        return SupportHelper.findFragment(getSupportFragmentManager(), fragmentClass);
    }

    /**
     * 获取主题标记
     */
    public int getThemeTag() {
        SharedPreferences preferences = getSharedPreferences("ValleyCache", Context.MODE_PRIVATE);
        return preferences.getInt(KEY_VALLEY_CACHE_THEME_TAG, 0);
    }

    /**
     * 设置主题标记
     */
    public void setThemeTag(int tag) {
        SharedPreferences preferences = getSharedPreferences("ValleyCache", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putInt(KEY_VALLEY_CACHE_THEME_TAG, tag);
        edit.commit();
    }

    /**
     * 加载主题
     */
    protected void loadingCurrentTheme() {
        switch (getThemeTag()) {
            case 0:
                setTheme(R.style.ValleyTheme_Day);
                break;
            case 1:
                setTheme(R.style.ValleyTheme_Night);
                break;
        }
    }

    /**
     * 获取语言标记
     */
    public int getLanguageTag() {
        SharedPreferences preferences = getSharedPreferences("ValleyCache", Context.MODE_PRIVATE);
        return preferences.getInt(KEY_VALLEY_CACHE_LANGUAGE_TAG, 0);//1为跟随系统
    }

    /**
     * 设置语言标记
     */
    public void setLanguageTag(int tag) {
        SharedPreferences preferences = getSharedPreferences("ValleyCache", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putInt(KEY_VALLEY_CACHE_LANGUAGE_TAG, tag);
        edit.commit();
    }

    /**
     * 加载语言设置
     */
    protected void loadingCurrentLanguage() {
        Resources resources = getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();
        switch (getLanguageTag()) {
            case 0:
                config.locale = Locale.getDefault();
                break;
            case 1:
                config.locale = Locale.SIMPLIFIED_CHINESE;
                break;
            case 2:
                config.locale = Locale.ENGLISH;
                break;
        }
        resources.updateConfiguration(config, dm);
    }

    /**
     * 获取账号标记
     */
    public boolean getAccountStatus() {
        SharedPreferences preferences = getSharedPreferences("AccountCache", Context.MODE_PRIVATE);
        return preferences.getBoolean(KEY_VALLEY_CACHE_ACCOUNT_TAG, false);
    }
    /**
     * 设置账号标记
     */
    public void setAccountStatus(boolean tag) {
        SharedPreferences preferences = getSharedPreferences("AccountCache", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putBoolean(KEY_VALLEY_CACHE_ACCOUNT_TAG, tag);
        edit.commit();
    }

    /**
     * 获取用户名
     */
    public String getAccountName() {
        SharedPreferences preferences = getSharedPreferences("AccountCache", Context.MODE_PRIVATE);
        return preferences.getString(KEY_VALLEY_CACHE_ACCOUNT_NAME_TAG, getString(R.string.app_name));
    }

    /**
     *设置用户名标记
     */
    public void setAccountName(String name) {
        SharedPreferences preferences = getSharedPreferences("AccountCache", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString(KEY_VALLEY_CACHE_ACCOUNT_NAME_TAG, name);
        edit.commit();
    }

    /**
     * 获取会员标记
     */
    public boolean getMemberStatus() {
        SharedPreferences preferences = getSharedPreferences("AccountCache", Context.MODE_PRIVATE);
        return preferences.getBoolean(KEY_VALLEY_CACHE_MEMBER_TAG, false);
    }

    /**
     * 设置会员标记
     */
    public void setMemberStatus(boolean tag) {
        SharedPreferences preferences = getSharedPreferences("AccountCache", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putBoolean(KEY_VALLEY_CACHE_MEMBER_TAG, tag);
        edit.commit();
    }
}
