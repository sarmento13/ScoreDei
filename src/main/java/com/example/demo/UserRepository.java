package com.example.demo;

import java.util.Optional;

import com.example.data.Users;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Users, Integer>   
{   
    @Query("select user from Users user where user.email like %?1 and user.password like %?2")
    Users findByEmailAndPassword(String email,String password);
    
    @Query("select user from Users user where user.email like %?1")
    Users findByEmail(String email);
} 