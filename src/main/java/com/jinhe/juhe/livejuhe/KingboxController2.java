package com.jinhe.juhe.livejuhe;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

public class KingboxController2 {
    private static String url = "http://pz.haizisou.cn:8099/kingbox/stream";
    private List<Thread> Threadlist;

    public Platforminfo domain(Integer id, String ip, Integer port) {
                    Threadlist = new ArrayList<>();
                    String result = HttpUtils.httpRequest(url + "/" +id,ip,port);
        System.out.println(result);
                    if (result.isEmpty()) {

                        return null ;
                    }
                    Platforminfo platforminfo = JSON.parseObject(result, Platforminfo.class);
                    if (platforminfo.code != 200) {
                        System.out.println("返回错误");
                        return null;
                    }

                    for (int j = 0; j < platforminfo.data.size(); j++) {

                        int finalJ = j;
                        Thread thread1 = new Thread() {
                            @Override
                            public void run() {
                                String result = HttpUtils.httpRequest(url + "/" + id + "/" + platforminfo.data.get(finalJ).roomid, ip, port);


                                if (result.isEmpty()) {

                                    return;
                                }
                                Playinfo playinfo = JSON.parseObject(result, Playinfo.class);
                                Playinfo.DataEntity data = playinfo.data;

                                if (data.playu_flv != null && !data.playu_flv.isEmpty()) {
                                    platforminfo.data.get(finalJ).playu_url = data.playu_flv;
                                }
                                if (data.playu_mp4 != null && !data.playu_mp4.isEmpty()) {
                                    platforminfo.data.get(finalJ).playu_url = data.playu_flv;
                                }
                                if (data.playu_rtmp != null && !data.playu_rtmp.isEmpty()) {
                                    platforminfo.data.get(finalJ).playu_url = data.playu_rtmp;
                                }
                                if (data.playu_url != null && !data.playu_url.isEmpty()) {
                                    platforminfo.data.get(finalJ).playu_url = data.playu_url;
                                }




                                super.run();
                            }
                        };
                        thread1.start();
                        Threadlist.add(thread1);


                    }


            for (int i = 0; i < Threadlist.size(); i++) {
                try {
                    Threadlist.get(i).join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }




        return platforminfo;
    }
}
