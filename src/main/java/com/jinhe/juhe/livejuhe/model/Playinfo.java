package com.jinhe.juhe.livejuhe.model;

public class Playinfo {

    /**
     * msg : 成功
     * code : 200
     * data : {"playu_mp4":"","playu_flv":"","playu_hls":"http://xghls.bxbxg.cn/xgtv/56524_cd34c0e5f31330b731ae/playlist.m3u8&txPlayerId=1509092749","playu_url":"rtmp://xgrtmp.bxbxg.cn/xgtv/56524_cd34c0e5f31330b731ae?t=59f19c16&k=0012396f133f9a95934cb41a03a3593c&txPlayerId=1509092749","playu_rtmp":"rtmp://xgrtmp.bxbxg.cn/xgtv/56524_cd34c0e5f31330b731ae?t=59f19c16&k=0012396f133f9a95934cb41a03a3593c&txPlayerId=1509092749"}
     */
    public String msg;
    public int code;
    public DataEntity data;

    public class DataEntity {
        /**
         * playu_mp4 :
         * playu_flv :
         * playu_hls : http://xghls.bxbxg.cn/xgtv/56524_cd34c0e5f31330b731ae/playlist.m3u8&txPlayerId=1509092749
         * playu_url : rtmp://xgrtmp.bxbxg.cn/xgtv/56524_cd34c0e5f31330b731ae?t=59f19c16&k=0012396f133f9a95934cb41a03a3593c&txPlayerId=1509092749
         * playu_rtmp : rtmp://xgrtmp.bxbxg.cn/xgtv/56524_cd34c0e5f31330b731ae?t=59f19c16&k=0012396f133f9a95934cb41a03a3593c&txPlayerId=1509092749
         */
        public String playu_mp4;
        public String playu_flv;
        public String playu_hls;
        public String playu_url;
        public String playu_rtmp;
    }
}
