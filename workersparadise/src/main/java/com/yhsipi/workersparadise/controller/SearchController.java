package com.yhsipi.workersparadise.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yhsipi.workersparadise.entities.Certification;
import com.yhsipi.workersparadise.entities.Person;
import com.yhsipi.workersparadise.service.CertificationService;
import com.yhsipi.workersparadise.service.PersonService;

@Controller
@RequestMapping(value="/search")
public class SearchController {

	@Autowired
	private CertificationService certificationService;
	@Autowired
	private PersonService personService;
	
	@GetMapping("/")
	public String displaySearchResults(Model model) {
		model.addAttribute("certifications", model);
		return "/";
	}
	
	
	@RequestMapping(value = "/{searchStr}")
	public String getSearchResult(@PathVariable String searchStr, Model model) {
		List<Certification> c = certificationService.findSearchResult(searchStr);
		
		model.addAttribute("certifications", c);
		return "/search/index";
	}
}
