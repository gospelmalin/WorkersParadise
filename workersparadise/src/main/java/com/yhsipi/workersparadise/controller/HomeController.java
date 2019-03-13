package com.yhsipi.workersparadise.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	// Main entrance to webapplication
	@RequestMapping(value = {"/","home"})
	public String Home() {
		return "home/index";
	}
}
