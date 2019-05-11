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

import com.yhsipi.workersparadise.entities.Email;
import com.yhsipi.workersparadise.entities.EmailPK;
import com.yhsipi.workersparadise.entities.Webpage;
import com.yhsipi.workersparadise.entities.WebpagePK;
import com.yhsipi.workersparadise.service.PersonService;
import com.yhsipi.workersparadise.service.WebpageService;

@Controller
@RequestMapping(value = "/webpages")
public class WebpageController {
	
	@Autowired
	private WebpageService webpageService;
	@Autowired
	private PersonService personService;

	// FindAll
	@RequestMapping(value = "/")
	public String getWebpages(Model model) {
		model.addAttribute("webpages", webpageService.findAll());
		return "/webpage/index";
	}
	
	@RequestMapping(value = "/person/{id}")
	public String getWebpagesByPerson(@PathVariable String id, Model model) {
		int personId = Integer.parseInt(id);
		model.addAttribute("webpages", webpageService.findByPerson(personId));
		return "/webpage/index";
	}	
	
	// Add -> AddForm
    @GetMapping("/person/{id}/add")
    public String addWebpageFormForPerson(Model model) {
    	System.out.println("adding webpage...");
    	Webpage w = new Webpage();
    	System.out.println(w.toString());
    	model.addAttribute("webpage", w);
        return "/webpage/addedit";
    }
    
 // Edit -> AddForm
    @GetMapping("/edit/{personid}/{webpageid}")
    public String editWebpage(@PathVariable int personid, @PathVariable int webpageid, Model model) {
      WebpagePK wpk = new WebpagePK();
      wpk.setIdWebpage(webpageid);
      wpk.setIdPerson(personid);
      System.out.println("Detta är personId för den som den editerade websidan tillhör: " + wpk.getIdPerson());
      model.addAttribute("webpage", webpageService.findOne(wpk).get());
        return "/webpage/addedit";
    }   
    
    // Save
    @PostMapping("/add")
    public String saveWebpage(@Valid Webpage webpage, BindingResult result, Model model){
    	System.out.println("Nu påbörjas save-metoden");
    	WebpagePK wPK = webpage.getId();
    	System.out.println("jag har ett WebpagePK: " + wPK);
    	System.out.println("webpageID: " + wPK.getIdWebpage() + " för person: " + wPK.getIdPerson());
    	
    	
    	if (result.hasErrors()) {
    		return "/webpage/addedit";
    	}
    	
    	webpageService.saveWebpage(webpage);
    	
        return "redirect:/webpages/";
    }
    
 // Delete //Does not work yet
 	@RequestMapping(value = "/remove/{id}")
 	public String deleteWebpage(@PathVariable int id) {
 		webpageService.deleteWebpage(id);		
 		return "redirect:/webpages/";
 	}
	
}
