package com.yhsipi.workersparadise.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.validation.Valid;

import com.yhsipi.workersparadise.entities.Person;
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
import com.yhsipi.workersparadise.service.PersonService;



@Controller
@RequestMapping(value = "/persons")
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	 @InitBinder
	    public void initBinder(WebDataBinder binder) {
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); //yyyy-MM-dd'T'HH:mm:ssZ example
	        dateFormat.setLenient(false);
	        dateFormat.setTimeZone(TimeZone.getTimeZone("CEST"));
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	    }
	
	// FindAll
	@RequestMapping(value = "/")
	public String getPersons(Model model) {
		model.addAttribute("persons", personService.findAll());
		return "/person/index";
	}
	
	// Add -> AddForm
    @GetMapping("/addperson")
    public String addForm(Model model) {
        model.addAttribute("person", new Person());
        return "/person/addperson";
    }

    // Edit -> AddForm
    @GetMapping("/edit/{id}")
    public String editPerson(@PathVariable int id, Model model) {
        model.addAttribute("person", personService.findOne(id));
        return "/person/addperson";
    }    
    
    // Save
    @PostMapping("/addperson")
    public String savePerson(@Valid Person person, BindingResult result, Model model){
    	
    	if (result.hasErrors()) {
    		return "/person/addperson";
    	}
    	
    	personService.savePerson(person);
    	
        return "redirect:/persons/";
    }
    
    // Delete
	@RequestMapping(value = "/remove/{id}")
	public String deletePerson(@PathVariable int id) {
		personService.deletePerson(id);		
		return "redirect:/persons/";
	}
	
	/*
	// save or update person
		// 1. @ModelAttribute bind form value
		// 2. @Validated form validator
		// 3. RedirectAttributes for flash value
		@RequestMapping(value = "/persons", method = RequestMethod.POST)
		public String saveOrUpdateUser(@ModelAttribute("addperson") @Validated Person person,
				BindingResult result, Model model, 
				final RedirectAttributes redirectAttributes) {

		//	logger.debug("saveOrUpdatePerson() : {}", person);

			if (result.hasErrors()) {
			//	populateDefaultModel(model);
				return "persons/person";
			} else {

				// Add message to flash scope
				redirectAttributes.addFlashAttribute("css", "success");
				if(person.isNew()){
				  redirectAttributes.addFlashAttribute("msg", "Person added successfully!");
				}else{
				  redirectAttributes.addFlashAttribute("msg", "Person updated successfully!");
				}
				
				personService.saveOrUpdate(person);
				
				// POST/REDIRECT/GET
				return "redirect:/persons/" + person.getIdPerson();

				// POST/FORWARD/GET
				// return "person/list";

			}

		}
		
	
		// show add person form
		@RequestMapping(value = "/persons/add", method = RequestMethod.GET)
		public String showAddPersonForm(Model model) {

		//	logger.debug("showAddPersonForm()");

			Person person = new Person();

			// set default value
			person.setFirstName("förnamn");
			person.setMiddleName("mellannamn");
			person.setLastName("efternamn");
			person.setGender("kön");
			person.setBirthdate(null);
			model.addAttribute("person", person);

		//	populateDefaultModel(model);

			return "persons/addperson";

		}
	
		
	// SavePerson
		@RequestMapping(value = "/addPerson", method = RequestMethod.POST)
		public String addPerson(Model model) {
			model.addAttribute("person", personService.savePerson(person));
			return "/person/addPerson";
		}
*/
		
}
