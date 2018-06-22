package com.vanxnf.photovalley.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.animation.Animation;

import com.vanxnf.photovalley.R;

import me.yokeyword.fragmentation.ExtraTransaction;
import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.SupportFragmentDelegate;
import me.yokeyword.fragmentation.SupportHelper;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * 展示自定制的SupportFragment，不继承SupportFragment
 */
public class BaseFragment extends Fragment implements ISupportFragment {

    final SupportFragmentDelegate mDelegate = new SupportFragmentDelegate(this);
    protected FragmentActivity _mActivity;

    private final String KEY_VALLEY_CACHE_TOKEN_TAG = "ValleyCache_tokenTag";
    private final String KEY_VALLEY_CACHE_THEME_TAG = "ValleyCache_themeTag";
    private final String KEY_VALLEY_CACHE_ACCOUNT_TAG = "AccountCache_statusTag";
    private final String KEY_VALLEY_CACHE_MEMBER_TAG = "AccountCache_memberTag";
    private final String KEY_VALLEY_CACHE_ACCOUNT_NAME_TAG = "AccountCache_nameTag";
    private final String KEY_VALLEY_CACHE_LANGUAGE_TAG = "ValleyCache_languageTag";
    private final String KEY_VALLEY_CACHE_START_PAGE_TAG = "ValleyCache_start_pageTag";

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mDelegate.onAttach((Activity) context);
        _mActivity = mDelegate.getActivity();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDelegate.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mDelegate.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mDelegate.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mDelegate.onPause();
    }

    @Override
    public void onDestroyView() {
        mDelegate.onDestroyView();
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        mDelegate.onDestroy();
        super.onDestroy();
    }

    @Override
    public SupportFragmentDelegate getSupportDelegate() {
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
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return mDelegate.onCreateAnimation(transit, enter, nextAnim);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mDelegate.onSaveInstanceState(outState);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        mDelegate.onHiddenChanged(hidden);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        mDelegate.setUserVisibleHint(isVisibleToUser);
    }

    /**
     * Causes the Runnable r to be added to the action queue.
     * <p>
     * The runnable will be run after all the previous action has been run.
     * <p>
     * 前面的事务全部执行后 执行该Action
     *
     * @deprecated Use {@link #post(Runnable)} instead.
     */
    @Deprecated
    @Override
    public void enqueueAction(Runnable runnable) {
        mDelegate.enqueueAction(runnable);
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

    /**
     * Called when the enter-animation end.
     * 入栈动画 结束时,回调
     */
    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        mDelegate.onEnterAnimationEnd(savedInstanceState);
    }


    /**
     * Lazy initial，Called when fragment is first called.
     * <p>
     * 同级下的 懒加载 ＋ ViewPager下的懒加载  的结合回调方法
     */
    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        mDelegate.onLazyInitView(savedInstanceState);
    }

    /**
     * Called when the fragment is visible.
     * 当Fragment对用户可见时回调
     * <p>
     * Is the combination of  [onHiddenChanged() + onResume()/onPause() + setUserVisibleHint()]
     */
    @Override
    public void onSupportVisible() {
        mDelegate.onSupportVisible();
    }

    /**
     * Called when the fragment is invivible.
     * <p>
     * Is the combination of  [onHiddenChanged() + onResume()/onPause() + setUserVisibleHint()]
     */
    @Override
    public void onSupportInvisible() {
        mDelegate.onSupportInvisible();
    }

    /**
     * Return true if the fragment has been supportVisible.
     */
    @Override
    final public boolean isSupportVisible() {
        return mDelegate.isSupportVisible();
    }

    /**
     * Set fragment animation with a higher priority than the ISupportActivity
     * 设定当前Fragmemt动画,优先级比在SupportActivity里高
     */
    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return mDelegate.onCreateFragmentAnimator();
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
     * 设置Fragment内的全局动画
     */
    @Override
    public void setFragmentAnimator(FragmentAnimator fragmentAnimator) {
        mDelegate.setFragmentAnimator(fragmentAnimator);
    }

    /**
     * 按返回键触发,前提是SupportActivity的onBackPressed()方法能被调用
     *
     * @return false则继续向上传递, true则消费掉该事件
     */
    @Override
    public boolean onBackPressedSupport() {
        return mDelegate.onBackPressedSupport();
    }

    /**
     * 类似 {@link Activity#setResult(int, Intent)}
     * <p>
     * Similar to {@link Activity#setResult(int, Intent)}
     *
     * @see #startForResult(ISupportFragment, int)
     */
    @Override
    public void setFragmentResult(int resultCode, Bundle bundle) {
        mDelegate.setFragmentResult(resultCode, bundle);
    }

    /**
     * 类似  {@link Activity#onActivityResult(int, int, Intent)}
     * <p>
     * Similar to {@link Activity#onActivityResult(int, int, Intent)}
     *
     * @see #startForResult(ISupportFragment, int)
     */
    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        mDelegate.onFragmentResult(requestCode, resultCode, data);
    }

    /**
     * 在start(TargetFragment,LaunchMode)时,启动模式为SingleTask/SingleTop, 回调TargetFragment的该方法
     * 类似 {@link Activity#onNewIntent(Intent)}
     * <p>
     * Similar to {@link Activity#onNewIntent(Intent)}
     *
     * @param args putNewBundle(Bundle newBundle)
     * @see #start(ISupportFragment, int)
     */
    @Override
    public void onNewBundle(Bundle args) {
        mDelegate.onNewBundle(args);
    }

    /**
     * 添加NewBundle,用于启动模式为SingleTask/SingleTop时
     *
     * @see #start(ISupportFragment, int)
     */
    @Override
    public void putNewBundle(Bundle newBundle) {
        mDelegate.putNewBundle(newBundle);
    }


    /****************************************以下为可选方法(Optional methods)******************************************************/
    // 自定制Support时，可移除不必要的方法

    /**
     * 隐藏软键盘
     */
    protected void hideSoftInput() {
        mDelegate.hideSoftInput();
    }

    /**
     * 显示软键盘,调用该方法后,会在onPause时自动隐藏软键盘
     */
    protected void showSoftInput(final View view) {
        mDelegate.showSoftInput(view);
    }

    /**
     * 加载根Fragment, 即Activity内的第一个Fragment 或 Fragment内的第一个子Fragment
     *
     * @param containerId 容器id
     * @param toFragment  目标Fragment
     */
    public void loadRootFragment(int containerId, ISupportFragment toFragment) {
        mDelegate.loadRootFragment(containerId, toFragment);
    }

    public void loadRootFragment(int containerId, ISupportFragment toFragment, boolean addToBackStack, boolean allowAnim) {
        mDelegate.loadRootFragment(containerId, toFragment, addToBackStack, allowAnim);
    }

    public void start(ISupportFragment toFragment) {
        mDelegate.start(toFragment);
    }

    /**
     * @param launchMode Similar to Activity's LaunchMode.
     */
    public void start(final ISupportFragment toFragment, @LaunchMode int launchMode) {
        mDelegate.start(toFragment, launchMode);
    }

    /**
     * Launch an fragment for which you would like a result when it poped.
     */
    public void startForResult(ISupportFragment toFragment, int requestCode) {
        mDelegate.startForResult(toFragment, requestCode);
    }

    /**
     * Start the target Fragment and pop itself
     */
    public void startWithPop(ISupportFragment toFragment) {
        mDelegate.startWithPop(toFragment);
    }

    /**
     * @see #popTo(Class, boolean)
     * +
     * @see #start(ISupportFragment)
     */
    public void startWithPopTo(ISupportFragment toFragment, Class<?> targetFragmentClass, boolean includeTargetFragment) {
        mDelegate.startWithPopTo(toFragment, targetFragmentClass, includeTargetFragment);
    }

    public void replaceFragment(ISupportFragment toFragment, boolean addToBackStack) {
        mDelegate.replaceFragment(toFragment, addToBackStack);
    }

    public void pop() {
        mDelegate.pop();
    }

    /**
     * Pop the last fragment transition from the manager's fragment
     * back stack.
     * <p>
     * 出栈到目标fragment
     *
     * @param targetFragmentClass   目标fragment
     * @param includeTargetFragment 是否包含该fragment
     */
    public void popTo(Class<?> targetFragmentClass, boolean includeTargetFragment) {
        mDelegate.popTo(targetFragmentClass, includeTargetFragment);
    }

    /**
     * 获取栈内的fragment对象
     */
    public <T extends ISupportFragment> T findChildFragment(Class<T> fragmentClass) {
        return SupportHelper.findFragment(getChildFragmentManager(), fragmentClass);
    }

    /**
     * 获取主题标记
     */
    public int getThemeTag() {
        SharedPreferences preferences = getActivity().getSharedPreferences("ValleyCache", Context.MODE_PRIVATE);
        return preferences.getInt(KEY_VALLEY_CACHE_THEME_TAG, 0);
    }
    /**
     * 设置主题标记
     */
    public void setThemeTag(int tag) {
        SharedPreferences preferences = getActivity().getSharedPreferences("ValleyCache", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putInt(KEY_VALLEY_CACHE_THEME_TAG, tag);
        edit.commit();
    }
    /**
     * 获取账号标记
     */
    public boolean getAccountStatus() {
        SharedPreferences preferences = getActivity().getSharedPreferences("AccountCache", Context.MODE_PRIVATE);
        return preferences.getBoolean(KEY_VALLEY_CACHE_ACCOUNT_TAG, false);
    }
    /**
     * 设置账号标记
     */
    public void setAccountStatus(boolean tag) {
        SharedPreferences preferences = getActivity().getSharedPreferences("AccountCache", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putBoolean(KEY_VALLEY_CACHE_ACCOUNT_TAG, tag);
        edit.commit();
    }

    /**
     * 获取语言标记
     */
    public int getLanguageTag() {
        SharedPreferences preferences = getActivity().getSharedPreferences("ValleyCache", Context.MODE_PRIVATE);
        return preferences.getInt(KEY_VALLEY_CACHE_LANGUAGE_TAG, 0);//0为跟随系统
    }

    /**
     * 设置语言标记
     */
    public void setLanguageTag(int tag) {
        SharedPreferences preferences = getActivity().getSharedPreferences("ValleyCache", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putInt(KEY_VALLEY_CACHE_LANGUAGE_TAG, tag);
        edit.commit();
    }

    /**
     * 设置启动页标记
     */
    public void setStartPageTag(int tag) {
        SharedPreferences preferences = getActivity().getSharedPreferences("ValleyCache", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putInt(KEY_VALLEY_CACHE_START_PAGE_TAG, tag);
        edit.commit();
    }

    /**
     * 获取语言标记
     */
    public int getStartPageTag() {
        SharedPreferences preferences = getActivity().getSharedPreferences("ValleyCache", Context.MODE_PRIVATE);
        return preferences.getInt(KEY_VALLEY_CACHE_START_PAGE_TAG, 1);
    }

    /**
     * 获取会员标记
     */
    public boolean getMemberStatus() {
        SharedPreferences preferences = getActivity().getSharedPreferences("AccountCache", Context.MODE_PRIVATE);
        return preferences.getBoolean(KEY_VALLEY_CACHE_MEMBER_TAG, false);
    }

    /**
     * 设置会员标记
     */
    public void setMemberStatus(boolean tag) {
        SharedPreferences preferences = getActivity().getSharedPreferences("AccountCache", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putBoolean(KEY_VALLEY_CACHE_MEMBER_TAG, tag);
        edit.commit();
    }

    /**
     * 获取用户名
     */
    public String getAccountName() {
        SharedPreferences preferences = getActivity().getSharedPreferences("AccountCache", Context.MODE_PRIVATE);
        return preferences.getString(KEY_VALLEY_CACHE_ACCOUNT_NAME_TAG, getActivity().getString(R.string.app_name));
    }

    /**
     *设置用户名标记
     */
    public void setAccountName(String name) {
        SharedPreferences preferences = getActivity().getSharedPreferences("AccountCache", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString(KEY_VALLEY_CACHE_ACCOUNT_NAME_TAG, name);
        edit.commit();
    }

    /**
     * 获取Token
     */
    public String getToken() {
        SharedPreferences preferences = getActivity().getSharedPreferences("AccountCache", Context.MODE_PRIVATE);
        return preferences.getString(KEY_VALLEY_CACHE_TOKEN_TAG, null);
    }

    /**
     *设置Token标记
     */
    public void setToken(String token) {
        SharedPreferences preferences = getActivity().getSharedPreferences("AccountCache", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString(KEY_VALLEY_CACHE_TOKEN_TAG, token);
        edit.commit();
    }

}
