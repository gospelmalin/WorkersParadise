package com.yhsipi.workersparadise.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import com.yhsipi.workersparadise.service.PersonService;

@Controller
public class ProfileController {

    @Autowired
    private PersonService personService;

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
}