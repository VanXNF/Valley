package com.vanxnf.photovalley.features.Home.Util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

public class FilterUtil {

    /**编码为 base64*/
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
        byte[] encode = Base64.encode(bytes,Base64.DEFAULT);
        String encodeString = new String(encode);
        return  encodeString;
    }

    public static final String[] FilterName = {
            "oil_painting",
            "golden_time"
    };

    public static String getFilterNameByPosition(int position) {
        return FilterName[position];
    }

}
