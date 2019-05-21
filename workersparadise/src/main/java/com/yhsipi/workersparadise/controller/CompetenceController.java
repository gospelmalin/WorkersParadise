package com.yhsipi.workersparadise.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yhsipi.workersparadise.entities.Competence;
import com.yhsipi.workersparadise.service.CompetenceService;
import com.yhsipi.workersparadise.service.PersonService;

@Controller
@RequestMapping(value="/competence")
public class CompetenceController {
	
	@Autowired
	private CompetenceService competenceService;
	
	@Autowired 
	private PersonService personService;
	
	@GetMapping("/")
	public String Competence(Model model) {
		
		model.addAttribute("competence", competenceService.findAll());
		return "competence/index";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable int id, Model model) {
		model.addAttribute("competence", competenceService.findOne(id));
		return "/competence/add";
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("competence", new Competence());
		return "/competence/add";
	}
	
	// TODO Post-mapping needed after change of competence entity and database table
	/*
	@PostMapping("/add")
	public String saveCompetence(@Valid Competence competence, BindingResult result, Model model) {
		competence.setPerson(personService.findOne(competence.getIdPerson()));
		
		if(result.hasErrors()) {
			return "competence/add";
		}
		competenceService.saveCompetence(competence);
		return "redirect:/competence/";
	}
	*/
}
