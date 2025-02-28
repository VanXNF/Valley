package com.vanxnf.photovalley.features.Preview.Util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

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

//    public static List<FilterPreviewItem> getItemData() {
//        List<FilterPreviewItem> list = new ArrayList<>();
//        for (int i = 0; i < filterPreviewTextIds.length; i++) {
//            list.add(new FilterPreviewItem(filterPreviewImageIds[i], filterPreviewTextIds[i]));
//        }
//        return list;
//    }
//
//    //滤镜缩略图 0为空
//    public static Integer[] filterPreviewImageIds = {
//            0,
//            R.drawable.sunshine,
//            R.drawable.twilight,
//            R.drawable.candy_town,
//            R.drawable.engraving_art,
//            R.drawable.oil_painting,
//            R.drawable.golden_time,
//            R.drawable.nature,
//            R.drawable.ocean_heart,
//            R.drawable.old_photo,
//            R.drawable.wave,
//            R.drawable.cubist,
//            R.drawable.feathers,
//            R.drawable.mosaic,
//            R.drawable.starry_night
//    };
//    //滤镜名称
//    public static Integer[] filterPreviewTextIds = {
//            R.string.original_image,
//            R.string.sunshine,
//            R.string.twilight,
//            R.string.candy_town,
//            R.string.engraving_art,
//            R.string.oil_painting,
//            R.string.golden_time,
//            R.string.nature,
//            R.string.ocean_heart,
//            R.string.old_photo,
//            R.string.wave,
//            R.string.cubist,
//            R.string.feathers,
//            R.string.mosaic,
//            R.string.starry_night
//    };
//
//    public static final String[] FilterName = {
//            "original_image",
//            "sunshine",
//            "twilight",
//            "candy_town",
//            "engraving_art",
//            "oil_painting",
//            "golden_time",
//            "nature",
//            "ocean_heart",
//            "old_photo",
//            "wave",
//            "cubist",
//            "feathers",
//            "mosaic",
//            "starry_night"
//    };
//
//    public static String getFilterNameByPosition(int position) {
//        return FilterName[position];
//    }

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