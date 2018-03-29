package com.vanxnf.photovalley.features.About.Util;

import com.vanxnf.photovalley.R;

/**
 * Created by VanXN on 2018/3/28.
 */

public class AboutDataUtil {

    /** 0 为 空*/
    public static final Integer[] cardTitles = {
            0 , R.string.author , R.string.share_feedback
    };

    public static final Integer[] imageIds = {
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
}
