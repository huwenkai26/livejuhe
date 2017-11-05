package com.jinhe.juhe.livejuhe.Task;

import com.alibaba.fastjson.JSON;
import com.jinhe.juhe.livejuhe.Controller.VkboxController;
import com.jinhe.juhe.livejuhe.model.*;
import com.jinhe.juhe.livejuhe.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Component
public class Jobs {
    public final static long ONE_Minute =  10*60 * 1000;
    private static String url = "http://api.jushiyaoye.com/apiv2/queryZhiBoIndex";
    private static String roomsurl = "http://api.jushiyaoye.com/apiv2/rooms";
    private static String getplayurl = "http://api.jushiyaoye.com/apiv2/getUrl";
    private static String TVurl = "http://api.jushiyaoye.com/apiv2/queryTVList";
    private List<Thread> Threadlist;
    String key = "b466e3f02af24a89b23df0e7538ea5fc";


    @Autowired
    private VkboxController vkboxController;
    @Scheduled(fixedDelay=ONE_Minute)
    public void fixedDelayJob(){
        String currentTime = DateUtil.getCurrentTime();
        System.out.println("开启定时任务："+currentTime);
        try {
            long timeMillis = System.currentTimeMillis();
            String data = "android1.1.0"+timeMillis;


            String post = HttpUtils.sendPost(url, "input=" + "ENd7cCa7cbfQdxkk6GoYkcgZbTY%2BFnyu3Ncu8ETjA9UVotJr0sr%2BW83zKugVodxpK8jHvljwmy6X%0AgBGxa9EpJGgcA7PVteEF2hIMPilRdpx0GIjSBPloAAPe01x1GuLLIdhQU5WPI6sYKhHOCYV1VezR%0AuS7ss8XqarH0BbTqgPQXZZsiHQ3LhQ%3D%3D%0A", false, null);

            Outbean outbean = JSON.parseObject(post, Outbean.class);
            String output = DESUtil.decrypt(outbean.output, "!ln1j2Z9");
            System.out.println(output);
            VkboxBean vkboxBean = JSON.parseObject(output, VkboxBean.class);
            List<VkboxBean.ListEntity> list = vkboxBean.list;
            Collections.sort(list, new Comparator<VkboxBean.ListEntity>() {
                @Override
                public int compare(VkboxBean.ListEntity o1, VkboxBean.ListEntity o2) {
                    return o2.nums-o1.nums;
                }
            });
            if(vkboxBean!=null&& list !=null){
                for (VkboxBean.ListEntity listEntity : list) {
                    vkboxController.domain(listEntity.liveid);
                }
            }


            System.out.println("结束定时任务："+currentTime);
        } catch (Exception e) {
            e.printStackTrace();
            ReturnUtil.Error("加密错误", null, null);
        }

        return;
    }



    @Scheduled(fixedDelay=ONE_Minute)
    public void fixedDelayJob2() {
        String currentTime = DateUtil.getCurrentTime();
        System.out.println("开启定时任务2：" + currentTime);

    }


}
