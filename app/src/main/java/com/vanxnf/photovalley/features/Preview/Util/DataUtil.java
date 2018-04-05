package com.vanxnf.photovalley.features.Preview.Util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Base64;

import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.features.Preview.Entity.FilterPreviewItem;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DataUtil {

    /**
     * 编码为 base64
     */
    public static String encode(String path, String type) {
        //decode to bitmap
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        //convert to byte array
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        if (type.equals("jpeg")) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
        } else if (type.equals("png")) {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
        } else if (type.equals("webp")) {
            bitmap.compress(Bitmap.CompressFormat.WEBP, 100, out);
        } else {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
        }
        byte[] bytes = out.toByteArray();
        //base64 encode
        byte[] encode = Base64.encode(bytes, Base64.DEFAULT);
        String encodeString = new String(encode);
        return encodeString;
    }

    public static List<FilterPreviewItem> getItemData() {
        List<FilterPreviewItem> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            list.add(new FilterPreviewItem(filterPreviewImageIds[i], filterPreviewTextIds[i]));
        }
        return list;
    }

    //滤镜缩略图
    public static Integer[] filterPreviewImageIds = {
            R.drawable.original_image,
            R.drawable.oil_painting,
            R.drawable.golden_time,
            R.drawable.ocean_heart
    };
    //滤镜名称
    public static Integer[] filterPreviewTextIds = {
            R.string.original_image,
            R.string.oil_painting,
            R.string.golden_time,
            R.string.ocean_heart
    };

    public static final String[] FilterName = {
            "original_image",
            "oil_painting",
            "golden_time",
            "ocean_heart"
    };

    public static String getFilterNameByPosition(int position) {
        return FilterName[position];
    }

    public static String changeUriToPathForFileProvider (Uri uri) {
        String[] content = uri.toString().split("/");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < content.length - 1; i++) {
            builder.append(content[i] + "/");
        }
        if (builder.toString().equals("content://com.vanxnf.photovalley.fileProvider/picture/")) {
            String Path = "/storage/emulated/0/Pictures/PhotoValley/" + content[content.length -1 ];
            return Path;
        }
        return null;
    }
}