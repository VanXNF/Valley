package com.vanxnf.photovalley.widget.PageFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vanxnf.photovalley.R;

/**
 * Created by VanXN on 2018/3/11.
 */

public class FilteerFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_filter, container, false);
    }

    @Override
    void lazyLoad() {
        if (!isVisible || !isPrepared) {
            return;
        }
        // TODO: 2018/3/11 init data 
    }
}
