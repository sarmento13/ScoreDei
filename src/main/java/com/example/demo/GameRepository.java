package com.example.demo;

import java.util.List;

import com.example.data.Game;
import com.example.data.Team;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game,Integer>{

    @Query("select game from Game game where game.status like %?1")
    List<Game> findGameStats(String state);
    
    @Query("select game from Game game where game.nameGame like %?1")
    Game findGameName(String nameGame);
    
    @Query("select game from Game game where game.nameGame like %?1")
    List<Game> findGameNameList(String nameGame);
    
    
    @Query("SELECT game FROM Game game ORDER BY status DESC")
    List<Game> findByOrder();
    
    @Query("SELECT game FROM Game game where game.status like %?1")
    List<Game> findByGamesInit(String gameStatus);
    
    @Query("SELECT game FROM Game game where game.equipaCasa.name like %?1 and game.equipaFora.name like %?2 OR game.equipaCasa.name like %?2 and game.equipaFora.name like %?1")
    List<Game> findTeamvsTeam(String teamHome,String teamAway);
    
}
