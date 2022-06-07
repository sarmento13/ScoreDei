package com.example.data;

import java.util.Calendar;
import java.util.Date;
import com.example.data.Game;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class GameEvent {
      
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    
    private String evento;
    private String data;
    private boolean adminCheck;
    

    @ManyToOne
    private Game g;

    public GameEvent(String evento) {
        this.evento = evento;
        this.adminCheck = false;
    }
    
    public GameEvent() {
    }

    public String getEvento() {
        return evento;
    }
    public void setEvento(String evento) {
        this.evento = evento;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

   

    public Game getG() {
        return g;
    }

    public void setG(Game g) {
        this.g = g;
    }

    @Override
    public String toString() {
        if(evento.compareTo("interrompido") == 0)
            return data + " - Jogo Interrompido !";
    
        if(evento.compareTo("iniciado") == 0)
            return data + " - Jogo Iniciado !";
    
        if(evento.compareTo("terminado") == 0)
            return data + " - Jogo Terminado !";

        
        return data + " - Jogo Resumido !";
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
    
    public boolean isAdminCheck() {
        return adminCheck;
    }

    public void setAdminCheck(boolean adminCheck) {
        this.adminCheck = adminCheck;
    }
    
    
}
