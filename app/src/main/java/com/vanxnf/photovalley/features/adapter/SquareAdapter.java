package com.vanxnf.photovalley.features.adapter;

import android.graphics.Picture;
import android.net.Uri;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.widget.CircleImageView.CircleImageView;

import java.util.Random;

/**
 * Created by VanXN on 2018/3/12.
 */

public class SquareAdapter extends RecyclerView.Adapter<SquareAdapter.ViewHolder> {

    View view;
    @Override
    public SquareAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        view = inflater.inflate(R.layout.square_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SquareAdapter.ViewHolder holder, int position) {
        String internetUri = new String("https://picsum.photos/800/600/?image=");
        int id = (position+1) * (position+1);
        String name = new String("第");
        String suffix = new String("只小猪猪");
        Uri imageUri = Uri.parse(internetUri + id);
        // TODO: 2018/3/12 尝试直接获取列表
//        switch (position % 5) {
//            case 0 :
//                holder.avatar.setImageResource(R.drawable.pic1);
//                break;
//            case 1:
//                holder.avatar.setImageResource(R.drawable.pic2);
//                break;
//            case 2:
//                holder.avatar.setImageResource(R.drawable.pic3);
//                break;
//            case 3:
//                holder.avatar.setImageResource(R.drawable.pic4);
//                break;
//            case 4:
//                holder.avatar.setImageResource(R.drawable.pic5);
//                break;
//        }
        Glide.with(view).load(imageUri).into(holder.avatar);
        holder.name.setText(name + (position+1) + suffix);
        Glide.with(view).load(imageUri).into(holder.picture);
    }

    @Override
    public int getItemCount() {
        return 25;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView avatar;
        TextView name;
        ImageView picture;
        ViewHolder(View itemView) {
            super(itemView);
            avatar = (CircleImageView) itemView.findViewById(R.id.avatar_square);
            name = (TextView) itemView.findViewById(R.id.name_square);
            picture = (ImageView) itemView.findViewById(R.id.display_image_square);
        }
    }
}
