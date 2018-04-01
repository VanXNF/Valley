package com.vanxnf.photovalley.features.Home.Gson;

import com.google.gson.annotations.SerializedName;

public class Filter {

    @SerializedName("user_name")
    public String userName;

    @SerializedName("filter_name")
    public String filterName;

    @SerializedName("image_width")
    public String imageWidth;

    @SerializedName("image_height")
    public String imageHeight;

    @SerializedName("image_base64")
    public String imageBase64;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFilterName() {
        return filterName;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

    public String getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(String imageWidth) {
        this.imageWidth = imageWidth;
    }

    public String getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(String imageHeight) {
        this.imageHeight = imageHeight;
    }
}
