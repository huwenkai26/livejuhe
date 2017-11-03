package com.jinhe.juhe.livejuhe.model;

public class Room {
    private String id;
    private String roomid;
    private String liveid;
    private String watchnum;
    private String nickname;
    private Boolean state;
    private String updatedat;
    private String playurl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoomid() {
        return roomid;
    }

    public void setRoomid(String roomid) {
        this.roomid = roomid;
    }

    public String getLiveid() {
        return liveid;
    }

    public void setLiveid(String liveid) {
        this.liveid = liveid;
    }

    public String getWatchnum() {
        return watchnum;
    }

    public void setWatchnum(String watchnum) {
        this.watchnum = watchnum;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public String getUpdatedat() {
        return updatedat;
    }

    public void setUpdatedat(String updatedat) {
        this.updatedat = updatedat;
    }

    public String getPlayurl() {
        return playurl;
    }

    public void setPlayurl(String playurl) {
        this.playurl = playurl;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    private String avatar;

}
