package com.vanxnf.photovalley.features.About.Adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.features.About.Entity.AboutCardItem;
import com.vanxnf.photovalley.features.About.Entity.AboutItem;
import com.vanxnf.photovalley.features.About.Util.ItemUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VanXN on 2018/3/28.
 */

public class AboutListAdapter extends BaseQuickAdapter<AboutCardItem, BaseViewHolder>
        implements BaseQuickAdapter.OnItemClickListener {

    public AboutListAdapter(Context context, List data) {
        super(R.layout.about_list_card, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AboutCardItem item) {
        if (item.getCardTitleId() == 0) {
            helper.getView(R.id.list_card_title).setVisibility(View.GONE);
        } else {
            helper.setText(R.id.list_card_title, item.getCardTitleId());
        }
        helper.setNestView(R.id.about_card_recycler_view);
        RecyclerView childRecyclerView = helper.getView(R.id.about_card_recycler_view);
        childRecyclerView.setLayoutManager(new LinearLayoutManager(helper.itemView.getContext(),
                LinearLayoutManager.VERTICAL, false));
        childRecyclerView.setHasFixedSize(true);
        int startPosition = item.getStartPosition();
        List<AboutItem> aboutItemData = ItemUtil.getAboutItem(startPosition, startPosition + item.getCardSize());
        AboutItemAdapter aboutItemAdapter = new AboutItemAdapter(aboutItemData);
        aboutItemAdapter.setOnItemClickListener(this);
        childRecyclerView.setAdapter(aboutItemAdapter);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        // TODO: 2018/4/6 列表点击事件
//        Toast.makeText(mContext, "嵌套RecycleView item 收到: " + "点击了第 " + position + " 一次", Toast.LENGTH_SHORT).show();
    }
}
