package com.vanxnf.photovalley.features.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.widget.ScrollParallaxImageView.ScrollParallaxImageView;
import com.vanxnf.photovalley.widget.ScrollParallaxImageView.parallaxstyle.VerticalMovingStyle;

/**
 * Created by VanXN on 2018/3/11.
 */

public class RecommendImageAdapter extends RecyclerView.Adapter<RecommendImageAdapter.ViewHolder> {

    private VerticalMovingStyle ParallaxStyle = new VerticalMovingStyle();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recommend_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.iv.setParallaxStyles(ParallaxStyle);
        switch (position % 5) {
            case 0 : holder.iv.setImageResource(R.drawable.pic1); break;
            case 1 : holder.iv.setImageResource(R.drawable.pic2); break;
            case 2 : holder.iv.setImageResource(R.drawable.pic3); break;
            case 3 : holder.iv.setImageResource(R.drawable.pic4); break;
            case 4 : holder.iv.setImageResource(R.drawable.pic5); break;
        }
    }

    @Override
    public int getItemCount() {
        return 25;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ScrollParallaxImageView iv;
        ViewHolder(View itemView) {
            super(itemView);
            iv = (ScrollParallaxImageView) itemView.findViewById(R.id.recommend_image);
        }
    }
}