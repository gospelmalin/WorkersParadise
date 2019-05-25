package com.yhsipi.workersparadise.controller;

import java.util.Optional;

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

import com.yhsipi.workersparadise.entities.About;
import com.yhsipi.workersparadise.entities.Users;
import com.yhsipi.workersparadise.service.AboutService;
import com.yhsipi.workersparadise.service.PersonService;
import com.yhsipi.workersparadise.service.UserService;

@Controller
@RequestMapping(value = "/about")
public class AboutController {

	@Autowired
	private AboutService aboutService;
	@Autowired
	private PersonService personService;
	@Autowired
	private UserService userService;
	
	@GetMapping("/all")
	public String AboutAll(Model model) {
		model.addAttribute("abouts", aboutService.findAll());
		return "about/index";
	}
	
	@GetMapping("/")
	public String About(Model model) {
		
		// Get logged in user
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Users user = userService.findByUsername(authentication.getName());
		
		About about = aboutService.findByPerson(user.person.getIdPerson());
		
		if (about == null) {
			about = new About();
			about.setIdPerson(user.person.getIdPerson());
		}

		model.addAttribute("about", about);
		return "about/dashboard";
	}
	
	@GetMapping("/edit/{id}")
	public String About(@PathVariable int id, Model model) {
		
		// Get logged in user
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Users user = userService.findByUsername(authentication.getName());
		
		Optional<About> a = aboutService.findOne(id);
		
		if (a.get().getIdPerson() != user.person.getIdPerson())
			return "redirect:/about/"; 							 	// TODO en redirect till en felsida 
				
		model.addAttribute("about", a);
		return "/about/edit";
	}
		
	@PostMapping("/save")
	public String saveAbout(@Valid About about, BindingResult result, Model model) {
		
		about.setPerson(personService.findOne(about.getIdPerson()));
		
		if(result.hasErrors()) {
			// log
			System.out.println("\n################ Save Erorrs in About ################\n#" + about + "\n# ");
			
			// log all errors
			for (ObjectError error : result.getAllErrors()) {
				System.out.println("# " + error.toString());
			} 
			model.addAttribute("about", about);
			return "about/edit";
		}
		aboutService.saveAbout(about);
		return "redirect:/about/";
	}
	
	@Transactional
	@RequestMapping(value="/remove/{aboutId}")
	public String deleteAbout(@PathVariable int aboutId) {
		aboutService.deleteAbout(aboutId);
		return "redirect:/about/";
	}
	
}
