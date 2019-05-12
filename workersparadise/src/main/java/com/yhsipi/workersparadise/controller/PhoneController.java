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

import com.yhsipi.workersparadise.entities.Phone;
import com.yhsipi.workersparadise.entities.PhonePK;
import com.yhsipi.workersparadise.service.PersonService;
import com.yhsipi.workersparadise.service.PhoneService;

@Controller
@RequestMapping(value = "/phone")
public class PhoneController {

	@Autowired
	private PhoneService phoneService;
	@Autowired
	private PersonService personService;
	
	@GetMapping("/")
	public String Phone(Model model) {
		model.addAttribute("phones", phoneService.findAll());
		return "phone/index";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable int id, Model model) {
		model.addAttribute("phones", phoneService.findOne(id));
		return "/phone/add";
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("phone", new Phone());
		return "/phone/add";
	}
	
	@PostMapping("/add")
	public String savePhone(@Valid Phone phone, BindingResult result, Model model) {
		PhonePK phonePK = phone.getId();
		phone.setPerson(personService.findOne(phonePK.getIdPerson()));
		
		if(result.hasErrors()) {
			return "phone/add";
		}
		phoneService.savePhone(phone);
		return "redirect:/phone/";
	}
}
