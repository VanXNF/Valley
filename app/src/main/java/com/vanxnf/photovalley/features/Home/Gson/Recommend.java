package com.vanxnf.photovalley.features.Home.Gson;

public class Recommend {

    /**
     * id : 532
     * username : 骚楠皮
     * image : https://s1.ax1x.com/2018/04/13/CViHtP.jpg
     * status : 0
     */
    //0 为不点赞 1为点赞

    private int id;
    private String username;
    private String image;
    private int status;

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
