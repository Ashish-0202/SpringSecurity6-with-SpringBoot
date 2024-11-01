package com.ashish.SpringSecuirtyEx.Controller;

import com.ashish.SpringSecuirtyEx.Entity.users;
import com.ashish.SpringSecuirtyEx.Service.MyUserDetailService;
import com.ashish.SpringSecuirtyEx.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/registerUser")
    public ResponseEntity<String> register(@RequestBody users user){
        users user1 = userService.register(user);
        return ResponseEntity.ok(user1.getUsername()+" Registered Successfully");
    }

    @PostMapping("/login")
    public String login(@RequestBody users user){
        System.out.println("Inside login controller:"+user);
        return userService.verify(user);
    }
}
