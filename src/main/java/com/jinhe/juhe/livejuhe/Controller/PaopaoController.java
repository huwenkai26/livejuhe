package com.jinhe.juhe.livejuhe.Controller;

import com.alibaba.fastjson.JSON;
import com.jinhe.juhe.livejuhe.model.*;
import com.jinhe.juhe.livejuhe.service.PaopaoRoomService;

import com.jinhe.juhe.livejuhe.utils.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class PaopaoController {


    private static String url = "http://www.8892236.cn:1340/apichannel/getPlayList?channel=";
    private static String indexurl = "http://www.8892236.cn:1340/apichannel/channelList";
    private static String getroompull = "http://www.8892236.cn:1340/apichannel/getroompull";
    private static String loginUrl = "http://www.8892236.cn:1340/apilogin";


    private String timestamp = "";
    private String loginMd5 = "2ff787e7c5f857b61afe0a485423cdd1";
    private String sign = "";
    private static Logger logger = Logger.getLogger(PaopaoController.class);

    @Autowired
    private PaopaoRoomService paopaoRoomService;


    @RequestMapping(value = "/paopao/getroomlist/{id}", method = {RequestMethod.GET})
    ModelMap getRoomlist(@PathVariable(value = "id") String id) {
        try {
            Set<Map.Entry<String, Object>> entries = JavaCallJsUtils.JavaCallJsGetGsign(loginMd5);
            for (Map.Entry<String, Object> entry : entries) {
                if (entry.getKey().equals("timestamp")) {
                    this.timestamp = (String) entry.getValue();
                } else {
                    this.sign = (String) entry.getValue();
                }
            }
            String result = HttpUtils.sendPaopaoPost(url + id, "{\"timestamp\":" + timestamp + ",\"loginMd5\":\"" + loginMd5 + "\",\"sign\":\"" + sign + "\"}");
//            if(result.contains("登陆")){
//                login();
//
//                 entries = JavaCallJsUtils.JavaCallJsGetGsign(loginMd5);
//                for (Map.Entry<String, Object> entry : entries) {
//                    if (entry.getKey().equals("timestamp")) {
//                        this.timestamp = (String) entry.getValue();
//                    } else {
//                        this.sign = (String) entry.getValue();
//                    }
//                }
//
//            result = HttpUtils.sendPaopaoPost(url + id, "{\"timestamp\":" + timestamp + ",\"loginMd5\":\"" + loginMd5 + "\",\"sign\":\"" + sign + "\"}");
//            }
            String decrypt = JavaCallJsUtils.JavaCallJsDecrypt(result,this.loginMd5);
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


                //设置id
                listEntity.setId(id+listEntity.getUid());
                //判断平台属于过滤条件否
                if(id.equals("meme")||id.equals("fanguo")||id.equals("yexiu")){
                    //根据id到数据库查询是否记录
                    PaopaoRoom selectRoom = paopaoRoomService.findById(listEntity);
                    if(selectRoom!=null&&listEntity.getUid().equals(selectRoom.getUid())){
                        System.out.println("数据库中存在改主播信息 ...跳过");
                        continue;
                    }
                }


                String roomresult = HttpUtils.sendPaopaoPost(getroompull, "{\"timestamp\":" + timestamp + ",\"loginMd5\":\"" + loginMd5 + "\",\"sign\":\"" + sign + "\",\"channel\":\"" + id + "\",\"room_uid\":\"" + listEntity.getUid() + "\"}");
                String jsDecrypt = JavaCallJsUtils.JavaCallJsDecrypt(roomresult,loginMd5);
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

    private void login() {
        String loginResult = HttpUtils.sendPaopaoPost(loginUrl, "{\"channel\":\"ywt\",\"user\":\"15806075007\",\"password\":\"111111\"}");
        PaopaoUser paopaoUser = JSON.parseObject(loginResult, PaopaoUser.class);
        if(paopaoUser!=null&&paopaoUser.ret!=1){
            this.loginMd5 =paopaoUser.loginMd5;

            System.out.println("重新登录成功"+loginResult);
        }else {
             loginResult = HttpUtils.sendPaopaoPost(loginUrl, "{\"channel\":\"ywt\",\"user\":\"17665381219\",\"password\":\"wen951219\"}");
             paopaoUser = JSON.parseObject(loginResult, PaopaoUser.class);
            this.loginMd5 =paopaoUser.loginMd5;

            System.out.println("重新登录成功"+loginResult);
        }


    }

    @RequestMapping(value = "/paopao/index", method = {RequestMethod.GET})
    ModelMap index() {
        try {

            Set<Map.Entry<String, Object>> entries = JavaCallJsUtils.JavaCallJsGetGsign(loginMd5);
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
