package com.vanxnf.photovalley.features.Home.Adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.features.Home.Entity.RecommendItem;

import java.util.List;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created by VanXN on 2018/3/11.
 */

public class HomeRecommendAdapter extends BaseQuickAdapter<RecommendItem, BaseViewHolder> {

    public HomeRecommendAdapter(Context context, List data) {
        super(R.layout.home_recommend_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RecommendItem item) {
        helper.setImageResource(R.id.action_like_recommend,
                item.isLiked() ? R.drawable.recommend_like_red : R.drawable.recommend_like_border)
                .setImageResource(R.id.action_download_recommend, R.drawable.recommend_download)
                .setText(R.id.author_name, item.getAuthorName())
                .addOnClickListener(R.id.action_like_recommend)
                .addOnClickListener(R.id.action_download_recommend)
                .addOnClickListener(R.id.recommend_image);
        Glide.with(mContext)
                .load(item.getPictureUri())
                .transition(withCrossFade(1000))
                .into((ImageView) helper.getView(R.id.recommend_image));
    }
}