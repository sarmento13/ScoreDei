package com.example.data;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Player {
      
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    
    private String name;
    private String posicao;
    private String data;
    private String logo;
    private int goals;
    private int yellowCards;
    private int redCards;
    private int medgoals;

    @ManyToOne
    private Team equipa;

    public Player(String name, String posicao, String data,String logo, Team equipa) {
        this.name = name;
        this.posicao = posicao;
        this.data = data;
        this.equipa = equipa;
        this.logo = logo;
        this.goals = 0;
        this.yellowCards = 0;
        this.redCards = 0;
    }



    public int getMedgoals() {
        return medgoals;
    }



    public void setMedgoals(int medgoals) {
        this.medgoals = medgoals;
    }



    public int getRedCards() {
        return redCards;
    }

    public void setRedCards(int redCards) {
        this.redCards += redCards;
    }

    public int getYellowCards() {
        return yellowCards;
    }

    public void setYellowCards(int yellowCards) {
        this.yellowCards += yellowCards;
    }

    public Player() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Team getEquipa() {
        return equipa;
    }

    public void setEquipa(Team equipa) {
        this.equipa = equipa;
    }



    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals += goals;
    }

   
    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    @Override
    public String toString() {
        return "Player [data=" + data + ", equipa=" + equipa + ", goals=" + goals + ", id=" + id + ", logo=" + logo
                + ", medgoals=" + medgoals + ", name=" + name + ", posicao=" + posicao + ", redCards=" + redCards
                + ", yellowCards=" + yellowCards + "]";
    }
}
