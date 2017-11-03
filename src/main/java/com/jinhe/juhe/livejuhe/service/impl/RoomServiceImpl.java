package com.jinhe.juhe.livejuhe.service.impl;

import com.jinhe.juhe.livejuhe.mapper.RoomDao;
import com.jinhe.juhe.livejuhe.model.Room;
import com.jinhe.juhe.livejuhe.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;


import java.util.List;


/**
 * 城市业务逻辑实现类
 * <p>
 * Created by bysocket on 07/02/2017.
 */
@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomDao roomDao;

    @Override
    public List<Room> findByupdatedat(String updatedAt) {
        return null;
    }

    @Override
    public Boolean updateRoom(Room room) {
        Room selectroom = null;
        try {
            Example example = new Example(Room.class);
            example.createCriteria().andEqualTo("id", room.getId());
            List<Room> rooms = roomDao.selectByExample(example);
            if(rooms!=null&&rooms.size()!=0){
            selectroom = rooms.get(0);
            }

        }catch (Exception e){
            System.out.println("插入一条房间数据");
        }finally {
            if (selectroom!=null &&selectroom.getPlayurl() != null && !selectroom.getPlayurl().isEmpty()) {
                roomDao.updateByPrimaryKey(room);
                return true;
            } else {
                return false;
            }
        }



    }


    @Override
    public void insertRoom(Room room) {
        roomDao.insert(room);
    }

    @Override
    public void clearRoom() {

    }

    @Override
    public void saveOrUpdate(Room room) {



    }

    @Override
    public Room seletByid(Room room) {
        Room selectroom = null;
        try {
            Example example = new Example(Room.class);
            example.createCriteria().andEqualTo("id", room.getId());
            List<Room> rooms = roomDao.selectByExample(example);
            if(rooms!=null&&rooms.size()!=0){
                 selectroom = rooms.get(0);
                return selectroom;
            }

        } catch (Exception e) {
            return null;
        }
        return selectroom;
    }

    @Override
    public List<Room> selectaAllbuliveId(String id) {
        Example example = new Example(Room.class);
        example.createCriteria().andEqualTo("liveid", id);
        List<Room> rooms = roomDao.selectByExample(example);
        return rooms;
    }

    @Override
    public void delete(Room romveroom) {
        Example example = new Example(Room.class);
        example.createCriteria().andEqualTo("id", romveroom.getId());
        roomDao.deleteByExample(example);
    }


}
