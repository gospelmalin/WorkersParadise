package com.yhsipi.workersparadise.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
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

import com.yhsipi.workersparadise.entities.Address;
import com.yhsipi.workersparadise.entities.AddressPK;
import com.yhsipi.workersparadise.entities.Email;
import com.yhsipi.workersparadise.entities.EmailPK;
import com.yhsipi.workersparadise.entities.Person;
import com.yhsipi.workersparadise.entities.Users;
import com.yhsipi.workersparadise.service.AddressService;
import com.yhsipi.workersparadise.service.PersonService;
import com.yhsipi.workersparadise.service.TypeService;
import com.yhsipi.workersparadise.service.UserService;

@Controller
@RequestMapping(value = "/addresses")
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	@Autowired
	private TypeService typeService;
	@Autowired
	private UserService userService;
	
	public AddressController() {
		super();
	}
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	//FindAll by logged in person - Get the logged in user and get his/her addresses
	@RequestMapping(value = "/")
	public String getAddressesByPerson(Model model) {
		
		// Get logged in user
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Users user = userService.findByUsername(authentication.getName());
		
		// models based on user
		model.addAttribute("addresses", addressService.findByPerson(user.person.getIdPerson()));
		model.addAttribute("person", user.person);
		return "/address/index";
	}
			
		// Create new email
	// Add -> AddForm
		@GetMapping("/add")
		public String addAddress(Model model) {
				
		// Models
		model.addAttribute("address",  new Address());
		model.addAttribute("types", typeService.findAll());
		
		return "/address/addedit";
		}
		
		//Edit - update addresses - show data for logged in person only
		@GetMapping("/edit/{addressid}")
		public String editAddress(@PathVariable int addressid, Model model) {
			
			// logged in user
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			Users user = userService.findByUsername(authentication.getName());
			
			// addressPK @PathId @loggedinuserPersonId
			AddressPK pk = new AddressPK();
			pk.setIdAddress(addressid);
			pk.setIdPerson(user.person.getIdPerson());

			// Address
			Address address = addressService.findOne(pk).get();
			
			// models based on loggedin user
			model.addAttribute("address", addressService.findOne(pk).get());
			model.addAttribute("type", address.getType());
			model.addAttribute("types", typeService.findAll());
			
			return "/address/addedit";

		}
		
		 // Save address or create new
	    // Save
	    @PostMapping("/save")
		public String saveAddress(@Valid Address address, BindingResult result, Model model) {

			// if person is null get person from logged in user
			if (address.getPerson()== null || address.getPerson().getIdPerson()==0) {
				
				System.out.println("\n################ Save new Address ################\n# ");
				
				// user
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				Users user = userService.findByUsername(authentication.getName());
				
				AddressPK apk = address.getId();
				apk.setIdPerson(user.person.getIdPerson());
				address.setId(apk);
			}
			
			// Error handling
			if (result.hasErrors()) {
				
				AddressPK pk = address.getId();
				
				// log
				System.out.println("\n################ Error when saving Address ################\n#" + addressService.findOne(pk).get() + "\n# ");
				
				// log all errors
				for (ObjectError error : result.getAllErrors()) {
					System.out.println("# " + error.toString());
				} 
							
				// models for retry
				model.addAttribute("address", addressService.findOne(pk).get());
				model.addAttribute("types", typeService.findAll());
				return "/address/addedit";
			}

			// Save if all is OK
			addressService.saveAddress(address);

			return "redirect:/addresses/";
		}
	    
	    // Delete a phone
	    @Transactional // <- Important to get delete to work
		@RequestMapping(value = "/remove/{id}")
		public String deleteAddress(@PathVariable int id) {
			
			// email
	    	AddressPK pk = new AddressPK();
			pk.setIdAddress(id);
			
			// user, person id
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			Users user = userService.findByUsername(authentication.getName());
			pk.setIdPerson(user.person.getIdPerson());
			
			// Delete
			addressService.deleteAddress(pk);
			
			return "redirect:/addresses/";
		}

	/*
	//OLD - might be used for admin, otherwise remove?
	// FindAll
			@RequestMapping(value = "/all")
			public String getAddresses(Model model) {
				model.addAttribute("addresses", addressService.findAll());
				return "/address/index";
			}
			
			// TODO
			// FindAllByPerson --> All other (add, edit, delete) should be based on this)
			
			@RequestMapping(value = "/person/{id}")
			public String getAddressesByPerson(@PathVariable String id, Model model) {
				int personId = Integer.parseInt(id);
				model.addAttribute("addresses", addressService.findByPerson(personId));
				return "/address/index";
			}
			
			//add
			 @GetMapping("/person/{id}/add")
			    public String addAddressFormForPerson(@PathVariable int id, Model model) {
			    	System.out.println("adding address...");
			    	Address a = new Address();
			    	AddressPK aPK = new AddressPK();
			    	aPK.setIdPerson(id);
			    	a.setId(aPK);
			    	model.addAttribute("address", a);
			        model.addAttribute("types", typeService.findAll());
			        return "/address/addedit";
			    }
			
			//Edit
			 @GetMapping("/edit/{personid}/{addressid}")
			    public String editAddress(@PathVariable int personid, @PathVariable int addressid, Model model) {
			      AddressPK apk = new AddressPK();
			      apk.setIdAddress(addressid);
			      apk.setIdPerson(personid);
			      System.out.println("Detta är personId för den som ska ha adress ändrad: " + apk.getIdPerson());
			      model.addAttribute("address", addressService.findOne(apk).get());
			      model.addAttribute("types", typeService.findAll());
			        return "/address/addedit";
			    }   
			
			//Save
			 @PostMapping("/add")
			    public String saveAddress(@Valid Address address, BindingResult result, Model model){
			    	System.out.println("Nu påbörjas save-metoden");
			    	AddressPK aPK = address.getId();
			    	System.out.println("jag har ett AddressPK: " + aPK);
			    	System.out.println("addressID: " + aPK.getIdAddress() + " för person: " + aPK.getIdPerson());
			    	
			    	
			    	if (result.hasErrors()) {
			    		return "/address/addedit";
			    	}
			    	
			    	addressService.saveAddress(address);
			    	
			        return "redirect:/addresses/";
			    }
			
		    // Delete
			@RequestMapping(value = "/remove/{personid}/{addressid}")
			public String deleteAddress(@PathVariable int personid, @PathVariable int addressid) {
				AddressPK apk = new AddressPK();
				apk.setIdAddress(addressid);
				apk.setIdPerson(personid);
				addressService.deleteAddress(addressService.findOne(apk).get());		
				return "redirect:/addresses/";
			}
	*/
}
