package com.example.demo;

import java.util.List;

import com.example.data.Player;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player,Integer>{
    @Query("select player from Player player ORDER BY goals DESC")
    List<Player> bestPlayer();
}

