package com.vanxnf.photovalley.features.About.Entity;

public class AboutItem {

    private Integer iconId;

    private Integer titleId;

    private Integer descId;

    public AboutItem(Integer iconId, Integer titleId, Integer descId) {
        this.iconId = iconId;
        this.titleId = titleId;
        this.descId = descId;
    }

    public Integer getIconId() {
        return iconId;
    }

    public void setIconId(Integer iconId) {
        this.iconId = iconId;
    }

    public Integer getTitleId() {
        return titleId;
    }

    public void setTitleId(Integer titleId) {
        this.titleId = titleId;
    }

    public Integer getDescId() {
        return descId;
    }

    public void setDescId(Integer descId) {
        this.descId = descId;
    }
}
