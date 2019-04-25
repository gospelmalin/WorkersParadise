package com.yhsipi.workersparadise.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yhsipi.workersparadise.entities.Education;
import com.yhsipi.workersparadise.service.EducationService;


@Controller
@RequestMapping(value = "/educations")
public class EducationController {
		
	@Autowired
	private EducationService educationService;
	
	 @InitBinder
	    public void initBinder(WebDataBinder binder) {
	        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd"); //yyyy-MM-dd'T'HH:mm:ssZ example
	        dateFormat.setLenient(false);
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	    }
	
	// FindAll
	@RequestMapping(value = "/")
	public String getEducations(Model model) {
		model.addAttribute("educations", educationService.findAll());
		return "/education/index";
	}
	
	// TODO
	// FindAllByPerson --> All other (add, edit, delete) should be based on this)
	
	// TODO
	// FindByID 
	
	// Add -> AddForm
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("education", new Education());
        return "/education/add";
    }

    // Edit -> AddForm
    @GetMapping("/edit/{id}")
    public String editEducation(@PathVariable int id, Model model) {
        model.addAttribute("education", educationService.findOne(id));
        return "/education/add";
    }    
    
    // Save
    @PostMapping("/add")
    public String saveEducation(@Valid Education education, BindingResult result, Model model){
    	
    	if (result.hasErrors()) {
    		return "/education/edit";
    	}
    	
    	educationService.saveEducation(education);
    	
        return "redirect:/educations/";
    }
    
    // Delete
	@RequestMapping(value = "/remove/{id}")
	public String deleteEducation(@PathVariable int id) {
		educationService.deleteEducation(id);		
		return "redirect:/educations/";
	}

}
