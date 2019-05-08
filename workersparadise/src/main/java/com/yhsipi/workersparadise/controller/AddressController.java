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

import com.yhsipi.workersparadise.entities.Address;
import com.yhsipi.workersparadise.service.AddressService;
import com.yhsipi.workersparadise.service.PersonService;

@Controller
@RequestMapping(value="/address")
public class AddressController {

	@Autowired 
	private AddressService addressService;
	@Autowired
	private PersonService personService;
	
	@GetMapping("/")
	public String Address(Model model) {
		model.addAttribute("address", addressService.findAll());
		return "address/index";
	}
	
	@GetMapping("/edit/{id}")
	public String editId(@PathVariable int id, Model model) {
		
		model.addAttribute("address", addressService.findOne(id));
		return "/address/index";
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("address", new Address());
		return "/address/add";
	}
	
	@PostMapping("/add")
	public String saveAddress(@Valid Address address, BindingResult result, Model model) {
		
		address.setPerson(personService.findOne(address.getPersonId()));
		
		if(result.hasErrors()) {
			return "address/add";
		}
		
		addressService.saveAddress(address);
		return "redirect:/address";
	}
	
}
