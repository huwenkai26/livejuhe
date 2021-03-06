package com.jinhe.juhe.livejuhe.service.impl;

import com.jinhe.juhe.livejuhe.mapper.PaopaoRoomDao;

import com.jinhe.juhe.livejuhe.model.PaopaoRoom;
import com.jinhe.juhe.livejuhe.model.Room;
import com.jinhe.juhe.livejuhe.service.PaopaoRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class PaopaoRoomServiceImp implements PaopaoRoomService {

    @Autowired
    private PaopaoRoomDao paopaoRoomDao;


    @Override
    public void insert(PaopaoRoom roomEntity) {
        try {
            paopaoRoomDao.insert(roomEntity);
        }catch (Exception e){
            System.out.println(roomEntity);
        }

    }

    @Override
    public PaopaoRoom findById(PaopaoRoom paopaoRoom) {
        PaopaoRoom selectroom = null;
        try {
            Example example = new Example(Room.class);
            example.createCriteria().andEqualTo("id", paopaoRoom.getId());
            List<PaopaoRoom> rooms = paopaoRoomDao.selectByExample(example);
            if (rooms != null && rooms.size() != 0) {
                selectroom = rooms.get(0);
                return selectroom;
            }

        } catch (Exception e) {
            return null;
        }
        return selectroom;
    }

    @Override
    public boolean update(PaopaoRoom paopaoRoom) {
        PaopaoRoom selectroom = null;
        try {
            Example example = new Example(PaopaoRoom.class);
            example.createCriteria().andEqualTo("id", paopaoRoom.getId());
            List<PaopaoRoom> rooms = paopaoRoomDao.selectByExample(example);
            if (rooms != null && rooms.size() != 0) {
                selectroom = rooms.get(0);
            }

        } catch (Exception e) {

        } finally {
            if (selectroom != null && selectroom.getPlayurl() != null && !selectroom.getPlayurl().isEmpty()) {
                paopaoRoomDao.updateByPrimaryKey(paopaoRoom);
                System.out.println("更新一房间信息" + paopaoRoom);
                return true;
            } else {
                return false;
            }
        }

    }
}
