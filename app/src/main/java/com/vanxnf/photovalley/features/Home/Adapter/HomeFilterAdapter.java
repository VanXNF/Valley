package com.vanxnf.photovalley.features.Home.Adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.features.Home.Entity.FilterItem;
import com.vanxnf.photovalley.features.Home.Util.FilterUtil;
import com.vanxnf.photovalley.utils.DataUtil;

import java.util.List;

public class HomeFilterAdapter extends BaseMultiItemQuickAdapter<FilterItem,BaseViewHolder> {

    public HomeFilterAdapter(Context context, List data) {
        super(data);
        addItemType(FilterItem.ACTION, R.layout.home_filter_action_item);
        addItemType(FilterItem.EVENT, R.layout.home_filter_event_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, FilterItem item) {
        int position = helper.getLayoutPosition();
        switch (helper.getItemViewType()) {
            case FilterItem.ACTION:
                helper.setText(R.id.filter_action_name,
                        mContext.getString(FilterUtil.getActionTextIdByPosition(position)));
                Glide.with(mContext)
                        .load(FilterUtil.getActionIconIdByPosition(position))
                        .into((ImageView) helper.getView(R.id.filter_action_icon));
                Glide.with(mContext)
                        .load(DataUtil.getImageUri(position + 200))
                        .into((ImageView) helper.getView(R.id.filter_action_image));
                break;
            case FilterItem.EVENT:
                helper.setText(R.id.filter_event_name,
                        FilterUtil.getFilterNameIdByPosition(position-2));
                Glide.with(mContext)
                        .load(DataUtil.getImageUri(position))
                        .into((ImageView) helper.getView(R.id.filter_event_image));
                break;
        }
    }

}
