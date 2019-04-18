package com.yhsipi.workersparadise.controller;

import javax.validation.Valid;

import com.yhsipi.workersparadise.entities.ProfessionalExperience;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.yhsipi.workersparadise.service.ExperienceService;



@Controller
@RequestMapping(value = "/experience")
public class ExperienceController {

	@Autowired
	private ExperienceService experienceService;
	
	// FindAll
	@RequestMapping(value = "/")
	public String getExperiences(Model model) {
		
		model.addAttribute("experiences", experienceService.findAll());
		return "/experience/index";
	}
	@RequestMapping(value = "/{id}")
	public String getExperiencesByPerson(@PathVariable int personId, Model model) {	
		model.addAttribute("experiences", experienceService.findByPerson(personId));
		return "/experience/index";
	}	
	// Add -> AddForm
    @GetMapping("/add")
    public String addExperience(Model model) {
        model.addAttribute("experience", new ProfessionalExperience());
        return "/experience/edit";
    }

    // Edit -> AddForm
    @GetMapping("/edit/{id}")
    public String editExperience(@PathVariable int id, Model model) {
        model.addAttribute("experience", experienceService.findOne(id));
        return "/experience/edit";
    }    
    
    // Save
    @PostMapping("/save")
    public String saveCompany(@Valid ProfessionalExperience experience, BindingResult result, Model model){
    	
    	if (result.hasErrors()) {
    		return "/experience/edit";
    	}
    	
    	experienceService.saveExperience(experience);
    	
        return "redirect:/experience/";
    }
    
    // Delete
	@RequestMapping(value = "/remove/{id}")
	public String deleteExperience(@PathVariable int id) {
		experienceService.deleteExperience(id);		
		return "redirect:/experience/";
	}
}
