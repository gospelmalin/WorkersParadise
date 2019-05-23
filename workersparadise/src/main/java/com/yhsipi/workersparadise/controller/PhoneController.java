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

import com.yhsipi.workersparadise.entities.Phone;
import com.yhsipi.workersparadise.entities.PhonePK;
import com.yhsipi.workersparadise.service.PhoneService;

@Controller
@RequestMapping(value = "/phone")
public class PhoneController {

	@Autowired
	private PhoneService phoneService;

	
	
	@GetMapping("/")
	public String Phone(Model model) {
		model.addAttribute("phones", phoneService.findAll());
		return "phone/index";
	}
	
	@GetMapping("/edit/{personId}/{phoneId}")
	public String edit(@PathVariable int personId, @PathVariable int phoneId, Model model) {
		
		PhonePK pPK = new PhonePK();
		pPK.setIdPerson(personId);
		pPK.setIdPhone(phoneId);
		Phone p = phoneService.findOne(pPK).get();
		model.addAttribute("phone", p);
		return "/phone/add";	
		
	}
	
	@GetMapping("/person/{personId}")
	public String getPhoneByPerson(@PathVariable int personId, Model model) {
		
		model.addAttribute("phones", phoneService.findByPerson(personId));
		return "/phone/index";
	}
	
	@GetMapping("/add")
	public String add(Model model) {

		model.addAttribute("phone", new Phone());
		return "/phone/add";
	}
	
	@PostMapping("/add")
	public String savePhone(@Valid Phone phone, BindingResult result, Model model) {

		if(result.hasErrors()) {
			return "phone/add";
		}
		phoneService.savePhone(phone);
		return "redirect:/phone/";
	}
	
	@Transactional
	@RequestMapping(value = "/remove/{personId}/{phoneId}")
	public String removePhone(@PathVariable int personId, @PathVariable int phoneId) {
		PhonePK pPK = new PhonePK();
		pPK.setIdPerson(personId);
		pPK.setIdPhone(phoneId);
		phoneService.deletePhone(phoneService.findOne(pPK).get());
		return "redirect:/phone/";
	}
}
