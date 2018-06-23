package com.vanxnf.photovalley.features.Home.Gson;

public class Message {

    /**
     * id : 545
     * username : czczcz
     * avatar : https://s1.ax1x.com/2018/06/22/Ppyv6I.jpg
     * like_number : 0
     * message : 123
     * image : http://192.168.4.128:8080/api/download/images/messages/20180622_232222_20150511215820.png
     * status : 0
     */

    private int id;
    private String username;
    private String avatar;
    private int like_number;
    private String message;
    private String image;
    private int status;

    public Message(String username, String avatar, String message, String image) {
        this.username = username;
        this.avatar = avatar;
        this.message = message;
        this.image = image;
        status = 0;
        like_number = 0;
        id = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getLike_number() {
        return like_number;
    }

    public void setLike_number(int like_number) {
        this.like_number = like_number;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
