package com.vanxnf.photovalley.features.Home.UI;

import android.content.Intent;

import android.os.Bundle;


import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import com.chad.library.adapter.base.BaseQuickAdapter;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vanxnf.photovalley.R;

import com.vanxnf.photovalley.features.Home.Adapter.HomeSquareAdapter;
import com.vanxnf.photovalley.base.BaseFragment;
import com.vanxnf.photovalley.features.Home.Gson.Message;
import com.vanxnf.photovalley.features.Home.Util.HttpUtil;

import com.vanxnf.photovalley.features.Preview.UI.PreviewFragment;
import com.vanxnf.photovalley.utils.SnackBar.SnackbarUtils;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


/**
 * Created by VanXN on 2018/3/11.
 * Edited by VanXN on 2018/4/6.
 */

public class SquareFragment extends BaseFragment {

    public static final int REQUEST_CODE = 0x11;
    private RecyclerView mRecycler;
    private HomeSquareAdapter mHSAdapter;
//    private SwipeRefreshLayout mSwipe;
    private List<Message> itemData;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home_square, container, false);
        initView();
        return view;
    }

    public static SquareFragment newInstance() {
        return new SquareFragment();
    }

    //轻量级初始化
    private void initView() {
        FloatingActionButton btnCreate = view.findViewById(R.id.action_create_square);
        mRecycler = view.findViewById(R.id.recycler_view_square);
//        mSwipe = view.findViewById(R.id.swipe_square);
        LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
        mRecycler.setLayoutManager(manager);
        mRecycler.getItemAnimator().setChangeDuration(0);
//        itemData = ItemUtil.getSquareItemData();
        HttpUtil.sendGetRequest("message", getToken(), new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                try {
                    Gson gson = new Gson();
                    itemData = gson.fromJson(responseData, new TypeToken<ArrayList<Message>>(){}.getType());
//                    notifyMessage(getString(R.string.load_success));
                } catch (Exception e) {
//                    itemData = new ArrayList<>();
//                    notifyMessage(getString(R.string.please_login_first));
                } finally {
                    mHSAdapter = new HomeSquareAdapter(_mActivity, itemData);
                    mHSAdapter.openLoadAnimation();
                    mHSAdapter.setDuration(800);
                    mHSAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                        @Override
                        public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
                            Integer id = view.getId();
                            Message item = itemData.get(position);
                            if (id == R.id.display_image_square) {
                                ((HomeFragment) getParentFragment()).start(PreviewFragment.newInstance(item.getImage()));
                            } else if (id == R.id.action_like_square) {
                                if (item.getStatus() == 1) {
                                    item.setStatus(0);
                                    item.setLike_number(item.getLike_number() - 1);
                                } else {
                                    item.setStatus(1);
                                    item.setLike_number(item.getLike_number() + 1);
                                }
                                likeAction(item.getId(), item.getStatus(), position);
                            } else if (id == R.id.action_comment_square) {
                                ((HomeFragment) getParentFragment()).start(CommentFragment.newInstance(item.getImage()));
                            } else if (id == R.id.action_share_square) {
                                Intent shareIntent = new Intent();
                                shareIntent.setAction(Intent.ACTION_SEND);
                                shareIntent.putExtra(Intent.EXTRA_STREAM, item.getImage());
                                shareIntent.setType("image/*");
                                startActivity(Intent.createChooser(shareIntent, getString(R.string.share_to)));
                            }
                        }
                    });
                }
            }
        });
        mRecycler.setAdapter(mHSAdapter);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getAccountStatus()) {
                    ((HomeFragment) getParentFragment()).startForResult(PublishFragment.newInstance(), REQUEST_CODE);
                } else {
                    SnackbarUtils.Short(view, getString(R.string.please_login_first)).info().show();
                }
            }
        });
    }

    private void likeAction(int id, int status, final int position) {
        HttpUtil.sendPostRequest("like", getToken(), status == 1 ? "True" : "False", id, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message item = itemData.get(position);
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
                            mHSAdapter.notifyItemChanged(position);
                        }
                    });
                }
            }
        });
    }


    public List<Message> getItemData() {
        return itemData;
    }

    public void setItemData(final List<Message> itemData) {
        this.itemData = itemData;
        post(new Runnable() {
            @Override
            public void run() {
                mHSAdapter.setNewData(itemData);
                mHSAdapter.notifyDataSetChanged();
            }
        });
    }
}
