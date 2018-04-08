package com.vanxnf.photovalley.features.Home.Util;

import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.features.Home.Entity.FilterItem;
import com.vanxnf.photovalley.features.Home.Entity.RecommendItem;
import com.vanxnf.photovalley.features.Home.Entity.SquareItem;
import com.vanxnf.photovalley.utils.DataUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ItemUtil {

    public static List<SquareItem> getSquareItemData() {
        List<SquareItem> list = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            list.add(new SquareItem(DataUtil.getImageUri(i + 200),
                    DataUtil.getImageUri(i + 100),
                    DataUtil.getRandomName(),
                    false,
                    getRandomNum()));
        }
        return list;
    }

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
        for (int i = 0; i < eventNames.length; i++) {
            list.add(new FilterItem(FilterItem.EVENT, FilterItem.ITEM_SPAN_SIZE, DataUtil.getRandomUri(), eventNames[i]));
        }
        return list;
    }

//    public static List<EventItem> getEventItemData() {
//        List<EventItem> list = new ArrayList<>();
//        return list;
//    }
    public static Integer[] eventNames = {
        R.string.event_spring_story,
        R.string.event_food_spree,
        R.string.event_school_life
};

    public static int getRandomNum() {
        Random rand = new Random();
        return rand.nextInt(1500);
    }
}
