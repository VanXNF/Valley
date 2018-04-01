package com.vanxnf.photovalley.utils.SharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.vanxnf.photovalley.R;

public class SharedPreferencesUtil {

    /**
     * 获取主题标记
     */
    public static int getThemeTag(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("ValleyCache", Context.MODE_PRIVATE);
        return preferences.getInt("ValleyCache_themeTag", 1);
    }

    /**
     * 获取登录状态
     */
    public static boolean getAccountStatus(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("AccountCache", Context.MODE_PRIVATE);
        return preferences.getBoolean("AccountCache_statusTag", false);
    }

    /**
     * 获取用户名
     */
    public static String getAccountName(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("AccountCache", Context.MODE_PRIVATE);
        return preferences.getString("AccountCache_nameTag", context.getString(R.string.app_name));
    }

}
