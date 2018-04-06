package com.vanxnf.photovalley.features.Home.Entity;

public class SquareItem {

    private String avatarUri;

    private String picUri;

    private String authorName;

    private boolean isMember;

    private boolean isLiked;

    private boolean isCommented;

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

    public boolean isCommented() {
        return isCommented;
    }

    public void setCommented(boolean commented) {
        isCommented = commented;
    }
}
