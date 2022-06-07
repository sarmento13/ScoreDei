package com.example.data;

import java.util.ArrayList;
import com.example.data.Player;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Team {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    private String name;
    private String localizacao;
    private String img;
    private int vitorias;
    private int derrotas;
    private int empates;
    private int numGames;
    private int points;

    
    @OneToMany
    private List<Player> listPlayers;

    public Team(String name,String localizacao, String img) {
        this.name = name;
        this.img = img;
        this.localizacao = localizacao;
        listPlayers = new ArrayList<Player>();
        this.points = 0;

    }
    public int getPoints() {
        return points;
    }
    public void setPoints(int points) {
        this.points += points;
    }
    public int getNumGames() {
        return numGames;
    }
    public void setNumGames(int numGames) {
        this.numGames += numGames;
    }
    public Team() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void add(Player player){
        this.listPlayers.add(player);
    }
    
    public void remove(Player player){
        this.listPlayers.remove(player);
    }

    public void show_all_players(){
        for(Player p: this.listPlayers)
            System.out.print(p.toString()+"\n");
    }

    public String toString(){
        return "{"+"\n"+"name: "+getName()+"\n"+"img: "+getImg()+"\n";
    }
    public List<Player> getListPlayers() {
        return listPlayers;
    }
    public void setListPlayers(List<Player> listPlayers) {
        this.listPlayers = listPlayers;
    }
    public String getLocalizacao() {
        return localizacao;
    }
    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }
   
    public int getVitorias() {
        return vitorias;
    }
    public void setVitorias(int vitorias) {
        this.vitorias += vitorias;
    }
    public int getDerrotas() {
        return derrotas;
    }
    public void setDerrotas(int derrotas) {
        this.derrotas += derrotas;
    }
    public int getEmpates() {
        return empates;
    }
    public void setEmpates(int empates) {
        this.empates += empates;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
}
