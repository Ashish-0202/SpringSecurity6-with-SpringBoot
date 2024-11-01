package com.ashish.SpringSecuirtyEx.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception{
        return security.csrf(AbstractHttpConfigurer::disable)
    //to disable csrf
        .authorizeHttpRequests(customizer->customizer.anyRequest().authenticated()) //to authenticate all request
        //.formLogin(Customizer.withDefaults()) //for web browser to get login form
        .httpBasic(Customizer.withDefaults()) //for rest api login using postman or api dog
        .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    //To override custom user password one, we can use below bean but only for in memory storing
    //UserDetailsService is an Interface, so we are returning Object of InMemoryUserDetailsManager as bts it is implementing UserDetailsService
    /*@Bean
    public UserDetailsService userDetailsService(){

        UserDetails user1= User.withUsername("Ashish")
                .password("{noop}Ash")
                .roles("ADMIN")
                .build();

        UserDetails user2= User.withUsername("Ashwini")
                .password("{noop}Ashw")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user1,user2);
    }*/

    @Bean
    public AuthenticationProvider authenticationProvider(){
        System.out.println("Inside AuthenticationProvider");
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }
}
