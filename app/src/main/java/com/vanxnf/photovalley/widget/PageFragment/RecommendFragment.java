package com.vanxnf.photovalley.widget.PageFragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.features.adapter.RecommendImageAdapter;

/**
 * Created by VanXN on 2018/3/11.
 */

public class RecommendFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommend, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_recommend);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new RecommendImageAdapter());
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