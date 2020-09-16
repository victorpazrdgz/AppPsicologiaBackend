package com.app.psicologia.service;


import java.util.ArrayList;
import java.util.List;


import com.app.psicologia.model.User;
import com.app.psicologia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        try {
            User user = userRepository.findByEmailOrUserName(s);

            if (user == null) {
                throw new UsernameNotFoundException(String.format("The username %s doesn't exist", s));
            }

            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(user.getRole()));

            UserDetails userDetails = new org.springframework.security.core.userdetails.
                    User(user.getUserName(), user.getPassword(), authorities);

            return userDetails;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}