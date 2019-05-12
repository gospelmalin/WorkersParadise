package com.yhsipi.workersparadise.controller;

import java.io.Console;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import com.yhsipi.workersparadise.entities.Users;
import com.yhsipi.workersparadise.repository.UsersRepository;
import com.yhsipi.workersparadise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import com.yhsipi.workersparadise.entities.Company;
import com.yhsipi.workersparadise.entities.Person;
import com.yhsipi.workersparadise.service.CompanyService;
import com.yhsipi.workersparadise.service.PersonService;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class ProfileController {

    @Autowired
    private PersonService personService;
    @Autowired
    private CompanyService companyservice;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UserService userService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); //yyyy-MM-dd'T'HH:mm:ssZ example
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @GetMapping("/profile")
    public String Profile(Model model) {

        model.addAttribute("person", personService.findOne(6).get());

        return "profile/profile";
    }

    @GetMapping("/profile/{id}")
    public String Profile(@PathVariable int id, Model model) {

        model.addAttribute("person", personService.findOne(id).get());
        return "profile/profile";
    }


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



/*
 *     	Optional<Person> p = personService.findOne(2);
    	
    	System.out.println("User");
    	System.out.println(p.get().toString());
    	
    	System.out.println("Adress");
    	System.out.println(p.get().getAddress().toString());
    	
    	System.out.println("Phone");
    	p.get().getPhones().forEach(x -> System.out.println("phone: "+ x.getPhoneNumber()));
 * */
