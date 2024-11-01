package com.ashish.SpringSecuirtyEx.Service;

import com.ashish.SpringSecuirtyEx.Entity.MyUserDetails;
import com.ashish.SpringSecuirtyEx.Entity.users;
import com.ashish.SpringSecuirtyEx.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
        users user1 = userRepository.findByUsername(username);
        if(user1 == null){
            throw new UsernameNotFoundException("User Not Found: "+username);
        }
        return new MyUserDetails(user1);
    }

}
