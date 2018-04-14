package com.vanxnf.photovalley.features.Collection.Util;

import com.vanxnf.photovalley.features.Collection.Entity.CollectionItem;
import com.vanxnf.photovalley.utils.DataUtil;

import java.util.ArrayList;
import java.util.List;

public class ItemUtil {

    public static List<CollectionItem> getCollectionItemData() {
        List<CollectionItem> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(new CollectionItem(DataUtil.getRandomUri()));
        }
        return list;
    }
}
