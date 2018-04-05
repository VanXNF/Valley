package com.vanxnf.photovalley.features.Preview.Adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.features.Preview.Entity.FilterPreviewItem;
import com.vanxnf.photovalley.utils.DataUtil;

import java.util.List;

public class FilterPreviewAdapter extends BaseQuickAdapter<FilterPreviewItem, BaseViewHolder> {

    public FilterPreviewAdapter (Context context, List data) {
        super(R.layout.filter_preview_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FilterPreviewItem item) {
        helper.setText(R.id.filter_name, item.getFilterNameId());
        Glide.with(mContext)
                .load(item.getThumbnailId())
                .into((ImageView) helper.getView(R.id.filter_thumbnail));
    }
}
