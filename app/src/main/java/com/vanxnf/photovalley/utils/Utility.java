package com.vanxnf.photovalley.utils;



import android.content.ContentUris;
import android.content.Context;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;

import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.vanxnf.photovalley.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * Created by VanXN on 2018/3/2.
 * 工具类
 */

public class Utility {

    /**状态栏颜色切换*/
    public static void setStatusBar(Window window, int themeTag) {
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= 23) {
            if (themeTag == 0) {
                window.getDecorView().setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                window.setStatusBarColor(Color.parseColor("#FFFFFF"));
            } else {
                window.setStatusBarColor(Color.parseColor("#1e1d1d"));
            }
        } else {
            window.getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            if (themeTag == 0) {
                window.setStatusBarColor(Color.parseColor("#9e9e9e"));
            }
        }

    }

    /**
     * 隐藏状态栏
     */
    public static void hideStatusBar(Window window) {
        //隐藏状态栏
        //定义全屏参数
        int flag= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //设置当前窗体为全屏显示
        window.setFlags(flag, flag);
    }
    /**
     * 显示状态栏
     */
    public static void showStatusBar(Window window) {
        window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    /**
     * 对ImageView着色
     */
    public static void setImageViewColor(ImageView view, int colorResId) {
        Drawable modeDrawable = view.getDrawable().mutate();
        Drawable temp = DrawableCompat.wrap(modeDrawable);
        ColorStateList colorStateList =     ColorStateList.valueOf(view.getResources().getColor(colorResId));
        DrawableCompat.setTintList(temp, colorStateList);
        view.setImageDrawable(temp);
    }
}
