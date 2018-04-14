package com.vanxnf.photovalley.features.Home.UI;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.vanxnf.photovalley.MainActivity;
import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.base.BaseFragment;
import com.vanxnf.photovalley.features.Home.Adapter.EventAdapter;
import com.vanxnf.photovalley.features.Home.Entity.EventItem;
import com.vanxnf.photovalley.features.Home.Util.ItemUtil;
import com.vanxnf.photovalley.features.Preview.UI.PreviewFragment;
import com.vanxnf.photovalley.utils.DataUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EventFragment extends BaseFragment {

    private static final String DATA_FORM = "data_form";
    private static final String TITLE_FORM = "title_form";
    private DrawerLayout drawerLayout;
    private RecyclerView mRecycler;
    private EventAdapter mEAdapter;
    private List<EventItem> itemData;
    private View view;
    private String title;
    private String uri;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            uri = args.getString(DATA_FORM);
            title = args.getString(TITLE_FORM);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_event, container, false);
        drawerLayout = ((MainActivity) getActivity()).getDrawerLayout();
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        initView();
        return view;
    }

    public static EventFragment newInstance(String uri, String titleName) {
        Bundle args = new Bundle();
        args.putString(DATA_FORM, uri);
        args.putString(TITLE_FORM, titleName);
        EventFragment fragment = new EventFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void initView() {
        mRecycler = (RecyclerView) view.findViewById(R.id.recycler_view_event);
        GridLayoutManager manager = new GridLayoutManager(_mActivity, 3);
        mRecycler.setLayoutManager(manager);
        mRecycler.getItemAnimator().setChangeDuration(0);
        itemData = new ArrayList<>();
        itemData.add(new EventItem(EventItem.TITLE, EventItem.ITEM_SPAN_SIZE, title,
                getString(R.string.event_description_text), uri));
        String[] itemUris;
        if (title.equals(getString(R.string.event_spring_story))) {
            itemUris = ItemUtil.springUris;
        } else if (title.equals(getString(R.string.event_food_spree))) {
            itemUris = ItemUtil.FoodUris;
        } else if (title.equals(getString(R.string.event_succulent))) {
            itemUris = ItemUtil.succulentUris;
        } else {
            itemUris = DataUtil.uris;
        }
        for (int i = 0; i< 12; i++) {
            itemData.add(new EventItem(EventItem.IMG, EventItem.ITEM_SPAN_SIZE_MIN, itemUris[i]));
        }
        mEAdapter = new EventAdapter(itemData);
        mEAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                return itemData.get(position).getSpanSize();
            }
        });
        mEAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                EventItem item = itemData.get(position);
                if (position != 0) {
                    start(PreviewFragment.newInstance(item.getUri()));
                }
            }
        });
        mEAdapter.openLoadAnimation();
        mEAdapter.setDuration(800);
        mRecycler.setAdapter(mEAdapter);
    }

    @Override
    public boolean onBackPressedSupport() {
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        pop();
        return true;
    }
}
