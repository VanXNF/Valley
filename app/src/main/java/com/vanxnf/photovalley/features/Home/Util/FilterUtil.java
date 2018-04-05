package com.vanxnf.photovalley.features.Home.Util;

import com.vanxnf.photovalley.R;

public class FilterUtil {

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
