package com.vanxnf.photovalley.features.Download.Adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.features.Download.Entity.DownloadItem;

import java.util.List;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class DownloadAdapter extends BaseQuickAdapter<DownloadItem, BaseViewHolder> {

    public DownloadAdapter(@Nullable List<DownloadItem> data) {
        super(R.layout.download_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DownloadItem item) {
        Glide.with(mContext)
                .load(item.getUri())
                .transition(withCrossFade(800))
                .into((ImageView) helper.getView(R.id.download_image));
    }
}
