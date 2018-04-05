package com.vanxnf.photovalley.features.Home.Util;

import com.vanxnf.photovalley.features.Home.Entity.FilterItem;
import com.vanxnf.photovalley.features.Home.Entity.RecommendItem;
import com.vanxnf.photovalley.utils.DataUtil;

import java.util.ArrayList;
import java.util.List;

public class ItemUtil {

    public static List<RecommendItem> getRecommendItemData() {
        List<RecommendItem> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(new RecommendItem(DataUtil.getRandomName(), DataUtil.getImageUri(i + 26)));
        }
        return list;
    }

    public static List<FilterItem> getFilterItemData() {
        List<FilterItem> list = new ArrayList<>();
        list.add(new FilterItem(FilterItem.ACTION, FilterItem.ITEM_SPAN_SIZE_MIN));
        list.add(new FilterItem(FilterItem.ACTION, FilterItem.ITEM_SPAN_SIZE_MIN));
        for (int i = 0; i < 3; i++) {
            list.add(new FilterItem(FilterItem.EVENT, FilterItem.ITEM_SPAN_SIZE));
        }
        return list;
    }
}
