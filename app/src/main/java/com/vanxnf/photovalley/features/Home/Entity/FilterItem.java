package com.vanxnf.photovalley.features.Home.Entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class FilterItem implements MultiItemEntity {
    public static final int ACTION = 1;
    public static final int EVENT = 2;
    public static final int ITEM_SPAN_SIZE = 2; //尺寸 Grid中占2个空间
    public static final int ITEM_SPAN_SIZE_MIN = 1; //尺寸 Grid中占1个空间
    private int itemType;
    private int spanSize;
    private String uri;
    private Integer eventName;


    public FilterItem(int itemType, int spanSize, String uri, Integer eventName) {
        this.itemType = itemType;
        this.spanSize = spanSize;
        this.uri = uri;
        this.eventName = eventName;
    }

    public FilterItem(int itemType, int spanSize, String uri) {
        this.itemType = itemType;
        this.spanSize = spanSize;
        this.uri = uri;
    }

    public int getSpanSize() {
        return spanSize;
    }

    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Integer getEventName() {
        return eventName;
    }

    public void setEventName(Integer eventName) {
        this.eventName = eventName;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}