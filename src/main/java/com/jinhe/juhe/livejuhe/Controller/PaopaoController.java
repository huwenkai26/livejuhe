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

    private boolean FLAG = true;

    private String timestamp = "";
    private String loginMd5 = "5e10bc67655fa67f15ebf6fd61eecfd2";
    private String loginMd51 = "d48db6a300a1d2a67d257d625b1a33b1";
    private String loginMd52 = "5e10bc67655fa67f15ebf6fd61eecfd2";
    private String sign = "";
    private static Logger logger = Logger.getLogger(PaopaoController.class);

    @Autowired
    private PaopaoRoomService paopaoRoomService;
    private String loginMd53 = "531fb3a20f6c894293e975b58b3d7c6d";


    @RequestMapping(value = "/paopao/getroomlist/{id}", method = {RequestMethod.GET})
    ModelMap findRoombyid(@PathVariable(value = "id") String id) {
        try {
            loginMd5 = loginMd53;
            String timestamp3 = "";
            String sign3 = "";
            Set<Map.Entry<String, Object>> entries = JavaCallJsUtils.JavaCallJsGetGsign(loginMd5);
            for (Map.Entry<String, Object> entry : entries) {
                if (entry.getKey().equals("timestamp")) {
                    timestamp3 = (String) entry.getValue();
                } else {
                    sign3 = (String) entry.getValue();
                }
            }
            String result = HttpUtils.sendPaopaoPost(url + id, "{\"timestamp\":" + timestamp3 + ",\"loginMd5\":\"" + loginMd5 + "\",\"sign\":\"" + sign3 + "\"}", "13528824689");
            if (result.contains("登陆")) {
                login("13528824689");

                entries = JavaCallJsUtils.JavaCallJsGetGsign(loginMd5);
                for (Map.Entry<String, Object> entry : entries) {
                    if (entry.getKey().equals("timestamp")) {
                        timestamp3 = (String) entry.getValue();
                    } else {
                        sign3 = (String) entry.getValue();
                    }
                }

                result = HttpUtils.sendPaopaoPost(url + id, "{\"timestamp\":" + timestamp3 + ",\"loginMd5\":\"" + loginMd5 + "\",\"sign\":\"" + sign3 + "\"}", "13528824689");

            }
            String decrypt = JavaCallJsUtils.JavaCallJsDecrypt(result, loginMd53);
            System.out.println(decrypt);

            PaopaoBasebean paopaoBasebean = JSON.parseObject(decrypt, PaopaoBasebean.class);
            if (paopaoBasebean == null) {
                System.out.println(decrypt);
                System.out.println("json解析错误PaopaoBasebean 稍等一分钟");
                Thread.sleep(60 * 1000);


                return ReturnUtil.Error("json解析错误PaopaoBasebean", null, null);
            }
            PaopaoRoomBean paopaoRoomBean = JSON.parseObject(paopaoBasebean.data, PaopaoRoomBean.class);
            if (paopaoRoomBean == null) {
                System.out.println(paopaoBasebean.data);
                System.out.println("json解析错误paopaoRoomBean 稍等一分钟");
                Thread.sleep(60 * 1000);

                return ReturnUtil.Error("json解析错误paopaoRoomBean", null, null);
            }


            for (PaopaoRoom paopaoRoom : paopaoRoomBean.list) {
                paopaoRoom.setId(id + paopaoRoom.getUid());
                PaopaoRoom selectRoom = paopaoRoomService.findById(paopaoRoom);
                if (selectRoom != null && paopaoRoom.getUid().equals(selectRoom.getUid())) {
                    paopaoRoom.setPlayurl(selectRoom.getPlayurl());
                    System.out.println("查询到一条主播-->" + selectRoom.getPlayurl());

                } else {
                    System.out.println("该主播数据库中没有" + paopaoRoom);
                }

            }

            return ReturnUtil.Success("操作成功", paopaoRoomBean.list, null);
        } catch (Exception e) {
            return ReturnUtil.Error("程序异常", null, null);
        }

    }

    public ModelMap getRoomlist(String id, String user) {
        try {

            if (user.contains("1766")) {
                loginMd5 = loginMd52;
            } else if (user.equals("15806075007")) {
                loginMd5 = loginMd51;
            } else if (user.equals("13528824689")) {
                loginMd5 = loginMd53;
            }
            Set<Map.Entry<String, Object>> entries = JavaCallJsUtils.JavaCallJsGetGsign(loginMd5);
            for (Map.Entry<String, Object> entry : entries) {
                if (entry.getKey().equals("timestamp")) {
                    this.timestamp = (String) entry.getValue();
                } else {
                    this.sign = (String) entry.getValue();
                }
            }
            String result = HttpUtils.sendPaopaoPost(url + id, "{\"timestamp\":" + timestamp + ",\"loginMd5\":\"" + loginMd5 + "\",\"sign\":\"" + sign + "\"}", user);
            if (result.contains("登陆")) {
                login(user);

                entries = JavaCallJsUtils.JavaCallJsGetGsign(loginMd5);
                for (Map.Entry<String, Object> entry : entries) {
                    if (entry.getKey().equals("timestamp")) {
                        this.timestamp = (String) entry.getValue();
                    } else {
                        this.sign = (String) entry.getValue();
                    }
                }

                result = HttpUtils.sendPaopaoPost(url + id, "{\"timestamp\":" + timestamp + ",\"loginMd5\":\"" + loginMd5 + "\",\"sign\":\"" + sign + "\"}", user);

            }
            System.out.println(result);
            String decrypt = JavaCallJsUtils.JavaCallJsDecrypt(result, this.loginMd5);
            System.out.println(decrypt);

            PaopaoBasebean paopaoBasebean = JSON.parseObject(decrypt, PaopaoBasebean.class);
            if (paopaoBasebean == null) {
                System.out.println(decrypt);
                System.out.println("json解析错误PaopaoBasebean 稍等一分钟" + user);
                Thread.sleep(60 * 1000);


                return ReturnUtil.Error("json解析错误PaopaoBasebean", null, null);
            }
            PaopaoRoomBean paopaoRoomBean = JSON.parseObject(paopaoBasebean.data, PaopaoRoomBean.class);
            if (paopaoRoomBean == null) {
                System.out.println(paopaoBasebean.data);
                System.out.println("json解析错误paopaoRoomBean 稍等一分钟" + user);
                Thread.sleep(60 * 1000);

                return ReturnUtil.Error("json解析错误paopaoRoomBean", null, null);
            }

            if (FLAG) {
                Collections.reverse(paopaoRoomBean.list);
                FLAG = false;
            }

            for (PaopaoRoom listEntity : paopaoRoomBean.list) {

                //设置id
                listEntity.setId(id + listEntity.getUid());
                //判断平台属于过滤条件否id.equals("meme") || id.equals("fanguo") || id.equals("yexiu")||id.equals("nanren")||id.equals("tianjiao")||id.equals("rumeng")||id.equals("yemao")||id.equals("qiuqiu")

                PaopaoRoom selectRoom = paopaoRoomService.findById(listEntity);
                if (selectRoom != null && listEntity.getUid().equals(selectRoom.getUid())) {
                    listEntity.setPlayurl(selectRoom.getPlayurl());
                    System.out.println("数据库中存在该主播信息 ...跳过播放连接为-->" + selectRoom.getPlayurl());
                    continue;
                }


                Thread.sleep(1500);


                String roomresult = HttpUtils.sendPaopaoPost(getroompull, "{\"timestamp\":" + timestamp + ",\"loginMd5\":\"" + loginMd5 + "\",\"sign\":\"" + sign + "\",\"channel\":\"" + id + "\",\"room_uid\":\"" + listEntity.getUid() + "\"}", user);
                String jsDecrypt = JavaCallJsUtils.JavaCallJsDecrypt(roomresult, loginMd5);
                System.out.println(jsDecrypt);
                PaopaoRoomUrlBean paopaoRoomUrlBean = JSON.parseObject(jsDecrypt, PaopaoRoomUrlBean.class);
                if (paopaoRoomUrlBean == null) {
                    continue;
                }
                if (paopaoRoomUrlBean != null && paopaoRoomUrlBean.pull != null && !paopaoRoomUrlBean.pull.isEmpty()) {
                    listEntity.setPlayurl(paopaoRoomUrlBean.pull);
                    listEntity.setUpdatetime(DateUtils.getCurrentTime());
                    if (!paopaoRoomService.update(listEntity)) {
                        paopaoRoomService.insert(listEntity);
                    }
                    System.out.println("采集连接" + paopaoRoomUrlBean.pull);
                } else if (paopaoRoomUrlBean.ret == -1) {
                    System.out.println("该主播停播");
                } else {
                    Thread.sleep(60 * 1000);
                    System.out.println("采集主播连接失败 采集频繁稍等几分钟");

                }
            }
            result = HttpUtils.sendPaopaoPost(url + id, "{\"timestamp\":" + timestamp + ",\"loginMd5\":\"" + loginMd5 + "\",\"sign\":\"" + sign + "\"}", user);
            if (result.isEmpty()) {

                System.out.println(result + " 稍等一分钟");
            }
            return ReturnUtil.Success("操作成功", paopaoRoomBean, null);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("程序异常");

        }

        return null;
    }

    //    @RequestMapping(value = "/paopao/login", method = {RequestMethod.GET})
    private void login(String user) {
        if (user.equals("17665381219")) {
            String loginResult = HttpUtils.sendPaopaoPost(loginUrl, "{\"channel\":\"ywt\",\"user\":\"17665381219\",\"password\":\"wen951219\"}", user);
            PaopaoUser paopaoUser = JSON.parseObject(loginResult, PaopaoUser.class);
            this.loginMd5 = paopaoUser.loginMd5;
            this.loginMd52 = paopaoUser.loginMd5;
            System.out.println("重新登录成功" + loginResult);
        } else if (user.equals("15806075007")) {
            String loginResult = HttpUtils.sendPaopaoPost(loginUrl, "{\"channel\":\"ywt\",\"user\":\"15806075007\",\"password\":\"111111\"}", user);
            PaopaoUser paopaoUser = JSON.parseObject(loginResult, PaopaoUser.class);
            if (paopaoUser != null && paopaoUser.ret != 1) {
                this.loginMd5 = paopaoUser.loginMd5;
                this.loginMd51 = paopaoUser.loginMd5;
                System.out.println("重新登录成功" + loginResult);
            }
        } else if (user.equals("13528824689")) {
            String loginResult = HttpUtils.sendPaopaoPost(loginUrl, "{\"channel\":\"ywt\",\"user\":\"13528824689\",\"password\":\"111111\"}", user);
            PaopaoUser paopaoUser = JSON.parseObject(loginResult, PaopaoUser.class);
            if (paopaoUser != null && paopaoUser.ret != 1) {
                this.loginMd5 = paopaoUser.loginMd5;
                this.loginMd53 = paopaoUser.loginMd5;
                System.out.println("重新登录成功" + loginResult);
            }
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
            String result = HttpUtils.sendPaopaoPost(indexurl, "{\"timestamp\":" + timestamp + ",\"loginMd5\":\"" + loginMd5 + "\",\"sign\":\"" + sign + "\"}", null);

            PaopaoIndexBean paopaoIndexBean = JSON.parseObject(result, PaopaoIndexBean.class);
            return ReturnUtil.Success("操作成功", paopaoIndexBean.data, null);
        } catch (Exception e) {
            e.printStackTrace();
            ReturnUtil.Error("加密错误", null, null);
        }

        return null;
    }

    public List<PaopaoIndexBean.DataEntity> domain() {
        try {

            Set<Map.Entry<String, Object>> entries = JavaCallJsUtils.JavaCallJsGetGsign(loginMd5);
            for (Map.Entry<String, Object> entry : entries) {
                if (entry.getKey().equals("timestamp")) {
                    this.timestamp = (String) entry.getValue();
                } else {
                    this.sign = (String) entry.getValue();
                }
            }
            String result = HttpUtils.sendPaopaoPost(indexurl, "{\"timestamp\":" + timestamp + ",\"loginMd5\":\"" + loginMd5 + "\",\"sign\":\"" + sign + "\"}", null);

            PaopaoIndexBean paopaoIndexBean = JSON.parseObject(result, PaopaoIndexBean.class);
            return paopaoIndexBean.data;
        } catch (Exception e) {
            e.printStackTrace();
            ReturnUtil.Error("加密错误", null, null);
        }

        return null;
    }


}
