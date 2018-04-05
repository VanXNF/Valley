package com.vanxnf.photovalley.features.Home.Entity;

public class RecommendItem {

    private String authorName;

    private String pictureUri;

    private boolean isLiked;

    private boolean isDownload;

    public RecommendItem(String authorName, String pictureUri) {
        this.authorName = authorName;
        this.pictureUri = pictureUri;
    }

    public RecommendItem(String authorName, String pictureUri, boolean isLiked) {
        this.authorName = authorName;
        this.pictureUri = pictureUri;
        this.isLiked = isLiked;
    }

    public RecommendItem(String authorName, String pictureUri, boolean isLiked, boolean isDownload) {
        this.authorName = authorName;
        this.pictureUri = pictureUri;
        this.isLiked = isLiked;
        this.isDownload = isDownload;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getPictureUri() {
        return pictureUri;
    }

    public void setPictureUri(String pictureUri) {
        this.pictureUri = pictureUri;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public boolean isDownload() {
        return isDownload;
    }

    public void setDownload(boolean download) {
        isDownload = download;
    }
}
