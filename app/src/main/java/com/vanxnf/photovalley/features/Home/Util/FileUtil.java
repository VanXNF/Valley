package com.vanxnf.photovalley.features.Home.Util;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FileUtil {

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
        String path = new String(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath()
                + "/Camera/" + imageFileName);
        File file = null;
        try {
            file = new File(path);
            file.getParentFile().mkdirs();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

}
