package com.vanxnf.photovalley.features.Home.UI;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.features.Home.Adapter.HomeRecommendAdapter;
import com.vanxnf.photovalley.base.BaseFragment;
import com.vanxnf.photovalley.features.Home.Gson.Recommend;
import com.vanxnf.photovalley.features.Home.Util.HttpUtil;


import com.vanxnf.photovalley.features.Preview.UI.PreviewFragment;

import com.vanxnf.photovalley.utils.SnackBar.SnackbarUtils;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Response;


/**
 * Created by VanXN on 2018/3/11.
 * Edited by VanXN on 2018/4/6.
 */

public class RecommendFragment extends BaseFragment {

    private View view;
    private RecyclerView mRecycler;
    private HomeRecommendAdapter mHRAdapter;
    private ArrayList<Recommend> itemData;
    private Call recommendCall;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home_recommend, container, false);
        initView(view);
        return view;
    }

    public static RecommendFragment newInstance() {
        return new RecommendFragment();
    }

    //初始化
    private void initView(final View view) {
        mRecycler = view.findViewById(R.id.recycler_view_recommend);
        LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
        mRecycler.getItemAnimator().setChangeDuration(0);
        mRecycler.setLayoutManager(manager);
        recommendCall = HttpUtil.sendGetRequest("recommend", getToken(), new okhttp3.Callback() {

            @Override
            public void onFailure(okhttp3.Call call, IOException e) {

                post(new Runnable() {
                    @Override
                    public void run() {
                        SnackbarUtils.Short(view, getString(R.string.recommend_list_error)).danger().show();
                    }
                });
            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                String responseData = response.body().string();
                try {
                    Gson gson = new Gson();
                    itemData = gson.fromJson(responseData, new TypeToken<ArrayList<Recommend>>(){}.getType());
                } catch (Exception e) {
                    itemData = new ArrayList<>();
                }
                mHRAdapter = new HomeRecommendAdapter(_mActivity, itemData);
                mHRAdapter.openLoadAnimation();
                mHRAdapter.setDuration(800);
                mHRAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(final BaseQuickAdapter adapter, View view, final int position) {
                        Integer id = view.getId();
                        Recommend item = itemData.get(position);
                        if (id == R.id.recommend_image) {
                            ((HomeFragment)getParentFragment()).start(PreviewFragment.newInstance(item.getImage()));
                        } else {
                            boolean isNeedUpdate = false;
                            if (id == R.id.action_like_recommend) {
                                if (item.getStatus() == 1) {
                                    item.setStatus(0);
                                } else {
                                    item.setStatus(1);
                                }
                                isNeedUpdate = true;
                            }
//                              else if (id == R.id.action_download_recommend) {
//                                if (item.isDownload()) {
//                                    SnackbarUtils.Short(view, getString(R.string.download_finish)).info().show();
//                                } else {
//                                    SnackbarUtils.Short(view, getString(R.string.already_download)).info().show();
//                                    item.setDownload(true);
//                                    isNeedUpdate = true;
//                                }
//                            }
                            if (isNeedUpdate) {
                                post(new Runnable() {
                                    @Override
                                    public void run() {
                                        mHRAdapter.notifyItemChanged(position);
                                    }
                                });
                            }
                        }
                    }
                });
                post(new Runnable() {
                    @Override
                    public void run() {
                        mRecycler.setAdapter(mHRAdapter);
                    }
                });

            }
        });
    }
}