package com.jinhe.juhe.livejuhe.service;


import com.jinhe.juhe.livejuhe.model.PaopaoRoom;

import java.util.List;

public interface PaopaoRoomService {
    void insert(PaopaoRoom paopaoRoom);

    PaopaoRoom findById(PaopaoRoom listEntity);

    boolean update(PaopaoRoom listEntity);

//
//    List<PaopaoRoom>
}
