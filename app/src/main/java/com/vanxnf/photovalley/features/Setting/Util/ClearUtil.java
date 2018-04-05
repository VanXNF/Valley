package com.vanxnf.photovalley.features.Setting.Util;

import android.content.Context;

import com.bumptech.glide.Glide;

public class ClearUtil {

    /**
     * 清除缓存
     *
     * @param context
     */
    public static void clearCache(final Context context) {
        clearMemoryCache(context);
        new Thread(new Runnable() {
            @Override
            public void run() {
                clearDiskCache(context);
            }
        }).start();
    }

    /**
     * 清除内存缓存
     *
     * @param context
     */
    public static void clearMemoryCache(Context context) {
        Glide.get(context).clearMemory();
    }

    /**
     * 清除磁盘缓存
     *
     * @param context
     */
    public static void clearDiskCache(Context context) {
        Glide.get(context).clearDiskCache();
    }

}
