package com.vanxnf.photovalley.features.Home.Entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class FilterItem implements MultiItemEntity {
    public static final int ACTION = 1;
    public static final int EVENT = 2;
    public static final int ITEM_SPAN_SIZE = 2; //尺寸 Grid中占2个空间
    public static final int ITEM_SPAN_SIZE_MIN = 1; //尺寸 Grid中占1个空间
    private int itemType;
    private int spanSize;
    private String content;

    public FilterItem(int itemType, int spanSize, String content) {
        this.itemType = itemType;
        this.spanSize = spanSize;
        this.content = content;
    }

    public FilterItem(int itemType, int spanSize) {
        this.itemType = itemType;
        this.spanSize = spanSize;
    }

    public int getSpanSize() {
        return spanSize;
    }

    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}