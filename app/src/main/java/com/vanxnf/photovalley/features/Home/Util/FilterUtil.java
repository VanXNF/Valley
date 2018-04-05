package com.vanxnf.photovalley.features.Home.Util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.features.Home.Entity.FilterItem;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class FilterUtil {


    public static List<FilterItem> getMultipleItemData() {
        List<FilterItem> list = new ArrayList<>();
        list.add(new FilterItem(FilterItem.ACTION, FilterItem.ITEM_SPAN_SIZE_MIN));
        list.add(new FilterItem(FilterItem.ACTION, FilterItem.ITEM_SPAN_SIZE_MIN));
        for (int i = 0; i < 3; i++) {
            list.add(new FilterItem(FilterItem.EVENT, FilterItem.ITEM_SPAN_SIZE));
        }
        return list;
    }

    public static Integer getActionIconIdByPosition(int position) {
        return ActionIconIds[position];
    }

    public static final Integer[] ActionIconIds = {
            R.drawable.filter_camera,
            R.drawable.filter_album
    };

    public static Integer getActionTextIdByPosition(int position) {
        return ActionTextIds[position];
    }

    public static final Integer[] ActionTextIds = {
            R.string.camera,
            R.string.album
    };

    public static Integer getFilterNameIdByPosition(int position) {
            return FilterNameIds[position];
    }


    public static final Integer[] FilterNameIds = {
            R.string.oil_painting,
            R.string.golden_time,
            R.string.ocean_heart
    };
}
