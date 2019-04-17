package com.yhsipi.workersparadise.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.yhsipi.workersparadise.entities.Person;
import com.yhsipi.workersparadise.service.PersonService;

@Controller
public class ProfileController {

	@Autowired
	private PersonService personService;
	
    @GetMapping("/profile")
    public String Profile(Model model) {

    	Optional<Person> p = personService.findOne(2);
    	
    	System.out.println("User");
    	System.out.println(p.get().toString());
    	
    	//System.out.println("Adress");
    	//System.out.println(p.get().getAddress().toString());
    
    	System.out.println("Phone");
    	p.get().getPhones().forEach(x -> System.out.println("phone: "+ x.getPhoneNumber()));
    	
    	model.addAttribute("person", personService.findOne(2));
    	
        return "profile/profile";
    }
}
