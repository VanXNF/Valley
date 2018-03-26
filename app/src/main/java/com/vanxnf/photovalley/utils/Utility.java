package com.vanxnf.photovalley.utils;



import android.content.ContentUris;
import android.content.Context;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.graphics.Color;
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
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
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

    /**获取图片真实路径*/
    public static String handleImage(Intent data, Context context) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(context, uri)) {
            // 如果是document类型的Uri，则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1]; // 解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection, context);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null, context);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // 如果是content类型的Uri，则使用普通方式处理
            imagePath = getImagePath(uri, null, context);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            // 如果是file类型的Uri，直接获取图片路径即可
            imagePath = uri.getPath();
        }
        return imagePath; // 根据图片路径显示图片
    }

    public static String getImagePath(Uri uri, String selection, Context context) {
        String path = null;
        // 通过Uri和selection来获取真实的图片路径
        Cursor cursor = context.getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    /**获取文件Uri*/
    public static Uri getFileUri(Context context, String authority, File file ) {
        Uri uri;
        if (Build.VERSION.SDK_INT >= 24) {
           uri = FileProvider.getUriForFile(context, authority, file);
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

    /**
     * 对ImageView着色
     */
    public static void setImageViewColor(ImageView view, int colorResId) {
        //mutate()
        Drawable modeDrawable = view.getDrawable().mutate();
        Drawable temp = DrawableCompat.wrap(modeDrawable);
        ColorStateList colorStateList =     ColorStateList.valueOf(view.getResources().getColor(colorResId));
        DrawableCompat.setTintList(temp, colorStateList);
        view.setImageDrawable(temp);
    }

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
}
