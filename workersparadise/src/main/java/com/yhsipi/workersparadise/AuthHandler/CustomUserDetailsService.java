package com.yhsipi.workersparadise.AuthHandler;

import com.yhsipi.workersparadise.entities.Users;


import com.yhsipi.workersparadise.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.HashSet;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService{
    @Autowired
    private UsersRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Users user;
        try{
            user = userRepository.findByUsername(userName);

        }catch(UsernameNotFoundException e) {
            throw new UsernameNotFoundException("Username not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                getAuthorities(user));
    }

    public Collection<? extends GrantedAuthority> getAuthorities(Users user) {
        return new HashSet<GrantedAuthority>();
    }


}