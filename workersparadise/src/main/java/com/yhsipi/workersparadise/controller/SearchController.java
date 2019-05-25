package com.yhsipi.workersparadise.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yhsipi.workersparadise.entities.Competence;
import com.yhsipi.workersparadise.entities.PersonCompetence;
import com.yhsipi.workersparadise.service.CompetenceService;
import com.yhsipi.workersparadise.service.PersonCompetenceService;

@Controller
@RequestMapping(value="/search")
public class SearchController {

	@Autowired
	private PersonCompetenceService personCompetenceService;
	@Autowired
	private CompetenceService competenceService;
	
	@GetMapping("/")
	public String displaySearchResults(@Valid String searchStr,Model model) {
		return "/search/" + searchStr;
	}
	
	
	@RequestMapping(value = "/{searchStr}")
	public String getSearchResult(@PathVariable String searchStr, Model model) {
		
		List<Competence> c = competenceService.findByCompetenceName(searchStr);
		int competenceId = c.get(0).getIdCompetence();
		List<PersonCompetence> pc = personCompetenceService.findByCompetence(competenceId);
		model.addAttribute("personCompetences", pc);
		model.addAttribute("competence", c.get(0));

		return "/search/index";
	}

}