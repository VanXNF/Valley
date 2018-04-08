package com.vanxnf.photovalley.features.Home.Entity;

public class CommentItem {

    private String avatarUri;

    private String commentText;

    public CommentItem(String avatarUri, String commentText) {
        this.avatarUri = avatarUri;
        this.commentText = commentText;
    }

    public String getAvatarUri() {
        return avatarUri;
    }

    public void setAvatarUri(String avatarUri) {
        this.avatarUri = avatarUri;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
}
