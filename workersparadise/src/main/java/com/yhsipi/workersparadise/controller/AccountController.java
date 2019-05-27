package com.yhsipi.workersparadise.controller;


import com.yhsipi.workersparadise.AuthHandler.CustomUserDetailsService;
import com.yhsipi.workersparadise.entities.About;
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


    @GetMapping("/account/changepassword")
    public ModelAndView changepasswordVie(Principal authuser) {
        ModelAndView model = new ModelAndView();
            Users user = new Users();
            model.addObject("user", user);
            model.setViewName("account/resetpassword");
            return model;
        }

    @PostMapping("/account/changepassword")
    public String changepassword(@Valid Users userinputs, BindingResult bindingResult) {
        ModelAndView model = new ModelAndView();

            // Get logged in user
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Users user = userService.findByUsername(authentication.getName());

            Users tuser = new Users();


        //TODO: Snygga till denna lösning, borde inte behöva läsa in hela användare + validera inputen, hantera felmeddelanden
            tuser.setIdUser(user.getIdUser());
            tuser.setUsername(user.getUsername());
            tuser.setPassword(userinputs.getPassword());
            tuser.setDateCreated(user.getDateCreated());
            tuser.setUrl(user.getUrl());
            tuser.setEnabled(user.getEnabled());
            tuser.setPerson(user.getPerson());


            System.out.println(tuser.getUsername());
            System.out.println(tuser.getPassword());
            System.out.println(tuser.getDateCreated());
            System.out.println(tuser.getUrl());
            System.out.println(tuser.getEnabled());
            System.out.println(tuser.getIdUser());
            userService.saveUser(tuser);
            return "redirect:/account/dashboard";

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

		// Get logged in user
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Users user = userService.findByUsername(authentication.getName());
		
        ModelAndView model = new ModelAndView();

        model.addObject("person", personService.findOne(user.person.getIdPerson()).get());
        model.setViewName("account/dashboard");

        return model;
    }

}


