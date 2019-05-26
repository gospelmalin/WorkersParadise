package com.yhsipi.workersparadise.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.yhsipi.workersparadise.entities.Email;
import com.yhsipi.workersparadise.entities.EmailPK;
import com.yhsipi.workersparadise.entities.Phone;
import com.yhsipi.workersparadise.entities.PhonePK;
import com.yhsipi.workersparadise.entities.Users;
import com.yhsipi.workersparadise.service.EmailService;
import com.yhsipi.workersparadise.service.TypeService;
import com.yhsipi.workersparadise.service.UserService;

@Controller
@RequestMapping(value = "/emails")
public class EmailController {

	@Autowired
	private EmailService emailService;
	@Autowired
	private TypeService typeService;
	@Autowired
	private UserService userService;
	
	public EmailController() {
		super();
	}
	

	//FindAll by logged in person - Get the logged in user and get his/her emails
	@RequestMapping(value = "/")
	public String getEmailsByPerson(Model model) {
		
		// Get logged in user
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Users user = userService.findByUsername(authentication.getName());
		
		// models based on user
		model.addAttribute("emails", emailService.findByPerson(user.person.getIdPerson()));
		model.addAttribute("person", user.person);
		return "/email/index";
	}
			
		// Create new email
	// Add -> AddForm
		@GetMapping("/add")
		public String addEmail(Model model) {
				
		// Models
		model.addAttribute("email",  new Email());
		model.addAttribute("types", typeService.findAll());
		
		return "/email/addedit";
		}
		
		//Edit - update emails - show data for logged in person only
		@GetMapping("/edit/{emailid}")
		public String editEmail(@PathVariable int emailid, Model model) {
			
			// logged in user
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			Users user = userService.findByUsername(authentication.getName());
			
			// emailPK @PathId @loggedinuserPersonId
			EmailPK pk = new EmailPK();
			pk.setIdEmail(emailid);
			pk.setIdPerson(user.person.getIdPerson());

			// Email
			Email email = emailService.findOne(pk).get();
			
			// models based on loggedin user
			model.addAttribute("email", emailService.findOne(pk).get());
			model.addAttribute("type", email.getType());
			model.addAttribute("types", typeService.findAll());
			
			return "/email/addedit";

		}
		
		 // Save email or create new
	    // Save
	    @PostMapping("/save")
		public String saveEmail(@Valid Email email, BindingResult result, Model model) {

			// if person is null get person from logged in user
			if (email.getPerson()== null || email.getPerson().getIdPerson()==0) {
				
				System.out.println("\n################ Save new Email ################\n# ");
				
				// user
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				Users user = userService.findByUsername(authentication.getName());
				
				EmailPK epk = email.getId();
				epk.setIdPerson(user.person.getIdPerson());
				email.setId(epk);
			}
			
			// Error handling
			if (result.hasErrors()) {
				
				EmailPK pk = email.getId();
				
				// log
				System.out.println("\n################ Error when saving Phone ################\n#" + emailService.findOne(pk).get() + "\n# ");
				
				// log all errors
				for (ObjectError error : result.getAllErrors()) {
					System.out.println("# " + error.toString());
				} 
							
				// models for retry
				model.addAttribute("email", emailService.findOne(pk).get());
				model.addAttribute("types", typeService.findAll());
				return "/email/addedit";
			}

			// Save if all is OK
			emailService.saveEmail(email);

			return "redirect:/emails/";
		}
	    
	    // Delete a phone
	    @Transactional // <- Important to get delete to work
		@RequestMapping(value = "/remove/{id}")
		public String deleteEmail(@PathVariable int id) {
			
			// email
	    	EmailPK pk = new EmailPK();
			pk.setIdEmail(id);
			
			// user, person id
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			Users user = userService.findByUsername(authentication.getName());
			pk.setIdPerson(user.person.getIdPerson());
			
			// Delete
			emailService.deleteEmail(pk);
			
			return "redirect:/emails/";
		}
	
	// OLD - remove if not to be used
	 
	/*    
	// FindAll
	@RequestMapping(value = "/all")
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
    @GetMapping("/person/{id}/add")
    public String addEmailFormForPerson(@PathVariable int id, Model model) {
    	System.out.println("adding email...");
    	Email e = new Email();
    	EmailPK ePK = new EmailPK();
    	ePK.setIdPerson(id);
    	e.setId(ePK);
   //  	e.setEmail("test@email.nu");
   //  	e.setIdType(1);
    	System.out.println(e.toString());
    //   model.addAttribute("email", new Email()); 
    	model.addAttribute("email", e);
        model.addAttribute("types", typeService.findAll());
        return "/email/addedit";
    }
    
 // Edit -> AddForm
    @GetMapping("/edit/{personid}/{emailid}")
    public String editEmail(@PathVariable int personid, @PathVariable int emailid, Model model) {
      EmailPK epk = new EmailPK();
      epk.setIdEmail(emailid);
      epk.setIdPerson(personid);
      System.out.println("Detta är personId för den som ska editera epost: " + epk.getIdPerson());
      model.addAttribute("email", emailService.findOne(epk).get());
    //	model.addAttribute("email", emailService.findOne(id));
       model.addAttribute("types", typeService.findAll());
        return "/email/addedit";
    }   
    
    // Save
    @PostMapping("/add")
    public String saveEmail(@Valid Email email, BindingResult result, Model model){
    	System.out.println("Nu påbörjas save-metoden");
    	EmailPK ePK = email.getId();
    	System.out.println("jag har ett EmailPK: " + ePK);
    	System.out.println("emailID: " + ePK.getIdEmail() + " för person: " + ePK.getIdPerson());
    	
    	
    	if (result.hasErrors()) {
    		return "/email/addedit";
    	}
    	
    	emailService.saveEmail(email);
    	
        return "redirect:/emails/";
    }
    
 // Delete
 	@RequestMapping(value = "/remove/{personid}/{emailid}")
 	public String deleteEmail(@PathVariable int personid, @PathVariable int emailid) {
 		EmailPK epk = new EmailPK();
		epk.setIdEmail(emailid);
		epk.setIdPerson(personid);
 		emailService.deleteEmail(emailService.findOne(epk).get());		
 		return "redirect:/emails/";
 	}
*/	
}
