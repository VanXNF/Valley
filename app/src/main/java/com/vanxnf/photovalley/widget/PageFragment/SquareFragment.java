package com.vanxnf.photovalley.widget.PageFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.features.adapter.RecommendImageAdapter;
import com.vanxnf.photovalley.features.adapter.SquareAdapter;

/**
 * Created by VanXN on 2018/3/11.
 */

public class SquareFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_square, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_square);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new SquareAdapter());
        return view;
    }

    @Override
    void lazyLoad() {
        if (!isVisible || !isPrepared) {
            return;
        }
        // TODO: 2018/3/11 init data 
    }
}
