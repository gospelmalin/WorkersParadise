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

import com.yhsipi.workersparadise.entities.Education;
import com.yhsipi.workersparadise.entities.EducationPK;
import com.yhsipi.workersparadise.entities.Email;
import com.yhsipi.workersparadise.entities.EmailPK;
import com.yhsipi.workersparadise.service.EmailService;
import com.yhsipi.workersparadise.service.PersonService;

@Controller
@RequestMapping(value = "/emails")
public class EmailController {

	@Autowired
	private EmailService emailService;
	@Autowired
	private PersonService personService;
	
	// FindAll
	@RequestMapping(value = "/")
	public String getEmails(Model model) {
		model.addAttribute("emails", emailService.findAll());
		return "/email/index";
	}
	
	@RequestMapping(value = "/person/{id}")
	public String getEmailsByPerson(@PathVariable String id, Model model) {
		int personId = Integer.parseInt(id);
		model.addAttribute("emails", emailService.findByPerson(personId));
		return "/email/index";
	}	
	
	// Add -> AddForm
    @GetMapping("/add")
    public String addForm(Model model) {
    	System.out.println("adding email...");
    	Email e = new Email();
   //  	e.setEmail("test@email.nu");
   //  	e.setIdType(1);
    	System.out.println(e.toString());
    //   model.addAttribute("email", new Email()); 
    	model.addAttribute("email", e);
        return "/email/addedit";
    }
    
 // Edit -> AddForm
    @GetMapping("/edit/{personid}/{emailid}")
    public String editEmail(@PathVariable int personid, @PathVariable int emailid, Model model) {
      EmailPK epk = new EmailPK();
      epk.setIdEmail(emailid);
      epk.setIdPerson(personid);
      model.addAttribute("email", emailService.findOne(epk).get());
    //	model.addAttribute("email", emailService.findOne(id));
        return "/email/addedit";
    }   
    
    // Save
    @PostMapping("/add")
    public String saveEmail(@Valid Email email, BindingResult result, Model model){
    	
    	EmailPK ePK = email.getId();
    	System.out.println(ePK.getIdEmail() + ePK.getIdPerson());
    	
    	
    	if (result.hasErrors()) {
    		return "/email/addedit";
    	}
    	
    	emailService.saveEmail(email);
    	
        return "redirect:/emails/";
    }
    
 // Delete
 	@RequestMapping(value = "/remove/{id}")
 	public String deleteEmail(@PathVariable int id) {
 		emailService.deleteEmail(id);		
 		return "redirect:/emails/";
 	}
	
}
