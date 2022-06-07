package com.example.demo;

import java.util.List;

import com.example.data.Player;
import com.example.data.Team;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PlayerService {

    @Autowired    
    private PlayerRepository playerRepository; 
    
    @Autowired    
    private TeamRepository teamRepository; 

    public void create_player(Player p)  
    {   
        Team t = teamRepository.findByName(p.getEquipa().getName());
        t.add(p);
        
        playerRepository.save(p);
    }
    
    public void create_player_without_team(Player p,String team)  
    {           
        Team t = teamRepository.findByName(team);

        p.setEquipa(t);
        t.add(p);

        playerRepository.save(p);
    }
    
    

    public List<Player> get_all_players(String name)  
    {   
        Team t = teamRepository.findByName(name);
        return t.getListPlayers();
    }

    public List<Player> bestPlayer(){
        return playerRepository.bestPlayer();
    }


}
