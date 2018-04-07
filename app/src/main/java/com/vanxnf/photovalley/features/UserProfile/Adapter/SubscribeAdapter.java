package com.vanxnf.photovalley.features.UserProfile.Adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.features.UserProfile.Entity.SubscribeItem;

import java.util.List;

/**
 * Created by VanXN 2018/4/6.
 */
public class SubscribeAdapter extends BaseQuickAdapter<SubscribeItem, BaseViewHolder> {
    @Override
    protected void convert(BaseViewHolder helper, SubscribeItem item) {
//        helper.setText(R.id.subscribe_description, item.getDescription());
//        helper.setText(R.id.subscribe_amount, item.getAmount());
    }

    public SubscribeAdapter(List data) {
        super(data);
    }
}
