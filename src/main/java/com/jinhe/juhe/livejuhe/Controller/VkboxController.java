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
import java.util.List;

@Controller
public class VkboxController {


    private static String url = "http://api.jushiyaoye.com/apiv2/queryZhiBoIndex";
    private static String roomsurl = "http://api.jushiyaoye.com/apiv2/rooms";
    private static String getplayurl = "http://api.jushiyaoye.com/apiv2/getUrl";
    private static String TVurl = "http://api.jushiyaoye.com/apiv2/queryTVList";
    private List<Thread> Threadlist;
    String key = "d6dffc12cff144899cabd1a90df02a6f";
    @Autowired
    private RoomService roomService;
    @RequestMapping(value = "/jushiyaoye/index", method = {RequestMethod.GET})
    @ResponseBody
    ModelMap index() {
        try {
            long timeMillis = System.currentTimeMillis();
            String data = "android1.1.0"+timeMillis;

            String sha256 = HMACSHA256Utils.HMACSHA256(data.getBytes(),key.getBytes()).toLowerCase();
            String encrypt = DESUtil.encrypt("{\"os\":\"android\",\"soft_ver\":\"1.1.0\",\"timestamp\":\""+timeMillis+"\",\"v\":\""+sha256+"\"}", "!ln1j2Z9");
            String encode = URLEncoder.encode(encrypt);
            String post = HttpUtils.sendPost(url, "input=" + "ENd7cCa7cbfQdxkk6GoYkcgZbTY%2BFnyu3Ncu8ETjA9UVotJr0sr%2BW83zKugVodxpjlOZAKje1nxM%0Ac1KfZ4z6HSEkCpYrDSoUQr16LxcnEAm1rNL36n0hfZyIeNzbeku2Ol6PoH2z63ylkXd3RkWL6DVL%0ALBz83X%2B%2BGX%2FzGVNMcHXUIUpvLaRmpw%3D%3D%0A", false);

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


    @RequestMapping(value = "/clear/{id}", method = {RequestMethod.GET})
    @ResponseBody
    ModelMap clearRoom(Integer id) {
        String encrypt = null;
        try {
            long timeMillis = System.currentTimeMillis();
            String data = id + "android1.1.0" + timeMillis;

            String sha256 = HMACSHA256Utils.HMACSHA256(data.getBytes(), key.getBytes()).toLowerCase();
            encrypt = DESUtil.encrypt("{\"liveId\":\"" + id + "\",\"os\":\"android\",\"soft_ver\":\"1.1.0\",\"timestamp\":\"" + timeMillis + "\",\"v\":\"" + sha256 + "\"}", "!ln1j2Z9");
//            encrypt = DESUtil.encrypt("{\"liveId\":\"277\",\"os\":\"android\",\"soft_ver\":\"1.1.0\",\"timestamp\":\"1506925205436\",\"v\":\"13ed7e80d25c2e0179ed55da9f964e471662632912c599341cbdb1c3bdbe6abf\"}", "!ln1j2Z9");
            String encode = URLEncoder.encode(encrypt);

            Threadlist = new ArrayList<>();
            String result = HttpUtils.sendPost(roomsurl, "input=" + encode, false);
            System.out.println(result);
            if (result.isEmpty()) {

                return null;
            }
            Outbean outbean = JSON.parseObject(result, Outbean.class);
            String output = DESUtil.decrypt(outbean.output, "!ln1j2Z9");
            System.out.println(output);
            Vkroombean vkroombean = JSON.parseObject(output, Vkroombean.class);
            List<Room> rooms = roomService.selectaAllbuliveId(id + "");
            List<Room> romverooms =new ArrayList<>();

            if(rooms!=null){
                for (Room room : rooms) {
                    Boolean flag = true;
                    for (Vkroombean.ListEntity listEntity : vkroombean.list) {
                            if(listEntity.roomId.equals(room.getRoomid())){
                                flag =false;
                                break;
                            }
                    }
                    if(flag){
                        romverooms.add(room);
                    }

                }
                for (Room romveroom : romverooms) {
                    roomService.delete(romveroom);
                }

                ReturnUtil.Success("操作成功",roomService.selectaAllbuliveId(id+""),null);

            }
        }catch (Exception e){
            System.out.println("清理数据库失败");
        }


        return null;
    }

    public ModelMap domain(Integer id) {
        String encrypt = null;
        try {
            long timeMillis = System.currentTimeMillis();
            String data = id+"android1.1.0"+timeMillis;

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



                int finalJ = j;

                Room room = new Room();
                room.setAvatar( vkroombean.list.get(finalJ).avatar);
                room.setId( id+vkroombean.list.get(finalJ).roomId);
                room.setLiveid( id+"");
                room.setRoomid( vkroombean.list.get(finalJ).roomId);
                room.setNickname( vkroombean.list.get(finalJ).nickName);
                room.setState(true);
                room.setWatchnum(vkroombean.list.get(finalJ).watchNum+"");
                room.setUpdatedat(System.currentTimeMillis()+"");
                Room selectroom = roomService.seletByid(room);
                Long s =-1l;//间隔时间
                if(selectroom!=null){
                    String updatedat = selectroom.getUpdatedat();
                    long hqtime = Long.parseLong(updatedat);
                    s = (System.currentTimeMillis() - hqtime) / (1000 * 60);
                }
                if(s!=-1&&s<30) {
                    continue;
                }
                Thread.sleep(2002);
                Thread thread = new Thread() {
                    @Override
                    public void run() {

                        String roomId = vkroombean.list.get(finalJ).roomId;
                        String data = id+roomId+"android1.1.0"+timeMillis;



                        String sha256 = HMACSHA256Utils.HMACSHA256(data.getBytes(),key.getBytes()).toLowerCase();
                        String encrypt = null;
                        try {
                            encrypt = DESUtil.encrypt("{\"liveId\":\""+id+"\",\"os\":\"android\",\"roomId\":\""+roomId+"\",\"soft_ver\":\"1.1.0\",\"timestamp\":\""+timeMillis+"\",\"v\":\""+sha256+"\"}", "!ln1j2Z9");
                        } catch (Exception e) {
                            e.printStackTrace();
                            ReturnUtil.Error("加密错误", null, null);
                        }

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
                            room.setPlayurl(playurlBean.url);
                            if(!roomService.updateRoom(room)) {
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


    return  ReturnUtil.Success("操作成功", vkroombean.list,null);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    @RequestMapping(value = "/jushiyaoye/queryTVList", method = {RequestMethod.GET})
    @ResponseBody
    ModelMap queryTVList() {
        try {
            long timeMillis = System.currentTimeMillis();
            String data = "android1.1.0"+timeMillis;

            String sha256 = HMACSHA256Utils.HMACSHA256(data.getBytes(),key.getBytes()).toLowerCase();
            String encrypt = DESUtil.encrypt("{\"os\":\"android\",\"soft_ver\":\"1.1.0\",\"timestamp\":\""+timeMillis+"\",\"v\":\""+sha256+"\"}", "!ln1j2Z9");
            String encode = URLEncoder.encode(encrypt);
            String post = HttpUtils.sendPost(TVurl, "input=" + "ENd7cCa7cbfQdxkk6GoYkcgZbTY%2BFnyu3Ncu8ETjA9UVotJr0sr%2BW83zKugVodxprZxXXBmH2kkt%0AqRoMmhJiVNPd2qDRU%2F7%2BO6OYBkT0PYVtY%2Bz1oMJE%2FaVm9wtEJ6RZTInciu3ymT6gnTKYGTR9hLqm%0A3t22XyeIuj8uI%2FNN%2FfeEgOhdLA4W9Q%3D%3D%0A", false);

            Outbean outbean = JSON.parseObject(post, Outbean.class);
            String output = DESUtil.decrypt(outbean.output, "!ln1j2Z9");
            System.out.println(output);
            VkTvbean vkboxBean = JSON.parseObject(output, VkTvbean.class);
            return  ReturnUtil.Success("操作成功", vkboxBean.list,null);
        } catch (Exception e) {
            e.printStackTrace();
            ReturnUtil.Error("加密错误", null, null);
        }

        return null;
    }



}
