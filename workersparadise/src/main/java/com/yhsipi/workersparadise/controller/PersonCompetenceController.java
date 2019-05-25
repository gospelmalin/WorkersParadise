package com.yhsipi.workersparadise.controller;

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
import com.yhsipi.workersparadise.entities.Person;
import com.yhsipi.workersparadise.entities.PersonCompetence;
import com.yhsipi.workersparadise.entities.Users;
import com.yhsipi.workersparadise.service.CompetenceService;
import com.yhsipi.workersparadise.service.PersonCompetenceService;
import com.yhsipi.workersparadise.service.PersonService;
import com.yhsipi.workersparadise.service.UserService;

@Controller
@RequestMapping(value="/personcompetences")
public class PersonCompetenceController {
	
	@Autowired
	private PersonCompetenceService personCompetenceService;
	@Autowired
	private CompetenceService competenceService;
	@Autowired
	private UserService userService;
	
	public PersonCompetenceController() {
		super();
	}

	// Old - might be removed?
	// FindAll
	@GetMapping("/all")
	public String getPersonCompetence(Model model) {
		
		model.addAttribute("personcompetences", personCompetenceService.findAll());
		return "personcompetence/index";
	}
	// Till dashboard, Ta reda på den inloggade användarens person och hämta dennes information
	@RequestMapping(value = "/")
	public String getCompetencesByPerson(Model model) {
		// Get logged in user
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Users user = userService.findByUsername(authentication.getName());
		
		// models based on user
		model.addAttribute("personcompetences", personCompetenceService.findByPerson(user.person.getIdPerson()));
		model.addAttribute("person", user.person);
		return "/personcompetence/index";
	}
			
	// Old - might be removed?
	@GetMapping(value = "/person/{id}")
	public String getCompetencesByPerson(@PathVariable String id, Model model) {
		int personId = Integer.parseInt(id);
		model.addAttribute("personcompetences", personCompetenceService.findByPerson(personId));
		return "/personcompetence/index";
	}
	
	//Create new personal competence
	//add -> AddForm
	 @GetMapping("/add")
	    public String addPersonCompetence(Model model) {    	
	    	model.addAttribute("personcompetence", new PersonCompetence());
	    	model.addAttribute("competences", competenceService.findAll());
	        return "/personcompetence/add";
	    }
	
	// Old - might be removed?
	//add
	 @GetMapping("/person/{id}/add")
	    public String addCompetenceFormForPerson(@PathVariable int id, Model model) {
	    	System.out.println("adding personal competence...");
	    	PersonCompetence pc = new PersonCompetence();
	    	Person person = new Person();
	    	person.setIdPerson(id);
	    	pc.setPerson(person);
	    	System.out.println(pc.toString());
	    	model.addAttribute("personcompetence", pc);
	    	model.addAttribute("competences", competenceService.findAll());
	        return "/personcompetence/add";
	    }

	 
	// Old - might be removed?
	//Edit
	 @GetMapping("/edit/{personid}/{personcompetenceid}")
	    public String editPersonCompetence(@PathVariable int personid, @PathVariable int personcompetenceid, Model model) {
	     Person p = new Person();
	     p.setIdPerson(personid);
	      System.out.println("Detta är personId för den som ska ha kompetence ändrad: " + p.getIdPerson());
	      model.addAttribute("personcompetence", personCompetenceService.findOne(personcompetenceid).get());
	      PersonCompetence pc = personCompetenceService.findOne(personcompetenceid).get();
	      model.addAttribute("competence", pc.getCompetence());
			model.addAttribute("competences", competenceService.findAll());
	        return "/personcompetence/add";
	    }   
	 
	 //Change personal competences, only showing logged in person's data
	//Edit --> Add form
	 @GetMapping("/edit/{personcompetenceid}")
	    public String editPersonCompetence(@PathVariable int personcompetenceid, Model model) {
	     
		// logged in user
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Users user = userService.findByUsername(authentication.getName());
			//TODO
		// person @PathId @loggedinuserPersonId
		Person p = new Person();
		p.setIdPerson(user.person.getIdPerson());
		
		// Personal competence
		PersonCompetence personCompetence = personCompetenceService.findOne(p.getIdPerson()).get();
		
		// models based on loggedin user
		model.addAttribute("personcompetence", personCompetenceService.findOne(personcompetenceid).get());
		model.addAttribute("competence", personCompetence.getCompetence());
		model.addAttribute("competences", competenceService.findAll());
		
	    return "/personcompetence/add";
	    }   

	//Save personal competence or create new
	 @PostMapping("/save")
	 public String savePersonCompetence(@Valid PersonCompetence personCompetence, BindingResult result, Model model) {
		// if person is null, get person from logged in user
		if (personCompetence.getPerson()==null || personCompetence.getPerson().getIdPerson()==0) {
			System.out.println("\n################ Save new Experience ################\n# ");
			
			// user
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			Users user = userService.findByUsername(authentication.getName());
			
			Person p = new Person();
			p.setIdPerson(user.person.getIdPerson());
			personCompetence.setPerson(p);
		}
		 
		// error handling
		if(result.hasErrors()) {
			
			int pcId = personCompetence.getIdPersonCompetence();
			
			// log
			System.out.println("\n################ Errors saving Personal Competence ################\n#" + personCompetenceService.findOne(pcId).get() + "\n# ");
			
			// log all errors
			for (ObjectError error : result.getAllErrors()) {
				System.out.println("# " + error.toString());
			} 
			
			// models for retry
			model.addAttribute("personcompetence", new PersonCompetence());
	    	model.addAttribute("competences", competenceService.findAll());
	        return "/personcompetence/add";
		}
	 
	 	// Save if all is OK
		personCompetenceService.savePersonCompetence(personCompetence);

		return "redirect:/personcompetences/";
	}
		
	 
	// Old - can be removed?
	 /*
	@PostMapping("/add")
	public String savePersonCompetence(@Valid PersonCompetence personCompetence, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			return "personcompetence/add";
		}
		personCompetenceService.savePersonCompetence(personCompetence);
		return "redirect:/personcompetences/";
	}
	*/
	
	 // Delete
		@RequestMapping(value = "/remove/{id}")
		public String deleteCompetence(@PathVariable int id) {
			personCompetenceService.deletePersonCompetence(id);		
			return "redirect:/personcompetences/";
		}
	
  
	
}
