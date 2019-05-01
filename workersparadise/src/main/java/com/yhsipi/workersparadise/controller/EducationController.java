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
import com.yhsipi.workersparadise.entities.EducationPK;
import com.yhsipi.workersparadise.service.EducationService;
import com.yhsipi.workersparadise.service.PersonService;


@Controller
@RequestMapping(value = "/educations")
public class EducationController {
		
	@Autowired
	private EducationService educationService;
	@Autowired
	private PersonService personService;
	
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
	
	@RequestMapping(value = "/person/{id}")
	public String getEducationsByPerson(@PathVariable String id, Model model) {
		int personId = Integer.parseInt(id);
		model.addAttribute("educations", educationService.findByPerson(personId));
		return "/education/index";
	}	
	
	// Add -> AddForm
    @GetMapping("/add")
    public String addForm(Model model) {
    	System.out.println("adding education..");
    	Education ed = new Education();
    	ed.setEducationName("Testutbildningsnamn");
    	System.out.println(ed.toString());
    //    model.addAttribute("education", new Education());
    	model.addAttribute("education", ed);
        return "/education/addedit";
    }

    // Edit -> AddForm
    @GetMapping("/edit/{personid}/{educationid}")
    public String editEducation(@PathVariable int personid, @PathVariable int educationid, Model model) {
      EducationPK edpk = new EducationPK();
      edpk.setIdEducation(educationid);
      edpk.setIdPerson(personid);
      model.addAttribute("education", educationService.findOne(edpk).get());
    //	model.addAttribute("education", educationService.findOne(id));
        return "/education/addedit";
    }    
    
    // Save
    @PostMapping("/add")
    public String saveEducation(@Valid Education education, BindingResult result, Model model){
    	
    	EducationPK edPK = education.getId();
    	System.out.println(edPK.getIdEducation() + edPK.getIdPerson());
    	
    	
    	if (result.hasErrors()) {
    		return "/education/addedit";
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
