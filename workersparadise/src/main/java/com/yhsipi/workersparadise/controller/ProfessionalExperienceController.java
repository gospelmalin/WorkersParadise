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

import com.yhsipi.workersparadise.entities.ProfessionalExperience;
import com.yhsipi.workersparadise.entities.ProfessionalExperiencePK;
import com.yhsipi.workersparadise.service.PersonService;
import com.yhsipi.workersparadise.service.ProfessionalExperienceService;

@Controller
@RequestMapping(value = "/professionalexperience")
public class ProfessionalExperienceController {
	
	@Autowired
	private ProfessionalExperienceService professionalExperienceService;
	
	@Autowired
	private PersonService personService;
	
	@GetMapping("/")
	public String findAll(Model model) {
		model.addAttribute("professionalexperience", professionalExperienceService.findAll());
		return "professionalexperience/index";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable int id, Model model) {
		model.addAttribute("professionalexperience", professionalExperienceService.findOne(id));
		return "professionalexperience/add";
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("professionalexperience", new ProfessionalExperience());
		return "/professionalexperience/add";
	}
	
	@PostMapping("/add")
	public String saveProfessionalExperience(@Valid ProfessionalExperience pe, BindingResult result, Model model) {
		
		ProfessionalExperiencePK pePK = pe.getId();
		pe.setPerson(personService.findOne(pePK.getIdPerson()));
		
		if(result.hasErrors()) {
			return "professionalexperience/add";
		}
		
		professionalExperienceService.saveProfessionalExperience(pe);
		return "redirect:/professionalexperience";
	}
}
