package com.vanxnf.photovalley.features.Home.UI;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


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
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
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
//    private SwipeRefreshLayout mSwipe;
    private Call recommendCall;
    private Call actionCall;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home_recommend, container, false);
        initView();
        return view;
    }

    public static RecommendFragment newInstance() {
        return new RecommendFragment();
    }

    //初始化
    private void initView() {
        mRecycler = view.findViewById(R.id.recycler_view_recommend);
//        mSwipe = view.findViewById(R.id.swipe_recommend);
        LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
        itemData = new ArrayList<>();
        mRecycler.getItemAnimator().setChangeDuration(0);
        mRecycler.setLayoutManager(manager);
//        mSwipe.setColorSchemeColors(getResources().getColor(android.R.color.holo_blue_bright), getResources().getColor(android.R.color.holo_green_light),
//                getResources().getColor(android.R.color.holo_orange_light), getResources().getColor(android.R.color.holo_red_light));
//        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                refreshRecommend();
//            }
//        });
        recommendCall = HttpUtil.sendGetRequest("recommend", getToken(), new okhttp3.Callback() {

            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                notifyMessage(getString(R.string.recommend_list_error));
            }
            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                String responseData = response.body().string();
                try {
                    Gson gson = new Gson();
                    itemData = gson.fromJson(responseData, new TypeToken<ArrayList<Recommend>>(){}.getType());
                    notifyMessage(getString(R.string.load_success));
                } catch (Exception e) {
                    itemData = new ArrayList<>();
                    notifyMessage(getString(R.string.please_login_first));
                } finally {
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

                                if (id == R.id.action_like_recommend) {
                                    if (item.getStatus() == 1) {
                                        item.setStatus(0);
                                    } else {
                                        item.setStatus(1);
                                    }
                                    likeAction(item.getId(), item.getStatus(), position);
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
            }
        });
    }

    private void likeAction(int id, int status, final int position) {
        actionCall = HttpUtil.sendPostRequest("like", getToken(), status == 1 ? "True" : "False", id, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Recommend item = itemData.get(position);
                if (item.getStatus() == 1) {
                    item.setStatus(0);
                } else {
                    item.setStatus(1);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                //获取服务器返回的Json数据
                Gson gson = new Gson();
                String status = null;
                Map<String, String> map = gson.fromJson(responseData, HashMap.class);

                for (String key : map.keySet()) {
                    if (key.equals("status")) {
                        status = new String(map.get(key));
                        break;
                    }
                }
                if (status.equals("OK")) {
                    post(new Runnable() {
                        @Override
                        public void run() {
                            mHRAdapter.notifyItemChanged(position);
                        }
                    });
                }
            }
        });
    }

//    private void refreshRecommend() {
//        if (mHRAdapter == null) {
//            mHRAdapter = new HomeRecommendAdapter(_mActivity, itemData);
//            mHRAdapter.openLoadAnimation();
//            mHRAdapter.setDuration(800);
//            mHRAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
//                @Override
//                public void onItemChildClick(final BaseQuickAdapter adapter, View view, final int position) {
//                    Integer id = view.getId();
//                    Recommend item = itemData.get(position);
//                    if (id == R.id.recommend_image) {
//                        ((HomeFragment)getParentFragment()).start(PreviewFragment.newInstance(item.getImage()));
//                    } else {
//
//                        if (id == R.id.action_like_recommend) {
//                            if (item.getStatus() == 1) {
//                                item.setStatus(0);
//                            } else {
//                                item.setStatus(1);
//                            }
//                            likeAction(item.getId(), item.getStatus(), position);
//                        }
//                    }
//                }
//            });
//        } else if (mRecycler.getAdapter() == null) {
//            mRecycler.setAdapter(mHRAdapter);
//        }
//        recommendCall = HttpUtil.sendGetRequest("recommend", getToken(), new okhttp3.Callback() {
//
//            @Override
//            public void onFailure(okhttp3.Call call, IOException e) {
//                notifyMessage(getString(R.string.recommend_list_error));
//                post(new Runnable() {
//                    @Override
//                    public void run() {
//                        mSwipe.setRefreshing(false);
//                    }
//                });
//            }
//            @Override
//            public void onResponse(okhttp3.Call call, Response response) throws IOException {
//                String responseData = response.body().string();
//                try {
//                    Gson gson = new Gson();
//                    ArrayList<Recommend> items;
//                    items = gson.fromJson(responseData, new TypeToken<ArrayList<Recommend>>(){}.getType());
//                    itemData.clear();
//                    itemData.addAll(items);
//                    mHRAdapter.replaceData(itemData);
//                    notifyMessage(getString(R.string.refresh_success));
//                } catch (Exception e) {
//                    notifyMessage(getString(R.string.refresh_failed));
//                } finally {
//                    post(new Runnable() {
//                        @Override
//                        public void run() {
//                            mHRAdapter.notifyDataSetChanged();
//                            mSwipe.setRefreshing(false);
//                        }
//                    });
//                }
//
//            }
//        });
//    }

    private void notifyMessage(final String message) {
        post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(_mActivity, message, Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

    }
}