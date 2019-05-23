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

import com.yhsipi.workersparadise.entities.Type;
import com.yhsipi.workersparadise.service.TypeService;

@Controller
@RequestMapping(value="/types")
public class TypeController {
		
	@Autowired
	private TypeService typeService;
	
	
	@GetMapping("/")
	public String getType(Model model) {
		
		model.addAttribute("types", typeService.findAll());
		return "type/index";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable int id, Model model) {
		model.addAttribute("type", typeService.findOne(id));
		return "/type/add";
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("type", new Type());
		return "/type/add";
	}
	

	@PostMapping("/add")
	public String saveType(@Valid Type type, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			return "type/add";
		}
		typeService.saveType(type);
		return "redirect:/types/";
	}
	
	 // Delete
		@RequestMapping(value = "/remove/{id}")
		public String deleteType(@PathVariable int id) {
			typeService.deleteType(id);		
			return "redirect:/types/";
		}


}
