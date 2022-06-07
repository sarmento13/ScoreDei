package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import com.example.data.Game;
import com.example.data.Team;

import org.springframework.beans.factory.annotation.Autowired;    
import org.springframework.stereotype.Service;

@Service
public class TeamService   
{    
    
    @Autowired    
    private TeamRepository teamRepository;    


 
    public Team create_team(Team t)  
    {   
        
        if(t.getName().isEmpty() || t.getImg().isEmpty())
            return null;
        
        if(get_team(t.getName()) != null)
            return null;
        
        return teamRepository.save(t);
    }

    public List<Team> get_all_teams()  
    {    
        List<Team> listTeams = new ArrayList<Team>();    
        teamRepository.findAll().forEach(listTeams::add);    
        return listTeams;    
    }
    
    
    public Team get_team(String name)  
    {    
        return teamRepository.findByName(name);    
    }

    public List<Team> bestScore(){
        return teamRepository.teamsScore();
    }



}    