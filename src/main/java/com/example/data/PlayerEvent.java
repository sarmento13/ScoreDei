package com.example.data;

import java.util.Calendar;
import java.util.Date;
import com.example.data.Game;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class PlayerEvent {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    private String evento;
    private String data;
    private boolean adminCheck;

 

    @ManyToOne
    private Game g;

    @ManyToOne
    private Player j;


    public PlayerEvent(Player j, String evento, Date data) {
        this.j = j;
        this.evento = evento;
        this.adminCheck = false;
    }
    
    public PlayerEvent() {
    }


    public Player getJ() {
        return j;
    }


    public void setJ(Player j) {
        this.j = j;
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

        if(evento.compareTo("golo") == 0)
            return data + " - GOLO ! ("+j.getName()+")";
        
        if(evento.compareTo("amarelo") == 0)
            return data + " - Cartão AMARELO para "+j.getName()+" !";

            return data + " - Cartão VERMELHO para "+j.getName()+" !";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isAdminCheck() {
        return adminCheck;
    }

    public void setAdminCheck(boolean adminCheck) {
        this.adminCheck = adminCheck;
    }
    

   

    
}
