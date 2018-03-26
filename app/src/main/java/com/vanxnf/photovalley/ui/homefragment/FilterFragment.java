package com.vanxnf.photovalley.ui.homefragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.adapter.Home.HomeFilterAdapter;
import com.vanxnf.photovalley.base.BaseFragment;
import com.vanxnf.photovalley.listener.OnItemClickListener;
import com.vanxnf.photovalley.ui.previewfragment.PreviewFragment;
import com.vanxnf.photovalley.utils.Utility;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by VanXN on 2018/3/11.
 */

public class FilterFragment extends BaseFragment {

    private View view;
    private RecyclerView mRecycler;
    private List<String> items = new ArrayList<>();
    private HomeFilterAdapter mHFAdapter;
    public static final int CHOOSE_PHOTO = 0;

    public static FilterFragment newInstance() {
        return new FilterFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home_filter, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mRecycler = (RecyclerView) view.findViewById(R.id.recycler_view_filter);
        mHFAdapter = new HomeFilterAdapter(_mActivity);
        LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
        mRecycler.setLayoutManager(manager);
        mRecycler.setAdapter(mHFAdapter);
        mHFAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                if (view instanceof SimpleDraweeView) {
                    if (!getAccountStatus(_mActivity)) {
                        Toast.makeText(getContext(), "请登录后使用", Toast.LENGTH_SHORT).show();
                    } else {
                        if (position == mHFAdapter.getItemCount()-2 || position == mHFAdapter.getItemCount()-1) {
                            Toast.makeText(getContext(), "本滤镜由会员专享", Toast.LENGTH_SHORT).show();
                        }
                        checkPermission();
                    }
//                    ((HomeFragment) getParentFragment()).start(CycleFragment.newInstance(1));
                }
            }
        });

        mRecycler.post(new Runnable() {
            @Override
            public void run() {
                // Init Datas
                // TODO: 2018/3/14 调整获取图片数据方式
                String uri = new String("https://picsum.photos/800/600/?image=");
                String item;
                for (int i = 60; i < 70; i++) {
                    item = uri + i;
                    items.add(item);
                }
                mHFAdapter.setData(items);
            }
        });
    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            openAlbum();
        }
    }

    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openAlbum();
            } else {
                Toast.makeText(getContext(), "You denied the permission", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CHOOSE_PHOTO && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            ((HomeFragment) getParentFragment()).start(PreviewFragment.newInstance(uri.toString()));
        }
    }
}
