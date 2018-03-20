package com.vanxnf.photovalley.adapter.Home;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VanXN on 2018/3/17.
 */

public class HomeFilterAdapter extends RecyclerView.Adapter<HomeFilterAdapter.ViewHolder> {

    private List<String> mItems = new ArrayList<>();
    private LayoutInflater mInflater;
    private View view;
    private OnItemClickListener mClickListener;


    public HomeFilterAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    public void setData(List<String> uriList) {
        mItems.clear();
        mItems.addAll(uriList);
        notifyDataSetChanged();
    }

    @Override
    public HomeFilterAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = mInflater.inflate(R.layout.home_filter_item, parent, false);
        final HomeFilterAdapter.ViewHolder holder = new HomeFilterAdapter.ViewHolder(view);
        holder.iv.setOnClickListener(new View.OnClickListener() {
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
    public void onBindViewHolder(final HomeFilterAdapter.ViewHolder holder, int position) {
        final String uri = mItems.get(position);
        holder.iv.setImageURI(Uri.parse(uri));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView iv;
        ViewHolder(View itemView) {
            super(itemView);
            iv = (SimpleDraweeView) itemView.findViewById(R.id.filter_image);
        }
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

}
