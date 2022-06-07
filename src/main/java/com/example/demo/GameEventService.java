package com.example.demo;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.example.data.GameEvent;
import com.example.data.Player;
import com.example.data.PlayerEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameEventService {
    @Autowired    
    private GameEventRepository gameEventRepository;    
    
    @Autowired    
    private PlayerEventRepository playerEventRepository;    

 
    public void create_gameEvent(GameEvent gameEvent)  
    {   
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
        String strDate = dateFormat.format(Calendar.getInstance().getTime()); 
        
        gameEvent.setData(strDate);
        gameEvent.setAdminCheck(false);

        if(gameEvent.getG().getStatus().compareTo("terminado") == 0)
            return;
        
        if(gameEvent.getEvento().compareTo("terminado") != 0)
            gameEvent.getG().setStatus(gameEvent.getEvento());
        
        if(gameEvent.getEvento().compareTo("terminado") == 0){

            if(gameEvent.getG().getGoloCasa() > gameEvent.getG().getGoloFora()){
                gameEvent.getG().getEquipaCasa().setVitorias(1);
                gameEvent.getG().getEquipaFora().setDerrotas(1);
                gameEvent.getG().getEquipaCasa().setPoints(2);
            }
           
            else if(gameEvent.getG().getGoloCasa() < gameEvent.getG().getGoloFora()){
                gameEvent.getG().getEquipaFora().setVitorias(1);
                gameEvent.getG().getEquipaCasa().setDerrotas(1);
                gameEvent.getG().getEquipaFora().setPoints(2);

            }

            else if(gameEvent.getG().getGoloCasa() == gameEvent.getG().getGoloFora()){
                gameEvent.getG().getEquipaFora().setEmpates(1);
                gameEvent.getG().getEquipaCasa().setEmpates(1);
                gameEvent.getG().getEquipaCasa().setPoints(1);
                gameEvent.getG().getEquipaFora().setPoints(1);

            }

            for(Player p : gameEvent.getG().getEquipaCasa().getListPlayers()){
                p.setMedgoals(p.getGoals()/gameEvent.getG().getEquipaCasa().getNumGames());
            }
            
            for(Player p : gameEvent.getG().getEquipaFora().getListPlayers()){
                p.setMedgoals(p.getGoals()/gameEvent.getG().getEquipaFora().getNumGames());
            }

            gameEvent.getG().setStatus(gameEvent.getEvento());
        }
        gameEventRepository.save(gameEvent);
    }


    public List<String> all_events(String name){
        List<PlayerEvent> listPE = new ArrayList<>();
        playerEventRepository.player_event_order(name).forEach(listPE :: add); 
        
        
        List<GameEvent> listGE = new ArrayList<>();
        gameEventRepository.game_event_order(name).forEach(listGE :: add); 


        List<String> allEvents = new ArrayList<String>();
        
        for(int i = 0;i<listGE.size();i++){
            allEvents.add(listGE.get(i).toString());
        }
        
        for(int i = 0;i<listPE.size();i++){
            allEvents.add(listPE.get(i).toString());
        }

        Collections.sort(allEvents);

        return allEvents;
    }


    public List<GameEvent> eventsFalse(String nameGame){
        return gameEventRepository.game_event_order_false(nameGame);
    }

    public void notifications_admin_check(String date){

        GameEvent ge = gameEventRepository.notifications_admin_change(date);
        ge.setAdminCheck(true);
        gameEventRepository.save(ge);

    }
}

