package com.vanxnf.photovalley.features.Home.Entity;

public class SquareItem {

    private String avatarUri;

    private String picUri;

    private String authorName;

    private String description;

    private boolean isMember;

    private boolean isLiked;

    private int likeNum;

    public SquareItem(String avatarUri, String picUri, String authorName) {
        this.avatarUri = avatarUri;
        this.picUri = picUri;
        this.authorName = authorName;
    }

    public SquareItem(String avatarUri, String picUri, String authorName, boolean isMember) {
        this.avatarUri = avatarUri;
        this.picUri = picUri;
        this.authorName = authorName;
        this.isMember = isMember;
    }

    public SquareItem(String avatarUri, String picUri, String authorName, String description, boolean isMember, int likeNum) {
        this.avatarUri = avatarUri;
        this.picUri = picUri;
        this.authorName = authorName;
        this.description = description;
        this.isMember = isMember;
        this.likeNum = likeNum;
    }

    public SquareItem(String avatarUri, String picUri, String authorName, boolean isMember, int likeNum) {
        this.avatarUri = avatarUri;
        this.picUri = picUri;
        this.authorName = authorName;
        this.isMember = isMember;
        this.likeNum = likeNum;
    }

    public SquareItem(String avatarUri, String picUri, String authorName, String description, boolean isMember) {
        this.avatarUri = avatarUri;
        this.picUri = picUri;
        this.authorName = authorName;
        this.description = description;
        this.isMember = isMember;
    }

    public String getAvatarUri() {
        return avatarUri;
    }

    public void setAvatarUri(String avatarUri) {
        this.avatarUri = avatarUri;
    }

    public String getPicUri() {
        return picUri;
    }

    public void setPicUri(String picUri) {
        this.picUri = picUri;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public boolean isMember() {
        return isMember;
    }

    public void setMember(boolean member) {
        isMember = member;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }
}
