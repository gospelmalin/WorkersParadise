package com.yhsipi.workersparadise.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yhsipi.workersparadise.entities.Company;
import com.yhsipi.workersparadise.entities.Person;
import com.yhsipi.workersparadise.service.CompanyService;
import com.yhsipi.workersparadise.service.PersonService;

@Controller
public class ProfileController {

	@Autowired
	private PersonService personService;
	@Autowired 
	private CompanyService companyservice;
	
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
    @GetMapping("/account/register")
    public String RegisterNewUser() {
        return "account/register";
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
