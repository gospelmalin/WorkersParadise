package com.yhsipi.workersparadise.controller;


import com.yhsipi.workersparadise.entities.Users;
import com.yhsipi.workersparadise.repository.UsersRepository;
import com.yhsipi.workersparadise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class AccountController {



    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UserService userService;

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
    public ModelAndView signup() {
        ModelAndView model = new ModelAndView();
        Users user = new Users();
        model.addObject("user", user);
        model.setViewName("account/register");

        return model;
    }


    @PostMapping("/account/register")
    public ModelAndView createUser(@Valid Users user, BindingResult bindingResult) {
        ModelAndView model = new ModelAndView();
        Users userExists = userService.findByUsername(user.getUsername());

        System.out.println("Användarnamn " + user.getUsername());
        System.out.println("Lösenord " + user.getPassword());
        System.out.println("identitet " + user.getUrl());
        System.out.println("Annan info");
        if (userExists != null) {
            bindingResult.rejectValue("username", "error.user", "This username already exists!");
        }
        if (bindingResult.hasErrors()) {
            model.setViewName("account/register");
        } else {
            userService.saveUser(user);
            model.addObject("msg", "User has been registered successfully!");
            model.addObject("user", new Users());
            model.setViewName("/");
        }

        return model;
    }

}


