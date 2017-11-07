package com.jinhe.juhe.livejuhe.Controller;

import com.alibaba.fastjson.JSON;
import com.jinhe.juhe.livejuhe.model.*;
import com.jinhe.juhe.livejuhe.service.RoomService;
import com.jinhe.juhe.livejuhe.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class VkboxController {


    private static String url = "http://api.jushiyaoye.com/apiv2/queryZhiBoIndex";
    private static String roomsurl = "http://api.jushiyaoye.com/apiv2/rooms";
    private static String getplayurl = "http://api.jushiyaoye.com/apiv2/getUrl";
    private static String TVurl = "http://api.jushiyaoye.com/apiv2/queryTVList";
    private List<Thread> Threadlist;
    private String key = "ce788cb3c9574d4e88b8003ba9e9614e";


    @Autowired
    private RoomService roomService;

    @RequestMapping(value = "/jushiyaoye/index", method = {RequestMethod.GET})
    @ResponseBody
    ModelMap index() {
        try {
            long timeMillis = System.currentTimeMillis();
            String data = "android1.1.0" + timeMillis;


            String post = HttpUtils.sendPost(url, "input=" + "ENd7cCa7cbfQdxkk6GoYkcgZbTY%2BFnyu3Ncu8ETjA9UVotJr0sr%2BW83zKugVodxpuVLkMBijnOrz%0As5bn7jD82FVg81aYD2uMOTVcsy7gVPruRNqm%2FDkyhfoAhBJpaLHCJNGKTx5Gdk2z98Or2a%2BWET%2BO%0AUNSpsijFV1H3VZ59cWJr%2FDlZ36cVOQ%3D%3D%0A", false, null);

            Outbean outbean = JSON.parseObject(post, Outbean.class);
            String output = DESUtil.decrypt(outbean.output, "!ln1j2Z9");
            System.out.println(output);
            VkboxBean vkboxBean = JSON.parseObject(output, VkboxBean.class);
            return ReturnUtil.Success("操作成功", vkboxBean.list, null);
        } catch (Exception e) {
            e.printStackTrace();
            ReturnUtil.Error("加密错误", null, null);
        }

        return null;
    }

    @RequestMapping(value = "/jushiyaoye/{id}", method = {RequestMethod.GET})
    @ResponseBody
    ModelMap jushiyaoye(@PathVariable(value = "id") Integer id) {

        return domain(id + "");
    }


    @RequestMapping(value = "/clear/{id}", method = {RequestMethod.GET})
    @ResponseBody
    ModelMap clearRoom(@PathVariable(value = "id") Integer id) {
        String encrypt = null;
        try {
            long timeMillis = System.currentTimeMillis();
            String data = id + "android1.1.0" + timeMillis;

            String sha256 = HMACSHA256Utils.HMACSHA256(data.getBytes(), key.getBytes()).toLowerCase();
            encrypt = DESUtil.encrypt("{\"liveId\":\"" + id + "\",\"os\":\"android\",\"soft_ver\":\"1.1.0\",\"timestamp\":\"" + timeMillis + "\",\"v\":\"" + sha256 + "\"}", "!ln1j2Z9");
//            encrypt = DESUtil.encrypt("{\"liveId\":\"277\",\"os\":\"android\",\"soft_ver\":\"1.1.0\",\"timestamp\":\"1506925205436\",\"v\":\"13ed7e80d25c2e0179ed55da9f964e471662632912c599341cbdb1c3bdbe6abf\"}", "!ln1j2Z9");
            String encode = URLEncoder.encode(encrypt);

            Threadlist = new ArrayList<>();
            String result = HttpUtils.sendPost(roomsurl, "input=" + encode, false, null);
            System.out.println(result);
            if (result.isEmpty()) {

                return null;
            }
            Outbean outbean = JSON.parseObject(result, Outbean.class);
            String output = DESUtil.decrypt(outbean.output, "!ln1j2Z9");
            System.out.println(output);
            Vkroombean vkroombean = JSON.parseObject(output, Vkroombean.class);
            List<Room> rooms = roomService.selectaAllbuliveId(id + "");
            List<Room> romverooms = new ArrayList<>();

            if (rooms != null) {
                for (Room room : rooms) {
                    Boolean flag = true;
                    for (Vkroombean.ListEntity listEntity : vkroombean.list) {
                        if (listEntity.roomId.equals(room.getRoomid())) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        romverooms.add(room);

                    }

                }
                for (Room romveroom : romverooms) {
                    roomService.delete(romveroom);
                }


            }
            List<Room> list = roomService.selectaAllbuliveId(id + "");
            return ReturnUtil.Success("操作成功", list, null);
        } catch (Exception e) {
            System.out.println("清理数据库失败");
            return ReturnUtil.Error("操作失败", null, null);
        }


    }

    public ModelMap domain(String id) {
        String encrypt = null;
        try {
            long timeMillis = System.currentTimeMillis();
            String data = id + "android1.1.0" + timeMillis;

            String sha256 = HMACSHA256Utils.HMACSHA256(data.getBytes(), key.getBytes()).toLowerCase();
            encrypt = DESUtil.encrypt("{\"liveId\":\"" + id + "\",\"os\":\"android\",\"soft_ver\":\"1.1.0\",\"timestamp\":\"" + timeMillis + "\",\"v\":\"" + sha256 + "\"}", "!ln1j2Z9");
//            encrypt = DESUtil.encrypt("{\"liveId\":\"277\",\"os\":\"android\",\"soft_ver\":\"1.1.0\",\"timestamp\":\"1506925205436\",\"v\":\"13ed7e80d25c2e0179ed55da9f964e471662632912c599341cbdb1c3bdbe6abf\"}", "!ln1j2Z9");
            String encode = URLEncoder.encode(encrypt);

            Threadlist = new ArrayList<>();
            String result = HttpUtils.sendPost(roomsurl, "input=" + encode, false, null);
            System.out.println(result);
            if (result.isEmpty()) {

                return null;
            }
            Outbean outbean = JSON.parseObject(result, Outbean.class);
            String output = DESUtil.decrypt(outbean.output, "!ln1j2Z9");
            System.out.println(output);
            Vkroombean vkroombean = JSON.parseObject(output, Vkroombean.class);

            for (int j = 0; j < vkroombean.list.size(); j++) {
                int finalJ = j;

                ArrayList<Map> users = new ArrayList<>();

                Map usermap = new HashMap<String,String>();

                String accesstoken = "d4678f6023c762fb262a9010a636a2fd8e8318233129f6270542535d94b0acac";
                String uuid = "31a1a17f-4007-42bd-b07f-c1cd1c64da8b";
                String key = "93b4e0bd231a4a26ad004ec901572637";
                usermap.put("key", key);
                usermap.put("accesstoken", accesstoken);
                usermap.put("uuid", uuid);
                Map usermap2 = new HashMap();
                String key2 = "9fb7084384e84c4596684a866e870d7d";
                String accesstoken2 = "1f80f37ddee8c88f381ee624c845f10c7ba5e10c2befee1d5c3c539a2c420175";
                String uuid2 = "e363fa42-4bf8-4b98-a466-f683210da95f";
                usermap2.put("key", key2);
                usermap2.put("accesstoken", accesstoken2);
                usermap2.put("uuid", uuid2);

                Map usermap3 = new HashMap();
                String key3= "222b65c82731456faa61c31c0df0cfa1";
                String accesstoken3 = "9ef400b890c020601598a9c0140ad5f660b223b10d3db0fa769eea7d7c52e242";
                String uuid3 = "ab481221-7327-466f-bcd7-fa2fe0e36ae1";
                usermap3.put("key", key3);
                usermap3.put("accesstoken", accesstoken3);
                usermap3.put("uuid", uuid3);

                users.add(usermap);
                users.add(usermap2);
                users.add(usermap3);





                Room room = new Room();
                room.setAvatar(vkroombean.list.get(finalJ).avatar);
                room.setId(id + vkroombean.list.get(finalJ).roomId);
                room.setLiveid(id + "");
                room.setRoomid(vkroombean.list.get(finalJ).roomId);
                room.setNickname(vkroombean.list.get(finalJ).nickName);
                room.setState(true);
                room.setWatchnum(vkroombean.list.get(finalJ).watchNum + "");
                room.setUpdatedat(System.currentTimeMillis() + "");
                Room selectroom = roomService.seletByid(room);
                Long s = -1l;//间隔时间
                if (selectroom != null) {
                    String updatedat = selectroom.getUpdatedat();
                    long hqtime = Long.parseLong(updatedat);
                    s = (System.currentTimeMillis() - hqtime) / (1000 * 60);
                }
//                if(selectroom.getLiveid()==)
                if (s != -1 && s < 10) {
                    continue;
                }
                Thread.sleep(700);
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        Map map = users.get(finalJ % 3);

                        String roomId = vkroombean.list.get(finalJ).roomId;
                        String data = id + roomId + "android1.1.0" + timeMillis;


                        String sha256 = HMACSHA256Utils.HMACSHA256(data.getBytes(),((String) map.get("key")).getBytes()).toLowerCase();
                        String encrypt = null;
                        try {
                            encrypt = DESUtil.encrypt("{\"liveId\":\"" + id + "\",\"os\":\"android\",\"roomId\":\"" + roomId + "\",\"soft_ver\":\"1.1.0\",\"timestamp\":\"" + timeMillis + "\",\"v\":\"" + sha256 + "\"}", "!ln1j2Z9");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        String encode = URLEncoder.encode(encrypt);

                        String result = HttpUtils.sendPost(getplayurl, "input=" + encode, false,map);
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
                        System.out.println(output+map);
                        PlayurlBean playurlBean = JSON.parseObject(output, PlayurlBean.class);
                        if (playurlBean.code == 200 && !playurlBean.url.isEmpty()) {
                            vkroombean.list.get(finalJ).playurl = playurlBean.url;
                            room.setPlayurl(playurlBean.url);
                            if (!roomService.updateRoom(room)) {
                                roomService.insertRoom(room);
                            }
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


            return ReturnUtil.Success("操作成功", vkroombean.list, null);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @RequestMapping(value = "/getrooms/{id}", method = {RequestMethod.GET})
    @ResponseBody
    ModelMap getrooms(@PathVariable(value = "id") Integer id) {
        String encrypt = null;
        try {
            long timeMillis = System.currentTimeMillis();
            String data = id + "android1.1.0" + timeMillis;

            String sha256 = HMACSHA256Utils.HMACSHA256(data.getBytes(), key.getBytes()).toLowerCase();
            encrypt = DESUtil.encrypt("{\"liveId\":\"" + id + "\",\"os\":\"android\",\"soft_ver\":\"1.1.0\",\"timestamp\":\"" + timeMillis + "\",\"v\":\"" + sha256 + "\"}", "!ln1j2Z9");
//            encrypt = DESUtil.encrypt("{\"liveId\":\"277\",\"os\":\"android\",\"soft_ver\":\"1.1.0\",\"timestamp\":\"1506925205436\",\"v\":\"13ed7e80d25c2e0179ed55da9f964e471662632912c599341cbdb1c3bdbe6abf\"}", "!ln1j2Z9");
            String encode = URLEncoder.encode(encrypt);

            String result = HttpUtils.sendPost(roomsurl, "input=" + encode, false, null);
            System.out.println(result);
            if (result.isEmpty()) {

                return null;
            }
            Outbean outbean = JSON.parseObject(result, Outbean.class);
            String output = DESUtil.decrypt(outbean.output, "!ln1j2Z9");
            System.out.println(output);
            Vkroombean vkroombean = JSON.parseObject(output, Vkroombean.class);
            return ReturnUtil.Success("ok",vkroombean.list,null);

        }catch (Exception e){

        }
        return null;
    }


    @RequestMapping(value = "/getplayurl/{liveid}}", method = {RequestMethod.GET})
    @ResponseBody
    ModelMap getplayURl(@PathVariable(value = "liveid") Integer liveid,String roomid) {
        long timeMillis = System.currentTimeMillis();
        String data = liveid + roomid + "android1.1.0" + timeMillis;

        String sha256 = HMACSHA256Utils.HMACSHA256(data.getBytes(),key.getBytes()).toLowerCase();
        String encrypt = null;
        try {
            encrypt = DESUtil.encrypt("{\"liveId\":\"" + liveid + "\",\"os\":\"android\",\"roomId\":\"" + roomid + "\",\"soft_ver\":\"1.1.0\",\"timestamp\":\"" + timeMillis + "\",\"v\":\"" + sha256 + "\"}", "!ln1j2Z9");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String encode = URLEncoder.encode(encrypt);

        String result = HttpUtils.sendPost(getplayurl, "input=" + encode, false,null);
        if (result.isEmpty()) {
            return null;
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

        return ReturnUtil.Success("OK",playurlBean,null);
    }


    @RequestMapping(value = "/jushiyaoye/queryTVList", method = {RequestMethod.GET})
    @ResponseBody
    ModelMap queryTVList() {
        try {
            long timeMillis = System.currentTimeMillis();
            String data = "android1.1.0" + timeMillis;

            String sha256 = HMACSHA256Utils.HMACSHA256(data.getBytes(), key.getBytes()).toLowerCase();
            String encrypt = DESUtil.encrypt("{\"os\":\"android\",\"soft_ver\":\"1.1.0\",\"timestamp\":\"" + timeMillis + "\",\"v\":\"" + sha256 + "\"}", "!ln1j2Z9");
            String encode = URLEncoder.encode(encrypt);
            String post = HttpUtils.sendPost(TVurl, "input=" + "ENd7cCa7cbfQdxkk6GoYkcgZbTY%2BFnyu3Ncu8ETjA9UVotJr0sr%2BW83zKugVodxprZxXXBmH2kkt%0AqRoMmhJiVNPd2qDRU%2F7%2BO6OYBkT0PYVtY%2Bz1oMJE%2FaVm9wtEJ6RZTInciu3ymT6gnTKYGTR9hLqm%0A3t22XyeIuj8uI%2FNN%2FfeEgOhdLA4W9Q%3D%3D%0A", false, null);

            Outbean outbean = JSON.parseObject(post, Outbean.class);
            String output = DESUtil.decrypt(outbean.output, "!ln1j2Z9");
            System.out.println(output);
            VkTvbean vkboxBean = JSON.parseObject(output, VkTvbean.class);
            return ReturnUtil.Success("操作成功", vkboxBean.list, null);
        } catch (Exception e) {
            e.printStackTrace();
            ReturnUtil.Error("加密错误", null, null);
        }

        return null;
    }


}
