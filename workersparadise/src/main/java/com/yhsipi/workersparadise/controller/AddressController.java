package com.yhsipi.workersparadise.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yhsipi.workersparadise.entities.Address;
import com.yhsipi.workersparadise.entities.AddressPK;
import com.yhsipi.workersparadise.entities.Users;
import com.yhsipi.workersparadise.service.AddressService;
import com.yhsipi.workersparadise.service.UserService;

@Controller
@RequestMapping(value = "/addresses")
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	@Autowired
	private UserService userService;
	
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
			 @GetMapping("/add")
			    public String addAddressFormForPerson(Model model) {
					
				 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
					Users user = userService.findByUsername(authentication.getName());
			    	Address a = new Address();
			    	AddressPK aPK = new AddressPK();
			    	aPK.setIdPerson(user.person.getIdPerson());
			    	a.setId(aPK);
			    	model.addAttribute("address", a);
			        return "/address/addedit";
			    }
			
			//Edit
			 @GetMapping("/edit/{addressid}")
			    public String editAddress(@PathVariable int addressid, Model model) {
			      AddressPK apk = new AddressPK();
				  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				  Users user = userService.findByUsername(authentication.getName());
			      apk.setIdAddress(addressid);
			      apk.setIdPerson(user.person.getIdPerson());
			      model.addAttribute("address", addressService.findOne(apk).get());
			        return "/address/addedit";
			    }   
			
			//Save
			 @PostMapping("/add")
			    public String saveAddress(@Valid Address address, BindingResult result, Model model){
			    	address.getId();
			    	
			    	
			    	if (result.hasErrors()) {
			    		return "/address/addedit";
			    	}
			    	
			    	addressService.saveAddress(address);
			    	
			        return "redirect:/addresses/";
			    }
			
		    // Delete
			@RequestMapping(value = "/remove/{addressid}")
			public String deleteAddress(@PathVariable int addressid) {
				
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				Users user = userService.findByUsername(authentication.getName());
				AddressPK apk = new AddressPK();
				apk.setIdAddress(addressid);
				apk.setIdPerson(user.person.getIdPerson());
				addressService.deleteAddress(addressService.findOne(apk).get());		
				return "redirect:/addresses/";
			}
	
}
