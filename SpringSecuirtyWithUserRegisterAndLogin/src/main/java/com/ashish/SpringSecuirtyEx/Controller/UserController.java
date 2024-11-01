package com.ashish.SpringSecuirtyEx.Controller;

import com.ashish.SpringSecuirtyEx.Entity.users;
import com.ashish.SpringSecuirtyEx.Service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private MyUserDetailService service;

    @PostMapping("/registerUser")
    public ResponseEntity<String> register(@RequestBody users user){
        users user1 = service.register(user);
        return ResponseEntity.ok(user1.getUsername()+"User Registered Successfully");
    }
}
