package com.yhsipi.workersparadise.AuthHandler.config;

import com.yhsipi.workersparadise.entities.Users;
import com.yhsipi.workersparadise.repository.UsersRepository;
import com.yhsipi.workersparadise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.Arrays;
import java.util.HashSet;
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepository userRepository;
/*
    @Autowired
    private RoleRespository roleRespository;
*/
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Users findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void saveUser(Users user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        //user.setActive(1);
       // Role userRole = roleRespository.findByRole("ADMIN");
        //user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }


}
