package com.yhsipi.workersparadise.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yhsipi.workersparadise.entities.About;
import com.yhsipi.workersparadise.service.AboutService;
import com.yhsipi.workersparadise.service.PersonService;

@Controller
@RequestMapping(value = "/about")
public class AboutController {

	@Autowired
	private AboutService aboutService;
	@Autowired
	private PersonService personService;
	
	@GetMapping("/")
	public String About(Model model) {
		model.addAttribute("abouts", aboutService.findAll());
		return "about/index";
	}
	
	@GetMapping("/edit/{id}")
	public String About(@PathVariable int id, Model model) {
		model.addAttribute("about", aboutService.findOne(id));
		return "/about/add";
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("about", new About());
		return "/about/add";
	}
	
	@PostMapping("/add")
	public String saveAbout(@Valid About about, BindingResult result, Model model) {
		about.setPerson(personService.findOne(about.getIdPerson()));
		if(result.hasErrors()) {
			return "about/add";
		}
		aboutService.saveAbout(about);
		return "redirect:/about/";
	}
	
	@Transactional
	@RequestMapping(value="/remove/{aboutId}")
	public String deleteAbout(@PathVariable int aboutId) {
		aboutService.deleteAbout(aboutId);
		return "redirect:/about/";
	}
	
}
