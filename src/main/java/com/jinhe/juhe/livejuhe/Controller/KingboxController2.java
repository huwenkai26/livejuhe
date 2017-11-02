package com.jinhe.juhe.livejuhe.Controller;

import com.alibaba.fastjson.JSON;
import com.jinhe.juhe.livejuhe.utils.HttpUtils;
import com.jinhe.juhe.livejuhe.model.Platforminfo;
import com.jinhe.juhe.livejuhe.model.Playinfo;
import com.jinhe.juhe.livejuhe.utils.ReturnUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class KingboxController2 {


    private static String url = "http://pz.haizisou.cn:8099/kingbox/stream";
    private List<Thread> Threadlist;


    @RequestMapping(value = "/livejuhe/{id}", method = {RequestMethod.GET})
    @ResponseBody
    ModelMap livejuhe(@PathVariable(value = "id") Integer id, @RequestParam("ip") String ip, @RequestParam("port") Integer port) {
        return domain(id, ip, port);
    }

    public ModelMap domain(Integer id, String ip, Integer port) {
        Threadlist = new ArrayList<>();
        String result = HttpUtils.httpRequest(url + "/" + id, ip, port);
        System.out.println(result);
        if (result.isEmpty()) {

            return null;
        }
        Platforminfo platforminfo = JSON.parseObject(result, Platforminfo.class);
        if (platforminfo.code != 200) {
            return ReturnUtil.Error("访问错误", null, null);
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


        return ReturnUtil.Success("操作成功", platforminfo.data, null);
    }
}
