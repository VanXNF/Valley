package com.vanxnf.photovalley.features.Setting.Adapter;

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

import java.util.ArrayList;
import java.util.List;


public class SettingAdapter extends RecyclerView.Adapter<SettingAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    private Context mContext;
    private View view;
    private OnItemClickListener mClickListener;
    private List<Integer> mIconIds = new ArrayList<>();
    private List<Integer> mTextIds = new ArrayList<>();

    public SettingAdapter (Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.mContext = context;
    }

    public void setData(List<Integer> iconIds, List<Integer> textIds) {
        mIconIds.clear();
        mIconIds.addAll(iconIds);
        mTextIds.clear();
        mTextIds.addAll(textIds);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = mInflater.inflate(R.layout.setting_item, parent, false);
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
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(view).load(mIconIds.get(position)).into(holder.mIcon);
        holder.mText.setText(mTextIds.get(position));
    }

    @Override
    public int getItemCount() {
        return mTextIds.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mIcon;
        private TextView mText;
        public ViewHolder(View itemView) {
            super(itemView);
            mIcon = (ImageView) itemView.findViewById(R.id.setting_icon);
            mText = (TextView) itemView.findViewById(R.id.setting_text);
        }
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }
}
