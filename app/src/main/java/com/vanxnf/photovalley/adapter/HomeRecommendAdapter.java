package com.vanxnf.photovalley.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.listener.OnItemClickListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created by VanXN on 2018/3/11.
 */

public class HomeRecommendAdapter extends RecyclerView.Adapter<HomeRecommendAdapter.ViewHolder> {

    private List<String> mItems = new ArrayList<>();
    private LayoutInflater mInflater;
    private View view;
    private OnItemClickListener mClickListener;


    public HomeRecommendAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    public void setData(List<String> uriList) {
        mItems.clear();
        mItems.addAll(uriList);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = mInflater.inflate(R.layout.home_recommend_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
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
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final String uri = mItems.get(position);
        final String name = new String("第" + String.valueOf(position+1) + "张图片");
        holder.itemView.post(new Runnable() {
            @Override
            public void run() {
                holder.tvAuthorName.setText(name);
                Glide.with(view)
                        .load(Uri.parse(uri))
                        .transition(withCrossFade())
                        .into(holder.iv);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private PhotoView iv;
        private TextView tvAuthorName;
        private ImageView ivLikeIcon;
        ViewHolder(View itemView) {
            super(itemView);
            iv = (PhotoView) itemView.findViewById(R.id.recommend_image);
            ivLikeIcon = (ImageView) itemView.findViewById(R.id.action_like_recommend);
            tvAuthorName = (TextView) itemView.findViewById(R.id.author_name);
        }
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

}