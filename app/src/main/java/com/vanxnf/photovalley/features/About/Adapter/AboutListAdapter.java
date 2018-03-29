package com.vanxnf.photovalley.features.About.Adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vanxnf.photovalley.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VanXN on 2018/3/28.
 */

public class AboutListAdapter extends RecyclerView.Adapter<AboutListAdapter.ViewHolder> {

    private List<Integer> mCardTitle = new ArrayList<>();
    private List<Integer> mImageId = new ArrayList<>();
    private List<Integer> mMainText = new ArrayList<>();
    private List<Integer> mDescText = new ArrayList<>();
    private LayoutInflater mInflater;
    private View view;

    public AboutListAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    public void setData(List<Integer> cardTitles, List<Integer> imageId, List<Integer> mainText, List<Integer> DescText) {
        mCardTitle.clear();
        mCardTitle.addAll(cardTitles);
        mImageId.clear();
        mImageId.addAll(imageId);
        mMainText.clear();
        mMainText.addAll(mainText);
        mDescText.clear();
        mDescText.addAll(DescText);
        notifyDataSetChanged();
    }

    @Override
    public AboutListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = mInflater.inflate(R.layout.about_list_card, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final AboutListAdapter.ViewHolder holder, final int position) {
        if (mCardTitle.get(position) == 0) {
            holder.mTitle.setVisibility(View.GONE);
        } else {
            holder.mTitle.setText(mCardTitle.get(position));
        }
        //card view item
        AboutItemAdapter mAIAdapter = new AboutItemAdapter(view.getContext());
        holder.mItemRecycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
        holder.mItemRecycler.setAdapter(mAIAdapter);
        holder.mItemRecycler.setNestedScrollingEnabled(false);
        holder.itemView.post(new Runnable() {
            @Override
            public void run() {
                AboutItemAdapter mAIAdapter = new AboutItemAdapter(view.getContext());
                holder.mItemRecycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
                holder.mItemRecycler.setAdapter(mAIAdapter);
                holder.mItemRecycler.setNestedScrollingEnabled(false);
                List<Integer> imageIds;
                List<Integer> mainTextIds;
                List<Integer> descTsxtIds;
                if (position == 0) {
                    //第一块内容 main
                    imageIds = mImageId.subList(0, 2);
                    mainTextIds = mMainText.subList(0, 2);
                    descTsxtIds = mDescText.subList(0, 2);
                } else if (position == 1) {
                    //第二块内容 author
                    imageIds = mImageId.subList(3, 6);
                    mainTextIds = mMainText.subList(3, 6);
                    descTsxtIds = mDescText.subList(3, 6);
                } else {
                    //第三块内容 share & feedback
                    imageIds = mImageId.subList(6, 9);
                    mainTextIds = mMainText.subList(6, 9);
                    descTsxtIds = mDescText.subList(6, 9);
                }
                mAIAdapter.setData(imageIds, mainTextIds, descTsxtIds);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCardTitle.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTitle;
        RecyclerView mItemRecycler;
        public ViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.list_card_title);
            mItemRecycler = (RecyclerView) itemView.findViewById(R.id.about_card_recycler_view);
        }
    }
}
