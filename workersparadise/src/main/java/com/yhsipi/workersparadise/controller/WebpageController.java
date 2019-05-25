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

import com.yhsipi.workersparadise.entities.Users;
import com.yhsipi.workersparadise.entities.Webpage;
import com.yhsipi.workersparadise.entities.WebpagePK;
import com.yhsipi.workersparadise.service.UserService;
import com.yhsipi.workersparadise.service.WebpageService;

@Controller
@RequestMapping(value = "/webpages")
public class WebpageController {
	
	@Autowired
	private WebpageService webpageService;
	@Autowired
	private UserService userService;
	
	public WebpageController() {
		super();
	}

	// Old controller - remove if not to be used?
	// FindAll
	@RequestMapping(value = "/all")
	public String getWebpages(Model model) {
		model.addAttribute("webpages", webpageService.findAll());
		return "/webpage/index";
	}
	
	//FindAll by logged in person - Get the logged in user and get his/her educations
	@RequestMapping(value = "/")
	public String getWebpagesByPerson(Model model) {
		
		// Get logged in user
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Users user = userService.findByUsername(authentication.getName());
		
		// models based on user
		model.addAttribute("webpages", webpageService.findByPerson(user.person.getIdPerson()));
		model.addAttribute("person", user.person);
		return "/webpage/index";
	}
		
	// Create new webpage
	@GetMapping("/add")
	public String addWebpage(Model model) {
			
	// Models
	model.addAttribute("webpage",  new Webpage());
	
	return "/webpage/addedit";
	}
	
	//Edit - update webpages - show data for logged in person only
	@GetMapping("/edit/{webpageid}")
	public String editWebpage(@PathVariable int webpageid, Model model) {
		
		// logged in user
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Users user = userService.findByUsername(authentication.getName());
		
		// webpagePK @PathId @loggedinuserPersonId
		WebpagePK pk = new WebpagePK();
		pk.setIdWebpage(webpageid);
		pk.setIdPerson(user.person.getIdPerson());

		// Webpage
		Webpage webpage = webpageService.findOne(pk).get();
		
		// models based on loggedin user
		model.addAttribute("webpage", webpageService.findOne(pk).get());
		
		return "/webpage/addedit";

	}
	
	 // Save webpage or create new
    // Save
    @PostMapping("/save")
	public String saveWebpage(@Valid Webpage webpage, BindingResult result, Model model) {

		// if person is null get person from logged in user
		if (webpage.getPerson()== null || webpage.getPerson().getIdPerson()==0) {
			
			System.out.println("\n################ Save new Experience ################\n# ");
			
			// user
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			Users user = userService.findByUsername(authentication.getName());
			
			WebpagePK epk = webpage.getId();
			epk.setIdPerson(user.person.getIdPerson());
			webpage.setId(epk);
		}
		
		// Error handling
		if (result.hasErrors()) {
			
			WebpagePK pk = webpage.getId();
			
			// log
			System.out.println("\n################ Error when saving Webpage ################\n#" + webpageService.findOne(pk).get() + "\n# ");
			
			// log all errors
			for (ObjectError error : result.getAllErrors()) {
				System.out.println("# " + error.toString());
			} 
						
			// models for retry
			model.addAttribute("webpage", webpageService.findOne(pk).get());
			return "/webpage/addedit";
		}

		// Save if all is OK
		webpageService.saveWebpage(webpage);

		return "redirect:/webpages/";
	}
    
    // Delete a webpage
    @Transactional // <- Important to get delete to work
	@RequestMapping(value = "/remove/{id}")
	public String deleteWebpage(@PathVariable int id) {
		
		// webpage
    	WebpagePK pk = new WebpagePK();
		pk.setIdWebpage(id);
		
		// user, person id
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Users user = userService.findByUsername(authentication.getName());
		pk.setIdPerson(user.person.getIdPerson());
		
		// Delete
		webpageService.deleteWebpage(pk);
		
		return "redirect:/webpages/";
	}
	
	// Old - remove if not used?
	@RequestMapping(value = "/person/{id}")
	public String getWebpagesByPerson(@PathVariable String id, Model model) {
		int personId = Integer.parseInt(id);
		model.addAttribute("webpages", webpageService.findByPerson(personId));
		return "/webpage/index";
	}	
	
	// Old - remove if not used?
	// Add -> AddForm
    @GetMapping("/person/{id}/add")
    public String addWebpageFormForPerson(Model model) {
    	System.out.println("adding webpage...");
    	Webpage w = new Webpage();
    	System.out.println(w.toString());
    	model.addAttribute("webpage", w);
        return "/webpage/addedit";
    }
    
 // Old - remove if not used?
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
    
    /*
 // Old - remove if not used?
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
    */
    
 // Old - remove if not used?
 // Delete 
 	@RequestMapping(value = "/remove/{personid}/{webpageid}")
 	public String deleteWebpage(@PathVariable int personid, @PathVariable int webpageid ) {
 		WebpagePK wpk = new WebpagePK();
 	      wpk.setIdWebpage(webpageid);
 	      wpk.setIdPerson(personid);
 		webpageService.deleteWebpage(webpageService.findOne(wpk).get());		
 		return "redirect:/webpages/";
 	}
	
}
