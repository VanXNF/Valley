package com.vanxnf.photovalley.features.About.Adapter;

import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.features.About.Entity.AboutItem;
import java.util.List;

/**
 * Created by VanXN on 2018/3/28.
 * Edited by VanXN on 2018/4/6.
 */

public class AboutItemAdapter extends BaseQuickAdapter<AboutItem, BaseViewHolder> {

    public AboutItemAdapter(List data) {
        super(R.layout.about_title_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AboutItem item) {
        if (item.getIconId() != 0) {
            helper.setImageResource(R.id.item_image, item.getIconId());
        }
        if (item.getTitleId() == 0) {
            helper.getView(R.id.about_title_item).setVisibility(View.GONE);
        } else {
            helper.setText(R.id.item_text, item.getTitleId());
        }
        if (item.getDescId() == 0) {
            helper.getView(R.id.item_desc).setVisibility(View.GONE);
        } else {
            helper.setText(R.id.item_desc, item.getDescId());
        }
    }
}
