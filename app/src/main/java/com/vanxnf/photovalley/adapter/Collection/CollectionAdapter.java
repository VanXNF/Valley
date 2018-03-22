package com.vanxnf.photovalley.adapter.Collection;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.listener.OnItemClickListener;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by VanXN on 2018/3/22.
 */

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.ViewHolder> {


    private List<String> mItems = new ArrayList<>();
    private LayoutInflater mInflater;
    private View view;
    private OnItemClickListener mClickListener;


    public CollectionAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    public void setData(List<String> uriList) {
        mItems.clear();
        mItems.addAll(uriList);
        notifyDataSetChanged();
    }

    @Override
    public CollectionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = mInflater.inflate(R.layout.collection_item, parent, false);
        final CollectionAdapter.ViewHolder holder = new CollectionAdapter.ViewHolder(view);
        holder.mCollectionImg.setOnClickListener(new View.OnClickListener() {
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
    public void onBindViewHolder(final CollectionAdapter.ViewHolder holder, int position) {
        final String uri = mItems.get(position);
        holder.mCollectionImg.setImageURI(Uri.parse(uri));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView mCollectionImg;

        public ViewHolder(View itemView) {
            super(itemView);
            mCollectionImg = (SimpleDraweeView) itemView.findViewById(R.id.collection_image);

        }
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }
}
