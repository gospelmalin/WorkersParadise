package com.yhsipi.workersparadise.controller;

import java.util.Optional;

import com.yhsipi.workersparadise.entities.Users;
import com.yhsipi.workersparadise.repository.UsersRepository;
import com.yhsipi.workersparadise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping("/login")
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

        if (userExists != null) {
            bindingResult.rejectValue("email", "error.user", "This userme already exists!");
        }
        if (bindingResult.hasErrors()) {
            model.setViewName("account/register");
        } else {
            userService.saveUser(user);
            model.addObject("msg", "User has been registered successfully!");
            model.addObject("user", new Users());
            model.setViewName("profile");
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
