package com.vanxnf.photovalley.utils;



import android.content.Context;

import android.graphics.Color;
import android.net.Uri;
import android.os.Build;

import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

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

    // TODO: 2018/3/11 待实现透明状态栏 
    /**实现透明状态栏*/
    public static void setStatusBarTransparent(Window window) {
//        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//        window.setStatusBarColor(Color.TRANSPARENT);

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);
        window.setNavigationBarColor(Color.TRANSPARENT);
    }

    /**使爆炸后的控件复现*/
    public static void reSetView(View view) {
        view.setScaleX(1);
        view.setScaleY(1);
        view.setAlpha(1.0f);
        view.setVisibility(View.VISIBLE);
    }

    /**获取文件Uri*/
    public static Uri getFileUri(Context context, String authority, File file ) {
        Uri uri;
        if (Build.VERSION.SDK_INT >= 24) {
           uri = FileProvider.getUriForFile(context, authority, file);
            Log.d("Test0", uri.toString());
        } else {
           uri = Uri.fromFile(file);
        }
        return uri;
    }

    /**创建相机图片*/
    public static File createImageFile() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "IMG_" + timeStamp + ".jpg";
        Log.d("Time", imageFileName);
        String path = new String(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath()
              + "/" + imageFileName);
        Log.d("File_Path", path);

        File file = null;
        try {
            file = new File(path);
            file.getParentFile().mkdirs();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    /**判断文件是否为gif*/
    public static boolean isGifFile(File file) {
        try {
            FileInputStream inputStream = new FileInputStream(file);
            int[] flags = new int[5];
            flags[0] = inputStream.read();
            flags[1] = inputStream.read();
            flags[2] = inputStream.read();
            flags[3] = inputStream.read();
            inputStream.skip(inputStream.available() - 1);
            flags[4] = inputStream.read();
            inputStream.close();
            return flags[0] == 71 && flags[1] == 73 && flags[2] == 70 && flags[3] == 56 && flags[4] == 0x3B;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
