package com.vanxnf.photovalley.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by VanXN on 2018/3/13.
 */

public class Photo {

    public String format;

    @SerializedName("width")
    public String picWidth;

    @SerializedName("height")
    public String picHeight;

    public String filename;

    @SerializedName("id")
    public String picId;

    public String author;

    @SerializedName("author_url")
    public String authorUrl;

    @SerializedName("post_url")
    public String postUrl;

    public String getFormat() {
        return format;
    }

    public String getPicWidth() {
        return picWidth;
    }

    public String getPicHeight() {
        return picHeight;
    }

    public String getFilename() {
        return filename;
    }

    public String getPicId() {
        return picId;
    }

    public String getAuthor() {
        return author;
    }

    public String getAuthorUrl() {
        return authorUrl;
    }

    public String getPostUrl() {
        return postUrl;
    }
}
