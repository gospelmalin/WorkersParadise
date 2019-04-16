package com.yhsipi.workersparadise.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    //TODO: Kan denna tas bort?
	// Main entrance to webapplication
	//@RequestMapping(value = {"/","home"})
	@GetMapping("/home2")
	public String Home() {
		String password = "123";
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		System.out.println(hashedPassword);
		return "home/index";
	}
}
