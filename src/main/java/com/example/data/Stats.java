package com.example.data;

public class Stats {
    
    private String Gamename;
    private String result;
    private String local;
    private String Data;
    private String events;


    public Stats(String gamename, String result, String local, String data, String events) {
        Gamename = gamename;
        this.result = result;
        this.local = local;
        Data = data;
        this.events = events;
    }


    public String getGamename() {
        return Gamename;
    }


    public void setGamename(String gamename) {
        Gamename = gamename;
    }


    public String getResult() {
        return result;
    }


    public void setResult(String result) {
        this.result = result;
    }


    public String getLocal() {
        return local;
    }


    public void setLocal(String local) {
        this.local = local;
    }


    public String getData() {
        return Data;
    }


    public void setData(String data) {
        Data = data;
    }


    public String getEvents() {
        return events;
    }


    public void setEvents(String events) {
        this.events = events;
    }


    
    
}
