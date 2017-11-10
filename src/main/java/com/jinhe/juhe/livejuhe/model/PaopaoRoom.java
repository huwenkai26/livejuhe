package com.jinhe.juhe.livejuhe.model;

public class PaopaoRoom {


    private String id;
    private String uid;

    private String city;

    private String user_nicename;

    private String avatar;
    private String title;
    private String nums;
    private String playurl;

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    private String updatetime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public String getUser_nicename() {
        return user_nicename;
    }

    public void setUser_nicename(String user_nicename) {
        this.user_nicename = user_nicename;
    }


    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNums() {
        return nums;
    }

    public void setNums(String nums) {
        this.nums = nums;
    }

    public String getPlayurl() {
        return playurl;
    }

    public void setPlayurl(String playurl) {
        this.playurl = playurl;
    }

    @Override
    public String toString() {
        return "PaopaoRoom{" +
                "id='" + id + '\'' +
                ", uid='" + uid + '\'' +
                ", city='" + city + '\'' +
                ", title='" + title + '\'' +
                ", playurl='" + playurl + '\'' +
                ", updatetime='" + updatetime + '\'' +
                '}';
    }
}
