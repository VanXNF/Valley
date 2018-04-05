package com.vanxnf.photovalley.features.Setting.Adapter;

import android.content.Context;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.features.Setting.Entity.SettingItem;

import java.util.List;


public class SettingAdapter extends BaseQuickAdapter<SettingItem, BaseViewHolder> {

    public SettingAdapter (Context context, List data) {
        super(R.layout.setting_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SettingItem item) {
        helper.setText(R.id.setting_text, item.getTextId())
                .setImageResource(R.id.setting_icon, item.getIconId());

    }
}
