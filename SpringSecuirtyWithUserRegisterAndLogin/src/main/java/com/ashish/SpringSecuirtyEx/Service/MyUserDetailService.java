package com.ashish.SpringSecuirtyEx.Service;

import com.ashish.SpringSecuirtyEx.Entity.MyUserDetails;
import com.ashish.SpringSecuirtyEx.Entity.users;
import com.ashish.SpringSecuirtyEx.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Inside userDetailService");
        System.out.println("username: "+username);
        users user1 = userRepository.findByUsername(username);
        System.out.println("Users:"+user1);
        if(user1 == null){
            System.out.println("Username: "+user1.getUsername());
            throw new UsernameNotFoundException("User Not Found: "+username);
        }
        System.out.println("Username: "+user1.getUsername());
        return new MyUserDetails(user1);
    }

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    //specifying the rounds

    public users register(users user){
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
