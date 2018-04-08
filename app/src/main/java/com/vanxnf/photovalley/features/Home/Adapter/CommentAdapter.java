package com.vanxnf.photovalley.features.Home.Adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.features.Home.Entity.CommentItem;
import com.vanxnf.photovalley.widget.CircleImageView.CircleImageView;

import java.util.List;

public class CommentAdapter extends BaseQuickAdapter<CommentItem, BaseViewHolder> {

    @Override
    protected void convert(BaseViewHolder helper, CommentItem item) {
        helper.setText(R.id.tvComment, item.getCommentText());
        Glide.with(mContext)
                .load(item.getAvatarUri())
                .into((CircleImageView) helper.getView(R.id.ivUserAvatar));
    }

    public CommentAdapter(@Nullable List<CommentItem> data) {
        super(R.layout.comment_item, data);
    }
}
