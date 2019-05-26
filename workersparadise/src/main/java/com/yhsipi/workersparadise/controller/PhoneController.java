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

import com.yhsipi.workersparadise.entities.Phone;
import com.yhsipi.workersparadise.entities.PhonePK;
import com.yhsipi.workersparadise.entities.Users;
import com.yhsipi.workersparadise.service.PhoneService;
import com.yhsipi.workersparadise.service.TypeService;
import com.yhsipi.workersparadise.service.UserService;

@Controller
@RequestMapping(value = "/phone")
public class PhoneController {

	@Autowired
	private PhoneService phoneService;
	@Autowired
	private TypeService typeService;
	@Autowired
	private UserService userService;
	
	public PhoneController() {
		super();
	}
	
	//FindAll by logged in person - Get the logged in user and get his/her phones
	@RequestMapping(value = "/")
	public String getPhonesByPerson(Model model) {
		
		// Get logged in user
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Users user = userService.findByUsername(authentication.getName());
		
		// models based on user
		model.addAttribute("phones", phoneService.findByPerson(user.person.getIdPerson()));
		model.addAttribute("person", user.person);
		return "/phone/index";
	}
			
		// Create new phone
	// Add -> AddForm
		@GetMapping("/add")
		public String addPhone(Model model) {
				
		// Models
		model.addAttribute("phone",  new Phone());
		model.addAttribute("types", typeService.findAll());
		
		return "/phone/add";
		}
		
		//Edit - update phones - show data for logged in person only
		@GetMapping("/edit/{phoneid}")
		public String editPhone(@PathVariable int phoneid, Model model) {
			
			// logged in user
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			Users user = userService.findByUsername(authentication.getName());
			
			// phonePK @PathId @loggedinuserPersonId
			PhonePK pk = new PhonePK();
			pk.setIdPhone(phoneid);
			pk.setIdPerson(user.person.getIdPerson());

			// Phone
			Phone phone = phoneService.findOne(pk).get();
			
			// models based on loggedin user
			model.addAttribute("phone", phoneService.findOne(pk).get());
			model.addAttribute("type", phone.getType());
			model.addAttribute("types", typeService.findAll());
			
			return "/phone/add";

		}
		
		 // Save phone or create new
	    // Save
	    @PostMapping("/save")
		public String savePhone(@Valid Phone phone, BindingResult result, Model model) {

			// if person is null get person from logged in user
			if (phone.getPerson()== null || phone.getPerson().getIdPerson()==0) {
				
				System.out.println("\n################ Save new Phone ################\n# ");
				
				// user
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				Users user = userService.findByUsername(authentication.getName());
				
				PhonePK ppk = phone.getId();
				ppk.setIdPerson(user.person.getIdPerson());
				phone.setId(ppk);
			}
			
			// Error handling
			if (result.hasErrors()) {
				
				PhonePK pk = phone.getId();
				
				// log
				System.out.println("\n################ Error when saving Phone ################\n#" + phoneService.findOne(pk).get() + "\n# ");
				
				// log all errors
				for (ObjectError error : result.getAllErrors()) {
					System.out.println("# " + error.toString());
				} 
							
				// models for retry
				model.addAttribute("phone", phoneService.findOne(pk).get());
				model.addAttribute("types", typeService.findAll());
				return "/phone/add";
			}

			// Save if all is OK
			phoneService.savePhone(phone);

			return "redirect:/phone/";
		}
	    
	    // Delete a phone
	    @Transactional // <- Important to get delete to work
		@RequestMapping(value = "/remove/{id}")
		public String deletePhone(@PathVariable int id) {
			
			// phone
	    	PhonePK pk = new PhonePK();
			pk.setIdPhone(id);
			
			// user, person id
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			Users user = userService.findByUsername(authentication.getName());
			pk.setIdPerson(user.person.getIdPerson());
			
			// Delete
			phoneService.deletePhone(pk);
			
			return "redirect:/phone/";
		}
	
	// Old - remove if not to be used?
	@GetMapping("/all")
	public String Phone(Model model) {
		model.addAttribute("phones", phoneService.findAll());
		return "phone/index";
	}
	
	// Old - remove if not to be used?
	@GetMapping("/edit/{personId}/{phoneId}")
	public String edit(@PathVariable int personId, @PathVariable int phoneId, Model model) {
		
		PhonePK pPK = new PhonePK();
		pPK.setIdPerson(personId);
		pPK.setIdPhone(phoneId);
		Phone p = phoneService.findOne(pPK).get();
		model.addAttribute("phone", p);
		return "/phone/add";	
		
	}
	
	// Old - remove if not to be used?
	@GetMapping("/person/{personId}")
	public String getPhoneByPerson(@PathVariable int personId, Model model) {
		
		model.addAttribute("phones", phoneService.findByPerson(personId));
		return "/phone/index";
	}
	
	/*
	// Old - remove if not to be used?
	@GetMapping("/add")
	public String add(Model model) {

		model.addAttribute("phone", new Phone());
		return "/phone/add";
	}
	*/
	// Old - remove if not to be used?
	/*
	@PostMapping("/add")
	public String savePhone(@Valid Phone phone, BindingResult result, Model model) {

		if(result.hasErrors()) {
			return "phone/add";
		}
		phoneService.savePhone(phone);
		return "redirect:/phone/";
	}
	*/
	
	// Old - remove if not to be used?
	@Transactional
	@RequestMapping(value = "/remove/{personId}/{phoneId}")
	public String removePhone(@PathVariable int personId, @PathVariable int phoneId) {
		PhonePK pPK = new PhonePK();
		pPK.setIdPerson(personId);
		pPK.setIdPhone(phoneId);
		phoneService.deletePhone(phoneService.findOne(pPK).get());
		return "redirect:/phone/";
	}
}
