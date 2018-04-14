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
                    getRandomUri(),
                    DataUtil.getRandomName(),
                    false,
                    getRandomNum()));
        }
        return list;
    }

    public static List<RecommendItem> getRecommendItemData() {
        List<RecommendItem> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(new RecommendItem(DataUtil.getRandomName(), getRandomUri()));
        }
        return list;
    }

    public static List<FilterItem> getFilterItemData() {
        List<FilterItem> list = new ArrayList<>();
        list.add(new FilterItem(FilterItem.ACTION, FilterItem.ITEM_SPAN_SIZE_MIN, actionBgUris[0]));
        list.add(new FilterItem(FilterItem.ACTION, FilterItem.ITEM_SPAN_SIZE_MIN, actionBgUris[1]));
        for (int i = 0; i < eventNames.length; i++) {
            list.add(new FilterItem(FilterItem.EVENT, FilterItem.ITEM_SPAN_SIZE, EventBgUris[i], eventNames[i]));
        }
        return list;
    }

//    public static List<EventItem> getEventItemData() {
//        List<EventItem> list = new ArrayList<>();
//        return list;
//    }


    public static String[] actionBgUris = {
            "https://s1.ax1x.com/2018/04/13/CEqNuT.png",
            "https://s1.ax1x.com/2018/04/13/CEqUDU.png"
    };
     public static String[] EventBgUris = {
             "https://s1.ax1x.com/2018/04/13/CE29iD.jpg",//Spring
             "https://s1.ax1x.com/2018/04/13/CVCB3F.jpg",
             "https://s1.ax1x.com/2018/04/13/CVC09U.jpg"
     };
    //美食狂欢节
    public static String[] FoodUris = {
            "https://s1.ax1x.com/2018/04/13/CEcycR.jpg",
            "https://s1.ax1x.com/2018/04/13/CEc6j1.jpg",
            "https://s1.ax1x.com/2018/04/13/CEcgnx.jpg",
            "https://s1.ax1x.com/2018/04/13/CEcRHK.jpg",
            "https://s1.ax1x.com/2018/04/13/CEc2B6.jpg",
            "https://s1.ax1x.com/2018/04/13/CEcfAO.jpg",
            "https://s1.ax1x.com/2018/04/13/CEc44e.jpg",
            "https://s1.ax1x.com/2018/04/13/CEchND.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgpgs.jpg",
            "https://s1.ax1x.com/2018/04/13/CEcI9H.jpg",
            "https://s1.ax1x.com/2018/04/13/CEco3d.jpg",
            "https://s1.ax1x.com/2018/04/13/CEcTgA.jpg",
            "https://s1.ax1x.com/2018/04/13/CEc7jI.jpg",
            "https://s1.ax1x.com/2018/04/13/CEcbut.jpg",
            "https://s1.ax1x.com/2018/04/13/CEcqDP.jpg",
            "https://s1.ax1x.com/2018/04/13/CEcLHf.jpg",
            "https://s1.ax1x.com/2018/04/13/CEcjUS.jpg",
            "https://s1.ax1x.com/2018/04/13/CEcXE8.jpg",
            "https://s1.ax1x.com/2018/04/13/CEczCQ.jpg",
            "https://s1.ax1x.com/2018/04/13/CEcv4g.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgS3j.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgPuq.jpg",
            "https://s1.ax1x.com/2018/04/13/CEg9vn.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgiD0.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgFbV.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgAET.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgEUU.jpg",
            "https://s1.ax1x.com/2018/04/13/CEg8UO.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgV5F.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgeC4.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgn29.jpg",
            "https://s1.ax1x.com/2018/04/13/CEguvR.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgMK1.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgQDx.jpg",
            "https://s1.ax1x.com/2018/04/13/CEglb6.jpg",
            "https://s1.ax1x.com/2018/04/13/CEg3VK.jpg"
    };

    //春日物语
    public static String[] springUris = {
            "https://s1.ax1x.com/2018/04/13/CEgG5D.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgYPe.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgHG4.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgN2d.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgUxA.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgdKI.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgDVf.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgwrt.jpg",
            "https://s1.ax1x.com/2018/04/13/CEg0qP.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgra8.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgsIS.jpg",
            "https://s1.ax1x.com/2018/04/13/CEg6Pg.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgg2j.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgcGQ.jpg",
            "https://s1.ax1x.com/2018/04/13/CEg2xs.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgWMn.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgfrq.jpg",
            "https://s1.ax1x.com/2018/04/13/CEghq0.jpg",
            "https://s1.ax1x.com/2018/04/13/CEg5ZV.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgIaT.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgoIU.jpg",
            "https://s1.ax1x.com/2018/04/13/CEg7iF.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgqz9.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgbRJ.jpg",
            "https://s1.ax1x.com/2018/04/13/CE2izd.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgOMR.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgXs1.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgjqx.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgxZ6.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgzdK.jpg",
            "https://s1.ax1x.com/2018/04/13/CE2SIO.jpg",
            "https://s1.ax1x.com/2018/04/13/CE29iD.jpg",
            "https://s1.ax1x.com/2018/04/13/CE2CJe.jpg"
    };
    //多肉联萌
    public static String[] succulentUris = {
            "https://s1.ax1x.com/2018/04/13/CVPiEq.jpg",
            "https://s1.ax1x.com/2018/04/13/CVPpuj.jpg",
            "https://s1.ax1x.com/2018/04/13/CVPCbn.jpg",
            "https://s1.ax1x.com/2018/04/13/CVP9Ds.jpg",
            "https://s1.ax1x.com/2018/04/13/CVPk5V.jpg",
            "https://s1.ax1x.com/2018/04/13/CVPV8U.jpg",
            "https://s1.ax1x.com/2018/04/13/CVPnKJ.jpg",
            "https://s1.ax1x.com/2018/04/13/CVPECT.jpg",
            "https://s1.ax1x.com/2018/04/13/CVPev4.jpg",
            "https://s1.ax1x.com/2018/04/13/CVPlUx.jpg",
            "https://s1.ax1x.com/2018/04/13/CVPKbR.jpg",
            "https://s1.ax1x.com/2018/04/13/CVPJ2D.jpg",
            "https://s1.ax1x.com/2018/04/13/CVP8PK.jpg",
            "https://s1.ax1x.com/2018/04/13/CVPYxe.jpg",
            "https://s1.ax1x.com/2018/04/13/CVPG8O.jpg",
            "https://s1.ax1x.com/2018/04/13/CVPwVI.jpg",
            "https://s1.ax1x.com/2018/04/13/CVPaqA.jpg",
            "https://s1.ax1x.com/2018/04/13/CVPUrd.jpg",
            "https://s1.ax1x.com/2018/04/13/CVPrPf.jpg",
            "https://s1.ax1x.com/2018/04/13/CVPyRS.jpg",
            "https://s1.ax1x.com/2018/04/13/CVP0at.jpg",
            "https://s1.ax1x.com/2018/04/13/CVPBIP.jpg",
            "https://s1.ax1x.com/2018/04/13/CVPsG8.jpg"
    };



    public static Integer[] eventNames = {
        R.string.event_spring_story,
        R.string.event_food_spree,
        R.string.event_succulent
    };

    public static String getRandomUri() {
        Random rand = new Random();
        return uris[rand.nextInt(uris.length)];
    }


    public static int getRandomNum() {
        Random rand = new Random();
        return rand.nextInt(1500);
    }

    public static String[] uris = {
            "https://s1.ax1x.com/2018/04/13/CEcycR.jpg",
            "https://s1.ax1x.com/2018/04/13/CEc6j1.jpg",
            "https://s1.ax1x.com/2018/04/13/CEcgnx.jpg",
            "https://s1.ax1x.com/2018/04/13/CEcRHK.jpg",
            "https://s1.ax1x.com/2018/04/13/CEc2B6.jpg",
            "https://s1.ax1x.com/2018/04/13/CEcfAO.jpg",
            "https://s1.ax1x.com/2018/04/13/CEc44e.jpg",
            "https://s1.ax1x.com/2018/04/13/CEchND.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgpgs.jpg",
            "https://s1.ax1x.com/2018/04/13/CEcI9H.jpg",
            "https://s1.ax1x.com/2018/04/13/CEco3d.jpg",
            "https://s1.ax1x.com/2018/04/13/CEcTgA.jpg",
            "https://s1.ax1x.com/2018/04/13/CEc7jI.jpg",
            "https://s1.ax1x.com/2018/04/13/CEcbut.jpg",
            "https://s1.ax1x.com/2018/04/13/CEcqDP.jpg",
            "https://s1.ax1x.com/2018/04/13/CEcLHf.jpg",
            "https://s1.ax1x.com/2018/04/13/CEcjUS.jpg",
            "https://s1.ax1x.com/2018/04/13/CEcXE8.jpg",
            "https://s1.ax1x.com/2018/04/13/CEczCQ.jpg",
            "https://s1.ax1x.com/2018/04/13/CEcv4g.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgS3j.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgPuq.jpg",
            "https://s1.ax1x.com/2018/04/13/CEg9vn.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgiD0.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgFbV.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgAET.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgEUU.jpg",
            "https://s1.ax1x.com/2018/04/13/CEg8UO.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgV5F.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgeC4.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgn29.jpg",
            "https://s1.ax1x.com/2018/04/13/CEguvR.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgMK1.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgQDx.jpg",
            "https://s1.ax1x.com/2018/04/13/CEglb6.jpg",
            "https://s1.ax1x.com/2018/04/13/CEg3VK.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgG5D.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgYPe.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgHG4.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgN2d.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgUxA.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgdKI.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgDVf.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgwrt.jpg",
            "https://s1.ax1x.com/2018/04/13/CEg0qP.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgra8.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgsIS.jpg",
            "https://s1.ax1x.com/2018/04/13/CEg6Pg.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgg2j.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgcGQ.jpg",
            "https://s1.ax1x.com/2018/04/13/CEg2xs.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgWMn.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgfrq.jpg",
            "https://s1.ax1x.com/2018/04/13/CEghq0.jpg",
            "https://s1.ax1x.com/2018/04/13/CEg5ZV.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgIaT.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgoIU.jpg",
            "https://s1.ax1x.com/2018/04/13/CEg7iF.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgqz9.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgbRJ.jpg",
            "https://s1.ax1x.com/2018/04/13/CE2izd.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgOMR.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgXs1.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgjqx.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgxZ6.jpg",
            "https://s1.ax1x.com/2018/04/13/CEgzdK.jpg",
            "https://s1.ax1x.com/2018/04/13/CE2SIO.jpg",
            "https://s1.ax1x.com/2018/04/13/CE29iD.jpg",
            "https://s1.ax1x.com/2018/04/13/CE2CJe.jpg",
            "https://s1.ax1x.com/2018/04/13/CVPiEq.jpg",
            "https://s1.ax1x.com/2018/04/13/CVPpuj.jpg",
            "https://s1.ax1x.com/2018/04/13/CVPCbn.jpg",
            "https://s1.ax1x.com/2018/04/13/CVP9Ds.jpg",
            "https://s1.ax1x.com/2018/04/13/CVPk5V.jpg",
            "https://s1.ax1x.com/2018/04/13/CVPV8U.jpg",
            "https://s1.ax1x.com/2018/04/13/CVPnKJ.jpg",
            "https://s1.ax1x.com/2018/04/13/CVPECT.jpg",
            "https://s1.ax1x.com/2018/04/13/CVPev4.jpg",
            "https://s1.ax1x.com/2018/04/13/CVPlUx.jpg",
            "https://s1.ax1x.com/2018/04/13/CVPKbR.jpg",
            "https://s1.ax1x.com/2018/04/13/CVPJ2D.jpg",
            "https://s1.ax1x.com/2018/04/13/CVP8PK.jpg",
            "https://s1.ax1x.com/2018/04/13/CVPYxe.jpg",
            "https://s1.ax1x.com/2018/04/13/CVPG8O.jpg",
            "https://s1.ax1x.com/2018/04/13/CVPwVI.jpg",
            "https://s1.ax1x.com/2018/04/13/CVPaqA.jpg",
            "https://s1.ax1x.com/2018/04/13/CVPUrd.jpg",
            "https://s1.ax1x.com/2018/04/13/CVPrPf.jpg",
            "https://s1.ax1x.com/2018/04/13/CVPyRS.jpg",
            "https://s1.ax1x.com/2018/04/13/CVP0at.jpg",
            "https://s1.ax1x.com/2018/04/13/CVPBIP.jpg",
            "https://s1.ax1x.com/2018/04/13/CVPsG8.jpg",
            "https://s1.ax1x.com/2018/04/13/CVPXZR.jpg",
            "https://s1.ax1x.com/2018/04/13/CVPjd1.jpg",
            "https://s1.ax1x.com/2018/04/13/CVPLL9.jpg",
            "https://s1.ax1x.com/2018/04/13/CVPzi6.jpg",
            "https://s1.ax1x.com/2018/04/13/CVPvIx.jpg",
            "https://s1.ax1x.com/2018/04/13/CVi9zD.jpg",
            "https://s1.ax1x.com/2018/04/13/CViSJK.jpg",
            "https://s1.ax1x.com/2018/04/13/CVipRO.jpg",
            "https://s1.ax1x.com/2018/04/13/CViisH.jpg",
            "https://s1.ax1x.com/2018/04/13/CViPQe.jpg",
            "https://s1.ax1x.com/2018/04/13/CViFLd.jpg",
            "https://s1.ax1x.com/2018/04/13/CViAeA.jpg",
            "https://s1.ax1x.com/2018/04/13/CViEdI.jpg",
            "https://s1.ax1x.com/2018/04/13/CVicY6.jpg",
            "https://s1.ax1x.com/2018/04/13/CVigfK.jpg",
            "https://s1.ax1x.com/2018/04/13/CVi6Fx.jpg",
            "https://s1.ax1x.com/2018/04/13/CViRSO.jpg",
            "https://s1.ax1x.com/2018/04/13/CVif6e.jpg",
            "https://s1.ax1x.com/2018/04/13/CVi7kt.jpg",
            "https://s1.ax1x.com/2018/04/13/CViWlD.jpg",
            "https://s1.ax1x.com/2018/04/13/CVihOH.jpg",
            "https://s1.ax1x.com/2018/04/13/CVi5md.jpg",
            "https://s1.ax1x.com/2018/04/13/CViI0A.jpg",
            "https://s1.ax1x.com/2018/04/13/CVioTI.jpg",
            "https://s1.ax1x.com/2018/04/13/CVibff.jpg",
            "https://s1.ax1x.com/2018/04/13/CViHtP.jpg"
    };
}
