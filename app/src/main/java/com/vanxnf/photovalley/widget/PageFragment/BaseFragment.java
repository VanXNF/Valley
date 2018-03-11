package com.vanxnf.photovalley.widget.PageFragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Created by VanXN on 2018/3/11.
 */

abstract class BaseFragment extends Fragment {

    public boolean isVisible = false;
    public boolean isPrepared = false;

    /**
     * setUserVisibleHint是在onCreateView之前调用的
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {

        super.setUserVisibleHint(isVisibleToUser);

        /**
         * 判断是否可见
         */
        if(getUserVisibleHint()) {
            isVisible = true;
            //执行可见方法-初始化数据之类
            lazyLoad();
        } else {
            isVisible = false;
            //不可见
            // TODO: 2018/3/11  onInvisible();
        }
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        //这里 初始化view的各控件 数据
        isPrepared = true;
        lazyLoad();
    }

    /**
     * 懒加载
     */
    abstract void lazyLoad() ;

}
