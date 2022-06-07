package com.example.data;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Users implements Serializable{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column (unique = true)
    private String nome;
    private String email;
    private String password;
    private String contacto;
    private boolean isAdmin;
    
    
    public Users() {
    }

    public Users(String nome, String email, String password, String contacto) {
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.contacto = contacto;
    }

    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String toString(){
        return "{"+"\n"+"name: "+getNome()+"\n"+"email: "+getEmail()+"\n"+"password: "+getPassword()+"\n"+"contacto:"+getContacto()+"\n"+"}\n";
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
