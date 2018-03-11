package com.vanxnf.photovalley.widget.PageFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vanxnf.photovalley.R;

/**
 * Created by VanXN on 2018/3/11.
 */

public class RecommendFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recommend, container, false);
    }

    @Override
    void lazyLoad() {
        if (!isVisible || !isPrepared) {
            return;
        }
        // TODO: 2018/3/11 init data 
    }
}