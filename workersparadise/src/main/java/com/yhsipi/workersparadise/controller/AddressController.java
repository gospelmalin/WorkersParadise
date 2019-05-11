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

import com.yhsipi.workersparadise.entities.Address;
import com.yhsipi.workersparadise.entities.AddressPK;
import com.yhsipi.workersparadise.service.AddressService;
import com.yhsipi.workersparadise.service.PersonService;

@Controller
@RequestMapping(value = "/addresses")
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	@Autowired
	private PersonService personService;

	// FindAll
			@RequestMapping(value = "/")
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
			    public String addAddressFormForPerson(Model model) {
			    	System.out.println("adding address...");
			    	Address a = new Address();
			    	System.out.println(a.toString());
			    	model.addAttribute("address", a);
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
			@RequestMapping(value = "/remove/{id}")
			public String deleteAddress(@PathVariable int id) {
				addressService.deleteAddress(id);		
				return "redirect:/addresses/";
			}
	
}
