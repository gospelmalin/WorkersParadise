package com.yhsipi.workersparadise.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yhsipi.workersparadise.entities.Education;
import com.yhsipi.workersparadise.entities.EducationPK;
import com.yhsipi.workersparadise.entities.Users;
import com.yhsipi.workersparadise.service.EducationService;
import com.yhsipi.workersparadise.service.UserService;


@Controller
@RequestMapping(value = "/educations")
public class EducationController {
		
	@Autowired
	private EducationService educationService;
	@Autowired
	private UserService userService;
	
	public EducationController() {
		super();
	}
	
	 @InitBinder
	    public void initBinder(WebDataBinder binder) {
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); //yyyy-MM-dd'T'HH:mm:ssZ example
	        dateFormat.setLenient(false);
	        dateFormat.setTimeZone(TimeZone.getTimeZone("CEST"));
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	    }
	
	// Old controller - remove if not to be used?
	// FindAll
	@RequestMapping(value = "/all")
	public String getEducations(Model model) {
		model.addAttribute("educations", educationService.findAll());
		return "/education/index";
	}
	
	//FindAll by logged in person - Get the logged in user and get his/her educations
	@RequestMapping(value = "/")
	public String getEducationsByPerson(Model model) {
		
		// Get logged in user
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Users user = userService.findByUsername(authentication.getName());
		
		// models based on user
		model.addAttribute("educations", educationService.findByPerson(user.person.getIdPerson()));
		model.addAttribute("person", user.person);
		return "/education/index";
	}
	
	// Old - remove if not used?
	// FindAllByPerson --> All other (add, edit, delete) should be based on this)
	@RequestMapping(value = "/person/{id}")
	public String getEducationsByPerson(@PathVariable String id, Model model) {
		int personId = Integer.parseInt(id);
		model.addAttribute("educations", educationService.findByPerson(personId));
		return "/education/index";
	}	
	
	// Create new education
	@GetMapping("/add")
	public String addEducation(Model model) {
			
	// Models
	model.addAttribute("education",  new Education());
	
	return "/education/addedit";
	}
	
	// Old - remove if not used
	// Add -> AddForm
	@GetMapping("/person/{id}/add")
    public String addForm(Model model) {
    	// System.out.println("adding education..");
    	Education ed = new Education();
    	System.out.println(ed.toString());
    	model.addAttribute("education", ed);
        return "/education/addedit";
    }

	//Edit - update educations - show data for logged in person only
	@GetMapping("/edit/{educationid}")
	public String editEducation(@PathVariable int educationid, Model model) {
		
		// logged in user
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Users user = userService.findByUsername(authentication.getName());
		
		// eduationPK @PathId @loggedinuserPersonId
		EducationPK pk = new EducationPK();
		pk.setIdEducation(educationid);
		pk.setIdPerson(user.person.getIdPerson());

		// Education
		Education education = educationService.findOne(pk).get();
		
		// models based on loggedin user
		model.addAttribute("education", educationService.findOne(pk).get());
		
		return "/experience/edit";

	}
	
	// Old - remove if not used?
    // Edit -> AddForm
    @GetMapping("/edit/{personid}/{educationid}")
    public String editEducation(@PathVariable int personid, @PathVariable int educationid, Model model) {
      EducationPK edpk = new EducationPK();
      edpk.setIdEducation(educationid);
      edpk.setIdPerson(personid);
      model.addAttribute("education", educationService.findOne(edpk).get());
        return "/education/addedit";
    }    
    
    // Save education or create new
    // Save
    @PostMapping("/save")
	public String saveEducation(@Valid Education education, BindingResult result, Model model) {

		// if person is null get person from logged in user
		if (education.getPerson()== null || education.getPerson().getIdPerson()==0) {
			
			System.out.println("\n################ Save new Experience ################\n# ");
			
			// user
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			Users user = userService.findByUsername(authentication.getName());
			
			EducationPK epk = education.getId();
			epk.setIdPerson(user.person.getIdPerson());
			education.setId(epk);
		}
		
		// Error handling
		if (result.hasErrors()) {
			
			EducationPK pk = education.getId();
			
			// log
			System.out.println("\n################ Error when saving Education ################\n#" + educationService.findOne(pk).get() + "\n# ");
			
			// log all errors
			for (ObjectError error : result.getAllErrors()) {
				System.out.println("# " + error.toString());
			} 
						
			// models for retry
			model.addAttribute("education", educationService.findOne(pk).get());
			return "/education/addedit";
		}

		// spara om det om allt är ok
		educationService.saveEducation(education);

		return "redirect:/educations/";
	}
    
    
    // Old - remove if not used?
    // Save
    /*
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
    */
    
    // Delete an education
    @Transactional // <- Important to get delete to work
	@RequestMapping(value = "/remove/{id}")
	public String deleteEducation(@PathVariable int id) {
		
		// education
		EducationPK pk = new EducationPK();
		pk.setIdEducation(id);
		
		// user, person id
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Users user = userService.findByUsername(authentication.getName());
		pk.setIdPerson(user.person.getIdPerson());
		
		// Delete
		educationService.deleteEducation(pk);
		
		return "redirect:/educations/";
	}
    
    // Old - remove?
    // Delete
	@RequestMapping(value = "/remove/{personid}/{educationid}")
	public String deleteEducation(@PathVariable int personid, @PathVariable int educationid) {
		EducationPK edpk = new EducationPK();
		edpk.setIdEducation(educationid);
		edpk.setIdPerson(personid);
		educationService.deleteEducation(educationService.findOne(edpk).get());		
		return "redirect:/educations/";
	}

}
