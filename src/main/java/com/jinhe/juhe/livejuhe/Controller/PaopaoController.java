package com.jinhe.juhe.livejuhe.Controller;

import com.alibaba.fastjson.JSON;
import com.jinhe.juhe.livejuhe.model.*;
import com.jinhe.juhe.livejuhe.service.PaopaoRoomService;
import com.jinhe.juhe.livejuhe.service.RoomService;
import com.jinhe.juhe.livejuhe.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class PaopaoController {


    private static String url = "http://www.8892236.cn:1340/apichannel/getPlayList?channel=";
    private static String indexurl = "http://www.8892236.cn:1340/apichannel/channelList";
    private static String getroompull = "http://www.8892236.cn:1340/apichannel/getroompull";


    private String timestamp = "";
    private String loginMd5 = "b6ad5c3f9a5cb5ed6cb599389fa5a6ce";
    private String sign = "";


    @Autowired
    private PaopaoRoomService paopaoRoomService;


    @RequestMapping(value = "/paopao/getroomlist/{id}", method = {RequestMethod.GET})
    ModelMap getRoomlist(@PathVariable(value = "id") String id) {
        try {
            Set<Map.Entry<String, Object>> entries = JavaCallJsUtils.JavaCallJsGetGsign();
            for (Map.Entry<String, Object> entry : entries) {
                if (entry.getKey().equals("timestamp")) {
                    this.timestamp = (String) entry.getValue();
                } else {
                    this.sign = (String) entry.getValue();
                }
            }
            String result = HttpUtils.sendPaopaoPost(url + id, "{\"timestamp\":" + timestamp + ",\"loginMd5\":\"" + loginMd5 + "\",\"sign\":\"" + sign + "\"}");
            String decrypt = JavaCallJsUtils.JavaCallJsDecrypt(result);
            System.out.println(decrypt);
            PaopaoBasebean paopaoBasebean = JSON.parseObject(decrypt, PaopaoBasebean.class);
            if(paopaoBasebean==null){
                Thread.sleep(60*1000);
                return  ReturnUtil.Error("json解析错误PaopaoBasebean", null, null);
            }
            PaopaoRoomBean paopaoRoomBean = JSON.parseObject(paopaoBasebean.data, PaopaoRoomBean.class);
            if(paopaoRoomBean==null){
                Thread.sleep(60*1000);
                return  ReturnUtil.Error("json解析错误paopaoRoomBean", null, null);
            }
            for (PaopaoRoom listEntity : paopaoRoomBean.list) {
                listEntity.setId(id+listEntity.getUid());
                String roomresult = HttpUtils.sendPaopaoPost(getroompull, "{\"timestamp\":" + timestamp + ",\"loginMd5\":\"" + loginMd5 + "\",\"sign\":\"" + sign + "\",\"channel\":\"" + id + "\",\"room_uid\":\"" + listEntity.getUid() + "\"}");
                String jsDecrypt = JavaCallJsUtils.JavaCallJsDecrypt(roomresult);
                PaopaoRoomUrlBean paopaoRoomUrlBean = JSON.parseObject(jsDecrypt, PaopaoRoomUrlBean.class);
                if (paopaoRoomUrlBean != null && paopaoRoomUrlBean.pull != null && !paopaoRoomUrlBean.pull.isEmpty()) {
                    listEntity.setPlayurl(paopaoRoomUrlBean.pull);

                    paopaoRoomService.insert(listEntity);
                    System.out.println(paopaoRoomUrlBean.pull);
                } else {
                    Thread.sleep(60*1000);
                    System.out.println("采集主播连接失败 采集频繁稍等几分钟");

                }
            }
            return ReturnUtil.Success("操作成功", paopaoRoomBean, null);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("程序异常");

        }

        return null;
    }

    @RequestMapping(value = "/paopao/index", method = {RequestMethod.GET})
    ModelMap index() {
        try {
            Set<Map.Entry<String, Object>> entries = JavaCallJsUtils.JavaCallJsGetGsign();
            for (Map.Entry<String, Object> entry : entries) {
                if (entry.getKey().equals("timestamp")) {
                    this.timestamp = (String) entry.getValue();
                } else {
                    this.sign = (String) entry.getValue();
                }
            }
            String result = HttpUtils.sendPaopaoPost(indexurl, "{\"timestamp\":" + timestamp + ",\"loginMd5\":\"" + loginMd5 + "\",\"sign\":\"" + sign + "\"}");

            PaopaoIndexBean paopaoIndexBean = JSON.parseObject(result, PaopaoIndexBean.class);
            return ReturnUtil.Success("操作成功", paopaoIndexBean.data, null);
        } catch (Exception e) {
            e.printStackTrace();
            ReturnUtil.Error("加密错误", null, null);
        }

        return null;
    }


}
