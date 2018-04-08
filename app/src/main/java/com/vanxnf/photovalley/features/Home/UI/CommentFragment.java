package com.vanxnf.photovalley.features.Home.UI;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.vanxnf.photovalley.MainActivity;
import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.base.BaseFragment;
import com.vanxnf.photovalley.features.Home.Adapter.CommentAdapter;
import com.vanxnf.photovalley.features.Home.Entity.CommentItem;
import com.vanxnf.photovalley.utils.DataUtil;

import java.util.ArrayList;
import java.util.List;

public class CommentFragment extends BaseFragment {

    private View view;
    private CommentAdapter mCAdapter;
    private RecyclerView mRecycler;
    private List<CommentItem> itemData;
    private DrawerLayout drawerLayout;
    private EditText etComment;
    private FloatingActionButton btnSend;

    public static CommentFragment newInstance() {
        return new CommentFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_comment, container ,false);
        drawerLayout = ((MainActivity) getActivity()).getDrawerLayout();
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        initView();
        return view;
    }

    private void initView() {
        mRecycler = (RecyclerView) view.findViewById(R.id.recycler_view_comment);
        etComment = (EditText) view.findViewById(R.id.etComment);
        btnSend = (FloatingActionButton) view.findViewById(R.id.btnSendComment);
        LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
        mRecycler.setLayoutManager(manager);
        mRecycler.getItemAnimator().setChangeDuration(0);
        itemData = new ArrayList<>();
        mCAdapter = new CommentAdapter(itemData);
        mCAdapter.openLoadAnimation();
        mCAdapter.setDuration(800);
        mRecycler.setAdapter(mCAdapter);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = etComment.getText().toString().trim();
                if (!TextUtils.isEmpty(comment)) {
                    itemData.add(new CommentItem(DataUtil.getRandomUri(), comment));
                    post(new Runnable() {
                        @Override
                        public void run() {
                            mCAdapter.setNewData(itemData);
                            mCAdapter.notifyDataSetChanged();
                        }
                    });
                    etComment.setText("");
                }
            }
        });
    }

    @Override
    public boolean onBackPressedSupport() {
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        pop();
        return true;
    }
}
