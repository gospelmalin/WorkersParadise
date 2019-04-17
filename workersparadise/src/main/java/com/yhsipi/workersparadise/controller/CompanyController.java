package com.yhsipi.workersparadise.controller;

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
    @GetMapping("/addcompany")
    public String addForm(Model model) {
        model.addAttribute("company", new Company());
        return "/company/addcompany";
    }

    // Edit -> AddForm
    @GetMapping("/edit/{id}")
    public String editCompany(@PathVariable int id, Model model) {
        model.addAttribute("company", companyService.findOne(id));
        return "/company/addcompany";
    }    
    
    // Save
    @PostMapping("/addcompany")
    public String saveCompany(@Valid Company company, BindingResult result, Model model){
    	
    	if (result.hasErrors()) {
    		return "/company/addcompany";
    	}
    	
    	companyService.saveCompany(company);
    	
        return "redirect:/companies/";
    }
    
    // Delete
	@RequestMapping(value = "/remove/{id}")
	public String deleteCompany(@PathVariable int id) {
		companyService.deleteCompany(id);		
		return "redirect:/companies/";
	}
}
