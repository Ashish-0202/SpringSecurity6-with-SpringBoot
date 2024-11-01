package com.ashish.SpringSecuirtyEx.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {

    @GetMapping("/home")
    public String greet(HttpServletRequest request){
        return "Welcome to our home page :"+request.getSession().getId();
    }
}
