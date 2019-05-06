package com.yhsipi.workersparadise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yhsipi.workersparadise.service.CertificationService;
import com.yhsipi.workersparadise.service.PersonService;

@Controller
@RequestMapping(value = "/certifications")
public class CertificationController {
	
	@Autowired
	private CertificationService certificationService;
	@Autowired
	private PersonService personService;

	
	// FindAll
		@RequestMapping(value = "/")
		public String getCertifications(Model model) {
			model.addAttribute("certifications", certificationService.findAll());
			return "/certification/index";
		}
		
		// TODO
		// FindAllByPerson --> All other (add, edit, delete) should be based on this)
		
		@RequestMapping(value = "/person/{id}")
		public String getCertificationsByPerson(@PathVariable String id, Model model) {
			int personId = Integer.parseInt(id);
			model.addAttribute("certifications", certificationService.findByPerson(personId));
			return "/certification/index";
		}
		
		//TODO add
		
		//TODO Edit
		
		//TODO Save
		
	    // Delete
		@RequestMapping(value = "/remove/{id}")
		public String deleteCertification(@PathVariable int id) {
			certificationService.deleteCertification(id);		
			return "redirect:/certifications/";
		}
	
}
