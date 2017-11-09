package com.jinhe.juhe.livejuhe.service;


import com.jinhe.juhe.livejuhe.model.Room;

import java.util.List;

public interface RoomService {
    List<Room> findByupdatedat (String updatedAt);
    Boolean updateRoom( Room room);
    void insertRoom(Room room);
    void clearRoom();

    Room saveOrUpdate(Room room);

    Room seletByid(Room room);

    List<Room> selectaAllbuliveId(String liveid);

    void delete(Room romveroom);


}
