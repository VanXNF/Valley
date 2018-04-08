package com.vanxnf.photovalley.features.Home.Adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.features.Home.Entity.EventItem;

import java.util.List;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class EventAdapter extends BaseMultiItemQuickAdapter<EventItem, BaseViewHolder> {

    public EventAdapter(List<EventItem> data) {
        super(data);
        addItemType(EventItem.TITLE, R.layout.event_title_item);
        addItemType(EventItem.IMG, R.layout.event_img_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, EventItem item) {
        int type = item.getItemType();
        if (type == EventItem.TITLE) {
            helper.setText(R.id.event_title_name, item.getTitle())
                    .setText(R.id.event_description, item.getDescription());
            Glide.with(mContext)
                    .load(item.getUri())
                    .transition(withCrossFade(800))
                    .into((ImageView) helper.getView(R.id.event_title_image));
        } else if (type == EventItem.IMG) {
            Glide.with(mContext)
                    .load(item.getUri())
                    .transition(withCrossFade(800))
                    .into((ImageView) helper.getView(R.id.event_image));
        }
    }
}
