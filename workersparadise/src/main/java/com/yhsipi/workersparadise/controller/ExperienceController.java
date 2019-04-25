package com.yhsipi.workersparadise.controller;

import javax.validation.Valid;

import com.yhsipi.workersparadise.entities.ProfessionalExperience;
import com.yhsipi.workersparadise.entities.ProfessionalExperiencePK;
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

	@RequestMapping(value = "/person/{id}")
	public String getExperiencesByPerson(@PathVariable String id, Model model) {
		int personId = Integer.parseInt(id);
		model.addAttribute("experiences", experienceService.findByPerson(personId));
		return "/experience/index";
	}	

	// Add -> AddForm
	@GetMapping("/add")
    public String addExperience(Model model) {
		System.out.println("adding experience..");
		ProfessionalExperience pe = new ProfessionalExperience();
		pe.setTitle("IT-UTvecklare");
		System.out.println(pe.toString());
        model.addAttribute("experience", pe);
        return "/experience/edit";
    }

    // Edit -> AddForm
    @GetMapping("/edit/{personid}/{experienceid}")
    public String editExperience(@PathVariable int personid,@PathVariable int experienceid , Model model) {
       
    	ProfessionalExperiencePK pk = new ProfessionalExperiencePK();
    	pk.setIdProfExperience(experienceid);
    	pk.setIdPerson(personid);
                	
    	model.addAttribute("experience", experienceService.findOne(pk).get());
    	
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
