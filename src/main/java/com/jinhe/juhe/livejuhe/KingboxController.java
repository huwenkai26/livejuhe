package com.jinhe.juhe.livejuhe;


import com.alibaba.fastjson.JSON;
import okhttp3.*;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class KingboxController {

    private StringBuilder stringBuilder;
    private static String url = "http://pz.haizisou.cn:8099/kingbox/stream";
    private static OkHttpClient client;
    private static CountDownLatch cdl = null;


    public String domain() {

        List<Thread> Threadlist = new ArrayList<Thread>();
        final StringBuilder stringBuilder = new StringBuilder();


        String response = HttpUtils.httpRequest(url);
        System.out.println(response);
        final IndexBean indexBean = JSON.parseObject(response, IndexBean.class);
        List<IndexBean.DataEntity> list = indexBean.data;
        if (list.size() != 0) {
            final CountDownLatch cdl2 = new CountDownLatch(indexBean.data.size());

            for (int i = 0; i < indexBean.data.size(); i++) {
                int finalI = i;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        String result = HttpUtils.httpRequest(url + "/" + indexBean.data.get(finalI).cid);


                        IndexBean.DataEntity dataEntity = indexBean.data.get(finalI);


                        if (result.isEmpty()) {

                            return;
                        }
                        Platforminfo platforminfo = JSON.parseObject(result, Platforminfo.class);
                        if (platforminfo.code != 200) {
                            return;
                        }
                        for (int j = 0; j < platforminfo.data.size(); j++) {

                            int finalJ = j;
                            Thread thread1 = new Thread() {
                                @Override
                                public void run() {
                                    String result = HttpUtils.httpRequest(url + "/" + dataEntity.cid + "/" + platforminfo.data.get(finalJ).roomid);


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

                                    indexBean.data.get(finalI).roomlist = platforminfo;

                                    System.out.println(finalI+"      "+finalJ);
                                    super.run();
                                }
                            };
                            thread1.start();
                            Threadlist.add(thread1);


                        }


                        super.run();
                    }
                };

                thread.start();
                Threadlist.add(thread);


            }

            try
            {
                for (int i = 0; i < Threadlist.size(); i++) {
                    Threadlist.get(i).join();
                }
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }

            System.out.println("quanbuwanc ");
            return JSON.toJSONString(indexBean);

        }



        return null;
    }

    private String getDecodeString(StringBuilder stringBuilder, IndexBean indexBean) {
        stringBuilder.append(JSON.toJSON(indexBean));
        String replaceAll = stringBuilder.toString().replaceAll("\\\\u003d", "=");
        replaceAll = replaceAll.toString().replaceAll("\\\\u0026", "&");
        System.out.println(replaceAll);
        return replaceAll;
    }


    public Request parseRequestParams(IndexBean.DataEntity listbean) {

        Request request = new Request.Builder().url(url + "/" + listbean.cid).build();
        return request;
    }

}
