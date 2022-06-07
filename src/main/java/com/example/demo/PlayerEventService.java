package com.example.demo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import com.example.data.PlayerEvent;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerEventService {
    @Autowired    
    private PlayerEventRepository playerEventRepository;    

 
    public void create_PlayerEvent(PlayerEvent playerEvent)  
    {  
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
        String strDate = dateFormat.format(Calendar.getInstance().getTime()); 
        
        playerEvent.setData(strDate);
        playerEvent.setAdminCheck(false);

        if(playerEvent.getG().getStatus().compareTo("iniciado") == 0 || playerEvent.getG().getStatus().compareTo("resumido") == 0){

            // System.out.println(playerEvent.toString());

                if(playerEvent.getEvento().compareTo("amarelo") == 0)
                playerEvent.getJ().setYellowCards(1);
            
            else if(playerEvent.getEvento().compareTo("vermelho") == 0)
                playerEvent.getJ().setRedCards(1);


            else if(playerEvent.getEvento().compareTo("golo") == 0){
                playerEvent.getJ().setGoals(1);

                if(playerEvent.getJ().getEquipa().getName().compareTo(playerEvent.getG().getEquipaCasa().getName()) == 0)
                    playerEvent.getG().setGoloCasa(1);

                else if(playerEvent.getJ().getEquipa().getName().compareTo(playerEvent.getG().getEquipaFora().getName()) == 0)
                    playerEvent.getG().setGoloFora(1);
            
            }

            playerEventRepository.save(playerEvent);
        }
        
    }

    public List<PlayerEvent> eventsFalse(String nameGame){
        return playerEventRepository.player_event_order_false(nameGame);
    }

    public void notifications_admin_check(String Id){
        PlayerEvent pe = playerEventRepository.notifications_admin_change(Id);
        pe.setAdminCheck(true);
        playerEventRepository.save(pe);
    }
}
