package com.vanxnf.photovalley.features.Setting.Entity;

public class SettingItem {

    private Integer iconId;

    private Integer textId;

    public SettingItem(Integer iconId, Integer textId) {
        this.iconId = iconId;
        this.textId = textId;
    }

    public Integer getIconId() {
        return iconId;
    }

    public void setIconId(Integer iconId) {
        this.iconId = iconId;
    }

    public Integer getTextId() {
        return textId;
    }

    public void setTextId(Integer textId) {
        this.textId = textId;
    }
}
