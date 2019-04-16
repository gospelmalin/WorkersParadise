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
import org.springframework.web.servlet.ModelAndView;

import com.yhsipi.workersparadise.model.User;
import com.yhsipi.workersparadise.service.UserService;

@Controller
@RequestMapping(value = "/users")
public class UserController {
	/*

	@Autowired
	private UserService userService;
	
	// FindAll
	@RequestMapping(value = "/")
	public String getUsers(Model model) {
		model.addAttribute("users", userService.findAll());
		return "/user/index";
	}
	
	// FindOne
	@RequestMapping(value = "/{id}")
	public ModelAndView getUser(@PathVariable int id) {
		ModelAndView mav = new ModelAndView("/user/user");
		mav.addObject("user", userService.findOne(id)); 
		return mav;
	}
	
	// Add -> AddForm
    @GetMapping("/adduser")
    public String addForm(Model model) {
        model.addAttribute("user", new Users());
        return "/user/adduser";
    }

    // Edit -> AddForm
    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable int id, Model model) {
        model.addAttribute("user", userService.findOne(id));
        return "/user/adduser";
    }    
    
    // Save
    @PostMapping("/adduser")
    public String saveUser(@Valid Users user, BindingResult result, Model model){
    	
    	if (result.hasErrors()) {
    		return "/user/adduser";
    	}
    	
    	userService.saveUser(user);
    	
        return "redirect:/users/";
    }
    
    // Delete
	@RequestMapping(value = "/remove/{id}")
	public String deleteUser(@PathVariable int id) {
		userService.deleteUser(id);		
		return "redirect:/users/";
	}
    
    */
}