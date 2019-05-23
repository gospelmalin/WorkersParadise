package com.yhsipi.workersparadise.controller;


import com.yhsipi.workersparadise.AuthHandler.CustomUserDetailsService;
import com.yhsipi.workersparadise.entities.Person;
import com.yhsipi.workersparadise.entities.Users;
import com.yhsipi.workersparadise.repository.UsersRepository;
import com.yhsipi.workersparadise.service.PersonService;
import com.yhsipi.workersparadise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.validation.Valid;
import java.security.Principal;
import java.sql.ResultSet;
import java.util.Collections;
import java.util.List;

@Controller
public class AccountController {



    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private PersonService personService;

    @Autowired
    JdbcTemplate jdbcTemplate;

/*
        USER LOGIN OCH REGISTERING
     */

    @GetMapping("/account/login")
    public ModelAndView login() {
        ModelAndView model = new ModelAndView();

        model.setViewName("account/login");
        return model;
    }


    @GetMapping("/account/register")
    public ModelAndView signup(Principal authuser) {
        ModelAndView model = new ModelAndView();

        if(authuser != null) {
            model.setViewName("redirect:dashboard");
            return model;
        }else {
            Users user = new Users();
            model.addObject("user", user);
            model.setViewName("account/register");
            return model;

        }
    }


    @PostMapping("/account/register")
    public ModelAndView createUser(@Valid Users user, BindingResult bindingResult) {
        ModelAndView model = new ModelAndView();
        Users userExists = userService.findByUsername(user.getUsername());
        int checkAccountUserID = 0;
        int checkAccountProfileID = 0;
        List<Users> allUsers = userService.findAll();
        List<Person> allPersons = personService.findAll();
        for(Users userID : allUsers) {
            if(userID.getIdUser() > checkAccountUserID) {
                checkAccountUserID = userID.getIdUser();
            }
        }
        for(Person userPersonID : allPersons) {
            if(userPersonID.getIdPerson() > checkAccountProfileID) {
                checkAccountProfileID = userPersonID.getIdPerson();
            }
        }
        user.setIdUser(checkAccountUserID +1);
        user.person.setIdPerson(checkAccountProfileID + 1);


        if (userExists != null) {
            bindingResult.rejectValue("username", "error.user", "This username already exists!");
        }
        if (bindingResult.hasErrors()) {
            model.setViewName("account/register");
        } else {
            userService.saveUser(user);
            model.addObject("msg", "User has been registered successfully!");
            model.addObject("user", new Users());
            model.setViewName("account/login");
        }

        return model;
    }


    @GetMapping("/account/dashboard")
    public ModelAndView dashboard(Principal principal){

        ModelAndView model = new ModelAndView();

        model.setViewName("dashboard/index");

        return model;
    }

}
