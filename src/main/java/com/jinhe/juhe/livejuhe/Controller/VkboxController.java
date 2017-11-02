package com.jinhe.juhe.livejuhe.Controller;

import com.alibaba.fastjson.JSON;
import com.jinhe.juhe.livejuhe.model.*;
import com.jinhe.juhe.livejuhe.utils.DESUtil;
import com.jinhe.juhe.livejuhe.utils.HMACSHA256Utils;
import com.jinhe.juhe.livejuhe.utils.HttpUtils;
import com.jinhe.juhe.livejuhe.utils.ReturnUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Controller
public class VkboxController {


    private static String url = "http://api.jushiyaoye.com/apiv2/queryZhiBoIndex";
    private static String roomsurl = "http://api.jushiyaoye.com/apiv2/rooms";
    private static String getplayurl = "http://api.jushiyaoye.com/apiv2/getUrl";
    private List<Thread> Threadlist;


    @RequestMapping(value = "/jushiyaoye/index", method = {RequestMethod.GET})
    @ResponseBody
    ModelMap index() {
        try {
            long timeMillis = System.currentTimeMillis();
            String encrypt = DESUtil.encrypt("{\"os\":\"android\",\"soft_ver\":\"1.1.0\",\"timestamp\":\"1509592879532\",\"v\":\"342c6708f105b19433f5192b69017a8a672c5dc5620d091c6e91bb50046a6306\"}", "!ln1j2Z9");
            String encode = URLEncoder.encode(encrypt);
            String post = HttpUtils.sendPost(url, "input=" + encode, false);

            Outbean outbean = JSON.parseObject(post, Outbean.class);
            String output = DESUtil.decrypt(outbean.output, "!ln1j2Z9");
            System.out.println(output);
            VkboxBean vkboxBean = JSON.parseObject(output, VkboxBean.class);
            return  ReturnUtil.Success("操作成功", vkboxBean.list,null);
        } catch (Exception e) {
            e.printStackTrace();
            ReturnUtil.Error("加密错误", null, null);
        }

        return null;
    }


    @RequestMapping(value = "/jushiyaoye/{id}", method = {RequestMethod.GET})
    @ResponseBody
    ModelMap jushiyaoye(@PathVariable(value = "id") Integer id) {
        return domain(id);
    }

    public ModelMap domain(Integer id) {
        String encrypt = null;
        try {
            long timeMillis = System.currentTimeMillis();
            String data = id+"android1.1.0"+timeMillis;
            String key = "c2cc2c0f4efc4d3e9e3d4284b957edbf";
            String sha256 = HMACSHA256Utils.HMACSHA256(data.getBytes(),key.getBytes()).toLowerCase();
            encrypt = DESUtil.encrypt("{\"liveId\":\""+id+"\",\"os\":\"android\",\"soft_ver\":\"1.1.0\",\"timestamp\":\""+timeMillis+"\",\"v\":\""+sha256+"\"}", "!ln1j2Z9");
//            encrypt = DESUtil.encrypt("{\"liveId\":\"277\",\"os\":\"android\",\"soft_ver\":\"1.1.0\",\"timestamp\":\"1506925205436\",\"v\":\"13ed7e80d25c2e0179ed55da9f964e471662632912c599341cbdb1c3bdbe6abf\"}", "!ln1j2Z9");
            String encode = URLEncoder.encode(encrypt);

            Threadlist = new ArrayList<>();
            String result = HttpUtils.sendPost(roomsurl ,"input=" + encode, false);
            System.out.println(result);
            if (result.isEmpty()) {

                return null;
            }
            Outbean outbean = JSON.parseObject(result, Outbean.class);
            String output = DESUtil.decrypt(outbean.output, "!ln1j2Z9");
            System.out.println(output);
            Vkroombean vkroombean = JSON.parseObject(output, Vkroombean.class);

            for (int j = 0; j < vkroombean.list.size(); j++) {

                    Thread.sleep(2002);

                int finalJ = j;
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        String roomId = vkroombean.list.get(finalJ).roomId;
                        String data = id+roomId+"android1.1.0"+timeMillis;


                        String key = "c2cc2c0f4efc4d3e9e3d4284b957edbf";
                        String sha256 = HMACSHA256Utils.HMACSHA256(data.getBytes(),key.getBytes()).toLowerCase();
                        String encrypt = null;
                        try {
                            encrypt = DESUtil.encrypt("{\"liveId\":\""+id+"\",\"os\":\"android\",\"roomId\":\""+roomId+"\",\"soft_ver\":\"1.1.0\",\"timestamp\":\""+timeMillis+"\",\"v\":\""+sha256+"\"}", "!ln1j2Z9");
                        } catch (Exception e) {
                            e.printStackTrace();
                            ReturnUtil.Error("加密错误", null, null);
                        }
//            encrypt = DESUtil.encrypt("{\"liveId\":\"277\",\"os\":\"android\",\"soft_ver\":\"1.1.0\",\"timestamp\":\"1506925205436\",\"v\":\"13ed7e80d25c2e0179ed55da9f964e471662632912c599341cbdb1c3bdbe6abf\"}", "!ln1j2Z9");
                        String encode = URLEncoder.encode(encrypt);


                        String result = HttpUtils.sendPost(getplayurl ,"input=" + encode, false);
                        if (result.isEmpty()) {
                            return;
                        }

                        Outbean outbean = JSON.parseObject(result, Outbean.class);
                        String output = null;
                        try {
                            output = DESUtil.decrypt(outbean.output, "!ln1j2Z9");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        System.out.println(output);
                        PlayurlBean playurlBean = JSON.parseObject(output, PlayurlBean.class);
                        if(playurlBean.code==200&&!playurlBean.url.isEmpty()) {
                            vkroombean.list.get(finalJ).playurl = playurlBean.url;
                        }
                        super.run();
                    }
                };
                thread.start();
                Threadlist.add(thread);
            }


            for (int i = 0; i < Threadlist.size(); i++) {
                try {
                    Threadlist.get(i).join();
                } catch (InterruptedException e) {
                    e.printStackTrace();

                }
            }


    return  ReturnUtil.Success("操作成功", vkroombean.list,null);

        } catch (Exception e) {
            e.printStackTrace();
        }







        return null;
    }
}
