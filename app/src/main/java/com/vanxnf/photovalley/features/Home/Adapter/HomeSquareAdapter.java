package com.vanxnf.photovalley.features.Home.Adapter;


import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.features.Home.Util.HomeDataUtil;
import com.vanxnf.photovalley.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VanXN on 2018/3/12.
 */

public class HomeSquareAdapter extends RecyclerView.Adapter<HomeSquareAdapter.ViewHolder> {

    private List<String> mItems = new ArrayList<>();
    private LayoutInflater mInflater;
    private View view;
    private Context context;
    private OnItemClickListener mClickListener;

    public HomeSquareAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    public void setData(List<String> uriList) {
        mItems.clear();
        mItems.addAll(uriList);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = mInflater.inflate(R.layout.home_square_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        //为头像设置监听
        holder.avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if (mClickListener != null) {
                    mClickListener.onItemClick(position, v);
                }
            }
        });
        //为图片设置监听
        holder.picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if (mClickListener != null) {
                    mClickListener.onItemClick(position, v);
                }
            }
        });
        //为喜欢按钮设置监听
        holder.likeIcon.setOnClickListener(new View.OnClickListener() {
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
    public void onBindViewHolder(final HomeSquareAdapter.ViewHolder holder, final int position) {
        final String uri = mItems.get(position);
        holder.itemView.post(new Runnable() {
            @Override
            public void run() {
                // TODO: 2018/3/14 增加获取头像与用户名的数据集
                holder.name.setText(HomeDataUtil.getRandomName());
                holder.picture.setImageURI(Uri.parse(uri));
                holder.avatar.setImageURI(Uri.parse(uri));
                //展示会员图标
                if (position % 5 == 1) {
                    holder.memberIcon.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView avatar;
        private TextView name;
        private SimpleDraweeView picture;
        private ImageView likeIcon;
        private ImageView commentIcon;
        private ImageView shareIcon;
        private ImageView memberIcon;
        ViewHolder(View itemView) {
            super(itemView);
            avatar = (SimpleDraweeView) itemView.findViewById(R.id.avatar_square);
            name = (TextView) itemView.findViewById(R.id.name_square);
            picture = (SimpleDraweeView) itemView.findViewById(R.id.display_image_square);
            likeIcon = (ImageView) itemView.findViewById(R.id.action_like_square);
            shareIcon = (ImageView) itemView.findViewById(R.id.action_share_square);
            commentIcon = (ImageView) itemView.findViewById(R.id.action_comment_square);
            memberIcon = (ImageView) itemView.findViewById(R.id.member_square);
        }
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }
}
