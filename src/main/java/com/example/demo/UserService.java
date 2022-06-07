package com.example.demo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.example.data.Users;

import org.springframework.beans.factory.annotation.Autowired;    
import org.springframework.stereotype.Service;

@Service
public class UserService   
{    
    String admin = "sd@gmail.com";


    @Autowired    
    private UserRepository userRepository;    

    public Users register(Users user)  
    {   

        if(userRepository.findByEmail(user.getEmail()) != null)
            return null;
        


        if(user.getEmail().isEmpty() || user.getPassword().isEmpty())
            return null;
        
        else{
            if(user.getEmail().compareTo(admin) == 0)
                user.setAdmin(true);
            
            else
                user.setAdmin(false);
            

            user.setPassword(encode(user.getPassword()));
            userRepository.save(user); 
        }

        return user;    
    }
    
    public Users authenticateUsers(Users user)  
    {   
        user.setPassword(encode(user.getPassword()));
        return userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword()); 
    }


    public String encode(String pass){
        StringBuilder sb = new StringBuilder();
        try 
        {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(pass.getBytes());
            byte[] bytes = md.digest();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}    