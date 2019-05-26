package com.yhsipi.workersparadise.controller;

import java.net.URI;

import javax.validation.Valid;

import com.yhsipi.workersparadise.entities.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.yhsipi.workersparadise.service.CompanyService;



@Controller
@RequestMapping(value = "/companies")
public class CompanyController {

	@Autowired
	private CompanyService companyService;
	
	// FindAll
	@RequestMapping(value = "/")
	public String getCompanies(Model model) {
		companyService.findAll().forEach(c -> System.out.println(c.toString()));
		model.addAttribute("companies", companyService.findAll());
		return "/company/index";
	}
	
	// Add -> AddForm
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("company", new Company());
        model.addAttribute("companies", companyService.findAll());
        return "/company/edit";
    }

    // Edit -> AddForm
    @GetMapping("/edit/{id}")
    public String editCompany(@PathVariable int id, Model model) {
        model.addAttribute("company", companyService.findOne(id));
        model.addAttribute("companies", companyService.findAll());
        return "/company/edit";
    }    
    
    // Save
    @PostMapping("/add")
    public String saveCompany(@Valid Company company, BindingResult result, Model model){

    	if (result.hasErrors()) {
    		model.addAttribute("company", company);
    		model.addAttribute("companies", companyService.findAll());
    		return "/company/edit";
    	}
    	
    	companyService.saveCompany(company);
    	
        return "redirect:/experience/";
    }
    
    // Delete
	@RequestMapping(value = "/remove/{id}")
	public String deleteCompany(@PathVariable int id) {
		companyService.deleteCompany(id);		
		return "redirect:/companies/";
	}
}
