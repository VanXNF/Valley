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

}
