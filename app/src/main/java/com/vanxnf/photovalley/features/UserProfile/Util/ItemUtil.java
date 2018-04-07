package com.vanxnf.photovalley.features.UserProfile.Util;


import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.features.UserProfile.Entity.ProfileItem;
import com.vanxnf.photovalley.features.UserProfile.Entity.SubscribeItem;
import com.vanxnf.photovalley.utils.DataUtil;

import java.util.ArrayList;
import java.util.List;

public class ItemUtil {

    public static List<ProfileItem> getProfileItem() {
        List<ProfileItem> list = new ArrayList<>();
        list.add(new ProfileItem(ProfileItem.TITLE, ProfileItem.TITLE_SPAN_SIZE));
        for (int i = 0; i < 10; i++) {
            list.add(new ProfileItem(DataUtil.getImageUri(i + 180)));
        }
        return list;
    }

    public static List<SubscribeItem> getSubscribeItem() {
        List<SubscribeItem> list = new ArrayList<>();
        list.add(new SubscribeItem(R.string.per_month, R.string.per_month_pay));
        list.add(new SubscribeItem(R.string.per_quarter, R.string.per_quarter_pay));
        list.add(new SubscribeItem(R.string.per_year, R.string.per_year_pay));
        return list;
    }
}
