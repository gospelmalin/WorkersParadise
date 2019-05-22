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
import com.yhsipi.workersparadise.entities.Person;
import com.yhsipi.workersparadise.entities.PersonCompetence;
import com.yhsipi.workersparadise.service.PersonCompetenceService;
import com.yhsipi.workersparadise.service.PersonService;

@Controller
@RequestMapping(value="/personcompetences")
public class PersonCompetenceController {
	
	@Autowired
	private PersonCompetenceService personCompetenceService;
	@Autowired
	private PersonService personService;

	@GetMapping("/")
	public String getPersonCompetence(Model model) {
		
		model.addAttribute("personcompetences", personCompetenceService.findAll());
		return "personcompetence/index";
	}
	
	@GetMapping(value = "/person/{id}")
	public String getCompetencesByPerson(@PathVariable String id, Model model) {
		int personId = Integer.parseInt(id);
		model.addAttribute("personcompetences", personCompetenceService.findByPerson(personId));
		return "/personcompetence/index";
	}
	
	//add //Works, but need to show person
	 @GetMapping("/person/{id}/add")
	    public String addCompetenceFormForPerson(Model model) {
	    	System.out.println("adding personal competence...");
	    	PersonCompetence pc = new PersonCompetence();
	    	System.out.println(pc.toString());
	    	model.addAttribute("personcompetence", pc);
	        return "/personcompetence/add";
	    }

	//Edit
	 @GetMapping("/edit/{personid}/{personcompetenceid}")
	    public String editPersonCompetence(@PathVariable int personid, @PathVariable int personcompetenceid, Model model) {
	     Person p = new Person();
	     p.setIdPerson(personid);
	      System.out.println("Detta är personId för den som ska ha kompetence ändrad: " + p.getIdPerson());
	      model.addAttribute("personcompetence", personCompetenceService.findOne(personcompetenceid).get());
	        return "/personcompetence/add";
	    }   
	 
	

	@PostMapping("/add")
	public String savePersonCompetence(@Valid PersonCompetence personCompetence, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			return "personcompetence/add";
		}
		personCompetenceService.savePersonCompetence(personCompetence);
		return "redirect:/personcompetences/";
	}
	
	 // Delete
		@RequestMapping(value = "/remove/{id}")
		public String deleteCompetence(@PathVariable int id) {
			personCompetenceService.deletePersonCompetence(id);		
			return "redirect:/personcompetences/";
		}
	
  
	
}
