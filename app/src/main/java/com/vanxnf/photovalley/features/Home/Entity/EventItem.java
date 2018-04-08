package com.vanxnf.photovalley.features.Home.Entity;


import com.chad.library.adapter.base.entity.MultiItemEntity;

public class EventItem implements MultiItemEntity {
    public static final int TITLE = 1;
    public static final int IMG = 2;
    public static final int ITEM_SPAN_SIZE = 3; //尺寸 Grid中占3个空间
    public static final int ITEM_SPAN_SIZE_MIN = 1; //尺寸 Grid中占1个空间
    private int itemType;
    private int spanSize;
    private String title;
    private String description;
    private String uri;

    public EventItem(int itemType, int spanSize, String uri) {
        this.itemType = itemType;
        this.spanSize = spanSize;
        this.uri = uri;
    }

    public EventItem(int itemType, int spanSize, String title, String description, String uri) {
        this.itemType = itemType;
        this.spanSize = spanSize;
        this.title = title;
        this.description = description;
        this.uri = uri;
    }

    public EventItem(int itemType, int spanSize) {
        this.itemType = itemType;
        this.spanSize = spanSize;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public int getSpanSize() {
        return spanSize;
    }

    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
