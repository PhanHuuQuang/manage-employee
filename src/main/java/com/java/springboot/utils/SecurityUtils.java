package com.java.springboot.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtils {

    public static String getEmail(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = "";
        if(principal instanceof UserDetails){
            email = ((UserDetails) principal).getUsername();
        } else{
            email = principal.toString();
        }
        return email;
    }
}
