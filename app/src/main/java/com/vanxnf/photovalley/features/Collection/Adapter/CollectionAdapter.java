package com.vanxnf.photovalley.features.Collection.Adapter;

import android.content.Context;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.features.Collection.Entity.CollectionItem;

import java.util.List;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created by VanXN on 2018/3/22.
 */

public class CollectionAdapter extends BaseQuickAdapter<CollectionItem, BaseViewHolder> {

    public CollectionAdapter(Context context, List data) {
        super(R.layout.collection_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CollectionItem item) {
        Glide.with(mContext)
                .load(item.getImageUri())
                .transition(withCrossFade(1000))
                .into((ImageView) helper.getView(R.id.collection_image));
    }
}
