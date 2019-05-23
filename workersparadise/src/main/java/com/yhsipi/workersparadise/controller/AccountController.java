package com.yhsipi.workersparadise.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yhsipi.workersparadise.entities.Users;
import com.yhsipi.workersparadise.repository.UsersRepository;
import com.yhsipi.workersparadise.service.UserService;

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
    	
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	
    	if((authentication instanceof AnonymousAuthenticationToken)) {
    		
    		ModelAndView model = new ModelAndView();
        	Users user = new Users();
            model.addObject("user", user);
            model.setViewName("account/register");

            return model;
    	}
    	return new ModelAndView("redirect:dashboard");
    }


    @PostMapping("/account/register")
    public ModelAndView createUser(@Valid Users user, BindingResult bindingResult) {
        ModelAndView model = new ModelAndView();
        Users userExists = userService.findByUsername(user.getUsername());

        List<Users> allUsers = userService.findAll();

        int getID = allUsers.size() + 1;
        user.setIdUser(getID);
        user.person.setIdPerson(getID);


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
    public ModelAndView dashboard() {
        ModelAndView model = new ModelAndView();

        model.setViewName("dashboard/index");
        return model;
    }

}


