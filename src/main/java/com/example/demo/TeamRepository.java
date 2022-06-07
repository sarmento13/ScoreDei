package com.example.demo;

import java.util.List;

import com.example.data.Team;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<Team,Integer>{

    @Query("select team from Team team where team.name like %?1")
    Team findByName(String name);

    @Query("select team from Team team where team.id like %?1")
    Team findId(String id);
    
    @Query("SELECT tc FROM Team tc ORDER BY points DESC")
    List<Team> teamsScore();
    
}
