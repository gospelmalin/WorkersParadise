package com.yhsipi.workersparadise.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

	@RequestMapping(method = RequestMethod.GET, value = "/")
	public String getSearchResult(@RequestParam("searchStr") String searchStr, Model model) {
		List<Competence> c = competenceService.findByCompetenceName(searchStr);
		int competenceId = c.get(0).getIdCompetence();
		List<PersonCompetence> pc = personCompetenceService.findByCompetence(competenceId);
		model.addAttribute("personCompetences", pc);
		model.addAttribute("competence", c.get(0));

		return "/search/index";
	}

}