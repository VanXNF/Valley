package com.vanxnf.photovalley.adapter.Home;

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
import com.vanxnf.photovalley.widget.CircleImageView.CircleImageView;

import java.util.ArrayList;
import java.util.List;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created by VanXN on 2018/3/12.
 */

public class HomeSquareAdapter extends RecyclerView.Adapter<HomeSquareAdapter.ViewHolder> {

    private List<String> mItems = new ArrayList<>();
    private LayoutInflater mInflater;
    private View view;
    private OnItemClickListener mClickListener;

    public HomeSquareAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
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
        // TODO: 2018/3/14  photoview 调整
//        holder.picture.enable();
//        holder.picture.setMaxScale(3.0f);
//        Info info = holder.picture.getInfo();
//        holder.picture.animaFrom(info);
//        holder.picture.setAnimaDuring(1);
        final String uri = mItems.get(position);
        final String nameString = new String("第" + String.valueOf(position+1) + "位用户");
        holder.itemView.post(new Runnable() {
            @Override
            public void run() {
                // TODO: 2018/3/14 增加获取头像与用户名的数据集
                holder.name.setText(nameString);
                Glide.with(view).load(Uri.parse(uri)).into(holder.avatar);
                Glide.with(view).load(Uri.parse(uri)).transition(withCrossFade(600)).into(holder.picture);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView avatar;
        private TextView name;
        private PhotoView picture;
        private ImageView likeIcon;
        ViewHolder(View itemView) {
            super(itemView);
            avatar = (CircleImageView) itemView.findViewById(R.id.avatar_square);
            name = (TextView) itemView.findViewById(R.id.name_square);
            picture = (PhotoView) itemView.findViewById(R.id.display_image_square);
            likeIcon = (ImageView) itemView.findViewById(R.id.action_like_square);
        }
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }
}
