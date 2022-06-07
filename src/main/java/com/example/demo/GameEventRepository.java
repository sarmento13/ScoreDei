package com.example.demo;


import java.sql.Date;
import java.util.List;

import javax.xml.crypto.Data;

import com.example.data.GameEvent;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface GameEventRepository extends CrudRepository<GameEvent,Integer>{
    @Query("SELECT ge FROM GameEvent ge where ge.g.nameGame like %?1 AND ge.adminCheck = true ORDER BY data ASC")
    List<GameEvent> game_event_order(String name);
    //mudar a query para "SELECT ge FROM GameEvent ge where ge.g.nameGame like %?1 AND ge.adminCheck like true ORDER BY data ASC"


    @Query("SELECT ge FROM GameEvent ge where ge.g.nameGame like %?1 AND ge.adminCheck = false ORDER BY data ASC")
    List<GameEvent> game_event_order_false(String name);

   
    @Query("SELECT ge FROM GameEvent ge where ge.adminCheck like false")
    List<GameEvent> game_event_order_for_admin();
    
    @Query("SELECT ge FROM GameEvent ge where ge.data like %?1")
    GameEvent notifications_admin_change(String data);
}