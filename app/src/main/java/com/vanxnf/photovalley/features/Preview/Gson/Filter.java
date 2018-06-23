package com.vanxnf.photovalley.features.Preview.Gson;

public class Filter {

    /**
     * id : 1
     * chinese_name : 午后暖阳
     * english_name : sunshine
     * pubdate : 2018-05-18
     * cover_image : app/static/images/filter_cover/sunshine.png
     * VIP_only : false
     */

    private int id;
    private String chinese_name;
    private String english_name;
    private String pubdate;
    private String cover_image;
    private boolean VIP_only;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChinese_name() {
        return chinese_name;
    }

    public void setChinese_name(String chinese_name) {
        this.chinese_name = chinese_name;
    }

    public String getEnglish_name() {
        return english_name;
    }

    public void setEnglish_name(String english_name) {
        this.english_name = english_name;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getCover_image() {
        return cover_image;
    }

    public void setCover_image(String cover_image) {
        this.cover_image = cover_image;
    }

    public boolean isVIP_only() {
        return VIP_only;
    }

    public void setVIP_only(boolean VIP_only) {
        this.VIP_only = VIP_only;
    }
}
