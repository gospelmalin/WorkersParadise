package com.yhsipi.workersparadise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yhsipi.workersparadise.service.PersonService;

@Controller
@RequestMapping(value = "/persons")
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	// FindAll
	@RequestMapping(value = "/")
	public String getPersons(Model model) {
		model.addAttribute("persons", personService.findAll());
		return "/person/index";
	}

}
