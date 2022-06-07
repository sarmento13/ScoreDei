package com.example.demo;

import java.util.List;

import com.example.data.PlayerEvent;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PlayerEventRepository extends CrudRepository<PlayerEvent,Integer>{
    @Query("SELECT pe FROM PlayerEvent pe where pe.g.nameGame like %?1 AND pe.adminCheck = true ORDER BY data ASC")
    List<PlayerEvent> player_event_order(String name);

    @Query("SELECT pe FROM PlayerEvent pe where pe.g.nameGame like %?1 AND pe.adminCheck = false ORDER BY data ASC")
    List<PlayerEvent> player_event_order_false(String name);

    @Query("SELECT ge FROM GameEvent ge where ge.adminCheck like false")
    List<PlayerEvent> player_event_order_for_admin();

    @Query("SELECT pe FROM PlayerEvent pe where pe.data like %?1")
    PlayerEvent notifications_admin_change(String data);
}
