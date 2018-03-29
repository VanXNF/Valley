package com.vanxnf.photovalley.features.About.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.listener.OnItemClickListener;
import com.vanxnf.photovalley.utils.Utility;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VanXN on 2018/3/28.
 */

public class AboutItemAdapter extends RecyclerView.Adapter<AboutItemAdapter.ViewHolder> {

    private List<Integer> mImageId = new ArrayList<>();
    private List<Integer> mMainText = new ArrayList<>();
    private List<Integer> mDescText = new ArrayList<>();
    private LayoutInflater mInflater;
    private View view;
    private Context context;
    private OnItemClickListener mClickListener;

    public AboutItemAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    public void setData(List<Integer> imageId, List<Integer> mainText, List<Integer> DescText) {
        mImageId.clear();
        mImageId.addAll(imageId);
        mMainText.clear();
        mMainText.addAll(mainText);
        mDescText.clear();
        mDescText.addAll(DescText);
        notifyDataSetChanged();
    }

    @Override
    public AboutItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = mInflater.inflate(R.layout.about_title_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if (mClickListener != null) {
                    mClickListener.onItemClick(position, v);
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(AboutItemAdapter.ViewHolder holder, int position) {
        int mImage = mImageId.get(position);
        if (mImage != 0) {
            Glide.with(view).load(mImageId.get(position)).into(holder.ivItem);
        }
        int mMain = mMainText.get(position);
        if (mMain == 0) {
            holder.itemView.setVisibility(View.GONE);
        } else {
            holder.tvMainText.setText(mMain);
        }
        int mDesc = mDescText.get(position);
        if (mDesc == 0) {
            holder.tvDescText.setVisibility(View.GONE);
        } else {
            holder.tvDescText.setText(mDesc);
        }

    }

    @Override
    public int getItemCount() {
        return mMainText.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivItem;
        TextView tvMainText;
        TextView tvDescText;
        public ViewHolder(View itemView) {
            super(itemView);
            ivItem = (ImageView) itemView.findViewById(R.id.item_image);
            tvMainText = (TextView) itemView.findViewById(R.id.item_text);
            tvDescText = (TextView) itemView.findViewById(R.id.item_desc);
        }
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }
}
