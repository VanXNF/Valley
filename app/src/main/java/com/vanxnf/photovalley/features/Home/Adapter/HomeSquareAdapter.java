package com.vanxnf.photovalley.features.Home.Adapter;


import android.content.Context;

import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.features.Home.Entity.SquareItem;
import com.vanxnf.photovalley.widget.CircleImageView.CircleImageView;


import java.util.List;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created by VanXN on 2018/3/12.
 */

public class HomeSquareAdapter extends BaseQuickAdapter<SquareItem, BaseViewHolder> {

    public HomeSquareAdapter(Context context, List data) {
        super(R.layout.home_square_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SquareItem item) {
        helper.addOnClickListener(R.id.action_like_square)
                .addOnClickListener(R.id.action_comment_square)
                .addOnClickListener(R.id.action_share_square)
                .addOnClickListener(R.id.display_image_square)
                .addOnClickListener(R.id.avatar_square)
                .setText(R.id.square_name_text, item.getAuthorName())
                .setText(R.id.like_num, String.valueOf(item.getLikeNum()))
                .setText(R.id.square_description_text,
                        item.getDescription() != null ?
                                item.getDescription() : mContext.getString(R.string.untitled))
                .setText(R.id.name_square, item.getAuthorName())
                .setImageResource(R.id.action_like_square,
                        item.isLiked() ? R.drawable.square_like_red : R.drawable.square_like_red_border);
        Glide.with(mContext)
                .load(item.getAvatarUri())
                .into((CircleImageView) helper.getView(R.id.avatar_square));
        Glide.with(mContext)
                .load(item.getPicUri())
                .transition(withCrossFade(800))
                .into((ImageView) helper.getView(R.id.display_image_square));
        if (item.isMember()) {
            helper.getView(R.id.member_square).setVisibility(View.VISIBLE);
        }
    }
}
