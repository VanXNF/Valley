package com.vanxnf.photovalley.features.UserProfile.Entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class ProfileItem implements MultiItemEntity {

    public static final int IMG = 2;
    public static final int IMG_SIZE = 1;
    public static final int TITLE = 1;
    public static final int TITLE_SPAN_SIZE = 2;

    private int itemType;
    private int spanSize;
    private String picUri;

    public ProfileItem(int itemType, int spanSize, String picUri) {
        this.itemType = itemType;
        this.spanSize = spanSize;
        this.picUri = picUri;
    }

    public ProfileItem(int itemType, int spanSize) {
        this.itemType = itemType;
        this.spanSize = spanSize;
    }

    public ProfileItem(String picUri) {
        this.itemType = IMG;
        this.spanSize = IMG_SIZE;
        this.picUri = picUri;
    }

    public int getSpanSize() {
        return spanSize;
    }

    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public String getPicUri() {
        return picUri;
    }

    public void setPicUri(String picUri) {
        this.picUri = picUri;
    }

}
