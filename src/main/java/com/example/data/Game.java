package com.example.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;



@Entity
public class Game implements Serializable{

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    
    @ManyToOne
    private Team equipaCasa;
    @ManyToOne
    private Team equipaFora;

    private String nameGame;
    private String localizacao;
    private String data;
    private String status;
    private int goloCasa;
    private int goloFora;

    
    public Game(Team equipaCasa, Team equipaFora, String localizacao, String data) {
        this.equipaCasa = equipaCasa;
        this.equipaFora = equipaFora;
        this.localizacao = localizacao;
        this.data = data;
        this.goloCasa = 0;
        this.goloFora = 0;
    }
    
    
    public String getNameGame() {
        return nameGame;
    }


    public void setNameGame(String nameGame) {
        this.nameGame = nameGame;
    }


    public Game() {
    }

    public Team getEquipaCasa() {
        return equipaCasa;
    }

    public void setEquipaCasa(Team equipaCasa) {
        this.equipaCasa = equipaCasa;
    }

    public Team getEquipaFora() {
        return equipaFora;
    }

    public void setEquipaFora(Team equipaFora) {
        this.equipaFora = equipaFora;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    
    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void forEach(Object object) {
    }

    public int getGoloCasa() {
        return goloCasa;
    }

    public void setGoloCasa(int goloCasa) {
        this.goloCasa += goloCasa;
    }

    public int getGoloFora() {
        return goloFora;
    }

    public void setGoloFora(int goloFora) {
        this.goloFora += goloFora;
    }

    @Override
    public String toString() {
        return "Game [data=" + data + ", equipaCasa=" + equipaCasa + ", equipaFora=" + equipaFora + ", localizacao="
                + localizacao + ", status=" + status + "]";
    }


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


  
}
