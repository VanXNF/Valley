package com.vanxnf.photovalley.features.About.Util;

import android.support.annotation.Nullable;

import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.features.About.Entity.AboutCardItem;
import com.vanxnf.photovalley.features.About.Entity.AboutItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VanXN on 2018/3/28.
 */

public class ItemUtil {

    /** 0 为 空*/
    public static final Integer[] cardTitles = {
            0 , R.string.author , R.string.share_feedback
    };

    public static final Integer[] iconIds = {
            R.drawable.about_app,
            R.drawable.about_information,
            0,
            R.drawable.about_author,
            R.drawable.about_github_logo,
            R.drawable.about_email,
            R.drawable.about_share,
            R.drawable.about_rate,
            R.drawable.about_feedback
    };

    public static final Integer[] mainTextIds = {
            R.string.app_name, R.string.version, 0,
            R.string.author_name, R.string.follow_on_git_hub, R.string.email,
            R.string.share_to_your_friends, R.string.rate_or_comment_in_market, R.string.feedback
    };

    public static final Integer[] descTextIds = {
            R.string.app_develop_date, R.string.version_code, 0,
            R.string.author_place, 0, R.string.email_address,
            0, 0, 0
    };

    public static Integer getCardTitles(int position) {
        return cardTitles[position];
    }

    public static Integer getIconIds(int position) {
        return iconIds[position];
    }

    public static Integer getMainTextIds(int position) {
        return mainTextIds[position];
    }

    public static Integer getDescTextIds(int position) {
        return descTextIds[position];
    }

    /**start开始 end结束 不包含end*/
    @Nullable
    public static List<AboutItem> getAboutItem(int start, int end) {
        if (start < 0 || end < start || end > mainTextIds.length) {
            return null;
        }
        List<AboutItem> list = new ArrayList<>();
        for (int i = start ; i < end; i++) {
            list.add(new AboutItem(getIconIds(i), getMainTextIds(i), getDescTextIds(i)));
        }
        return list;
    }

    public static List<AboutCardItem> getAboutCardItem() {
        List<AboutCardItem> list = new ArrayList<>();
        list.add(new AboutCardItem(getCardTitles(0), 2, 0));
        list.add(new AboutCardItem(getCardTitles(1), 3, 3));
        list.add(new AboutCardItem(getCardTitles(2), 3,6));
        return list;
    }
}
