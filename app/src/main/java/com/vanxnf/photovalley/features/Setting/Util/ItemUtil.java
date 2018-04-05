package com.vanxnf.photovalley.features.Setting.Util;

import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.features.Setting.Entity.SettingItem;

import java.util.ArrayList;
import java.util.List;

public class ItemUtil {

    public static final Integer[] IconIds = {
            R.drawable.setting_theme,
            R.drawable.setting_language,
            R.drawable.setting_start_page,
            R.drawable.setting_clear_cache
    };

    public static final Integer[] TitleIds = {
            R.string.theme,
            R.string.language,
            R.string.start_page,
            R.string.clear_pic_cache

    };

    public static Integer getIconId(int position) {
        return IconIds[position];
    }

    public static Integer getTitleId(int position) {
        return TitleIds[position];
    }

    public static List<SettingItem> getSettingItemData() {
        List<SettingItem> list = new ArrayList<>();
        for (int i = 0; i < TitleIds.length; i++) {
            list.add(new SettingItem(getIconId(i), getTitleId(i)));
        }
        return list;
    }
}
