package com.yhsipi.workersparadise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yhsipi.workersparadise.service.CompanyService;

@Controller
@RequestMapping(value = "/companies")
public class CompanyController {

	@Autowired
	private CompanyService companyService;
	
	// FindAll
	@RequestMapping(value = "/")
	public String getUsers(Model model) {
		model.addAttribute("companies", companyService.findAll());
		return "/company/index";
	}
}
