package com.jinhe.juhe.livejuhe.Task;


import com.jinhe.juhe.livejuhe.Controller.PaopaoController;
import com.jinhe.juhe.livejuhe.mapper.PaopaoRoomDao;
import com.jinhe.juhe.livejuhe.model.PaopaoIndexBean;
import com.jinhe.juhe.livejuhe.model.PaopaoRoom;
import com.jinhe.juhe.livejuhe.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import java.util.Collections;
import java.util.List;

@Component
public class PaopaoJobs {


    @Autowired
    PaopaoRoomDao paopaoRoomDao;


    @Autowired
    PaopaoController paopaoController;


    @Scheduled(fixedDelay = 6000)
    public void PaopaoaddTask() {

        System.out.println("账号17665381219开始采集");
        List<PaopaoIndexBean.DataEntity> domain = paopaoController.domain();
        for (PaopaoIndexBean.DataEntity dataEntity : domain) {
            String channel = dataEntity.channel;
            paopaoController.getRoomlist(channel, "17665381219");
            System.out.println("账号17665381219采集完成一个平台" + channel);
        }
        System.out.println("账号17665381219采集完毕");


    }

    @Scheduled(fixedDelay = 6000)
    public void PaopaoaddTaskbyuser() {

        System.out.println("账号15806075007开始采集");
        List<PaopaoIndexBean.DataEntity> domain = paopaoController.domain();
        Collections.reverse(domain);
        for (PaopaoIndexBean.DataEntity dataEntity : domain) {
            String channel = dataEntity.channel;
            paopaoController.getRoomlist(channel, "15806075007");
            System.out.println("账号15806075007采集完成一个平台" + channel);
        }

        System.out.println("账号15806075007采集完毕");
    }
}
