package com.vanxnf.photovalley.features.UserProfile.Adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.features.UserProfile.Entity.ProfileItem;
import com.vanxnf.photovalley.utils.SharedPreferences.SharedPreferencesUtil;

import java.util.List;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created by VanXN 2018/4/6
 */

public class ProfileAdapter extends BaseMultiItemQuickAdapter<ProfileItem, BaseViewHolder> {

    @Override
    protected void convert(BaseViewHolder helper, ProfileItem item) {
        int position = helper.getLayoutPosition();
        switch (helper.getItemViewType()) {
            case ProfileItem.IMG:
                helper.addOnClickListener(R.id.user_profile_image);
                Glide.with(mContext)
                        .load(item.getPicUri())
                        .transition(withCrossFade(800))
                        .into((ImageView) helper.getView(R.id.user_profile_image));
                break;
            case ProfileItem.TITLE:
                helper.addOnClickListener(R.id.user_avatar_image)
                        .addOnClickListener(R.id.user_declaration_text)
                        .addOnClickListener(R.id.user_bg_image);
                helper.getView(R.id.member_user_profile)
                        .setVisibility(SharedPreferencesUtil.getMemberStatus(mContext) ? View.VISIBLE : View.GONE);
                break;
        }

    }

    public ProfileAdapter(Context context, List data) {
        super(data);
        addItemType(ProfileItem.IMG, R.layout.user_pic_item);
        addItemType(ProfileItem.TITLE, R.layout.user_title_item);
    }
}
