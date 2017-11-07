package com.jinhe.juhe.livejuhe.service.impl;

import com.jinhe.juhe.livejuhe.mapper.PaopaoRoomDao;
import com.jinhe.juhe.livejuhe.mapper.RoomDao;
import com.jinhe.juhe.livejuhe.model.PaopaoRoom;
import com.jinhe.juhe.livejuhe.service.PaopaoRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaopaoRoomServiceImp implements PaopaoRoomService{

    @Autowired
    private PaopaoRoomDao paopaoRoomDao;


    @Override
    public void insert(PaopaoRoom roomEntity) {
        paopaoRoomDao.insert(roomEntity);
    }
}
