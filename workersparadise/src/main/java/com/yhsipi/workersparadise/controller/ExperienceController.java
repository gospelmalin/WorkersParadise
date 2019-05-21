package com.yhsipi.workersparadise.controller;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;
import com.yhsipi.workersparadise.entities.ProfessionalExperience;
import com.yhsipi.workersparadise.entities.ProfessionalExperiencePK;
import com.yhsipi.workersparadise.entities.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yhsipi.workersparadise.service.CompanyService;
import com.yhsipi.workersparadise.service.ExperienceService;
import com.yhsipi.workersparadise.service.UserService;

@Controller
@RequestMapping(value = "/experience")
public class ExperienceController {

	@Autowired
	private ExperienceService experienceService;

	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private UserService userService;
	
	public ExperienceController() {
		super();
	}

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); //yyyy-MM-dd'T'HH:mm:ssZ example
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

	// FindAll
	@RequestMapping(value = "/")
	public String getExperiences(Model model) {

		model.addAttribute("experiences", experienceService.findAll());
		
		return "/experience/index";
	}

	@RequestMapping(value = "/person")
	public String getExperiencesByPerson(Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Users user = userService.findByUsername(authentication.getName());
		
		model.addAttribute("experiences", experienceService.findByPerson(user.person.getIdPerson()));
		return "/experience/index";
	}

	// Add -> AddForm
	@GetMapping("/add")
	public String addExperience(Model model) {
		
		model.addAttribute("experience", new ProfessionalExperience());
		model.addAttribute("companies", companyService.findAll());
		
		return "/experience/edit";
	}

	// Edit -> AddForm
	@GetMapping("/edit/{personid}/{experienceid}")
	public String editExperience(@PathVariable int personid, @PathVariable int experienceid, Model model) {
		
		ProfessionalExperiencePK pk = new ProfessionalExperiencePK();
		pk.setIdProfExperience(experienceid);
		pk.setIdPerson(personid);

		ProfessionalExperience experience = experienceService.findOne(pk).get();
		System.out.println("############################\n"+experience.getCompany().getIdCompany());
		
		model.addAttribute("experience", experience);
		model.addAttribute("company", experience.getCompany());
		model.addAttribute("companies", companyService.findAll());
		
		return "/experience/edit";

	}

	// Save
	@PostMapping("/save")
	public String saveExperience(@Valid ProfessionalExperience experience, BindingResult result, Model model) {

		System.out.println("\n################ Save Experience ################\n# ");
		System.out.println(experience.toString());
		
		// Felhantering
		if (result.hasErrors()) {
			
			ProfessionalExperiencePK pk = experience.getId();
			
			System.out.println("\n################ Save Erorrs in Experience ################\n# " 
								+ experienceService.findOne(pk).get() + "\n# ");
			for (ObjectError error : result.getAllErrors()) {
				System.out.println("# " + error.toString());
			} 
			System.out.println("#\n###########################################################\n");
			
			model.addAttribute("experience", experienceService.findOne(pk).get());
			model.addAttribute("companies", companyService.findAll());
			return "/experience/edit";
		}

		// spara om det om allt är ok
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
