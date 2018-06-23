package com.vanxnf.photovalley.features.Preview.Adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.features.Preview.Gson.Filter;
import com.vanxnf.photovalley.utils.SharedPreferences.SharedPreferencesUtil;

import java.util.ArrayList;

public class FilterPreviewAdapter extends BaseQuickAdapter<Filter, BaseViewHolder> {

    public FilterPreviewAdapter (Context context, ArrayList data) {
        super(R.layout.filter_preview_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Filter item) {
        String name = item.getChinese_name();
        if (SharedPreferencesUtil.getLanguageTag(mContext) == 1) {
            name = item.getChinese_name();
        } else if (SharedPreferencesUtil.getLanguageTag(mContext) == 2) {
            name = item.getEnglish_name();
        }
        helper.setText(R.id.filter_name, name);
        Glide.with(mContext)
                .load(item.getCover_image())
                .into((ImageView) helper.getView(R.id.filter_thumbnail));
    }
}
