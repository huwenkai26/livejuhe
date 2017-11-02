package com.jinhe.juhe.livejuhe.model;

import java.util.List;

public class Platforminfo {


    public String msg;
    public int code;
    public List<DataEntity> data;

    public class DataEntity {

        public String nick;
        public String cache_time;
        public String uid;
        public String img;
        public String site;
        public String is_vip;
        public int online;
        public String title;
        public String roomid;
        public int cid;
        public String play_flv;
        public String playu_url;
    }
}
