package com.ThankSens.Hentori.Security.Units;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("admin12".equals(username)){
            UserDetails userDetails = User.builder()
                    .username("admin12")
                    .password("12345")
                    .roles("USER")
                    .build();
            return userDetails;
        }else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
