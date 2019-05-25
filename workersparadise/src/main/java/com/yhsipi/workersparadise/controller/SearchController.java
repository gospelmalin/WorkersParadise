package com.yhsipi.workersparadise.controller;

import java.util.ArrayList;
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
		List<Integer> cIdList = new ArrayList<Integer>();
		int loop = 0;
		for (Competence comp : c) {
			cIdList.add(comp.getIdCompetence());
		}
		List<PersonCompetence> pcList = new ArrayList<PersonCompetence>();
		for (Integer i : cIdList) {
			pcList.addAll(personCompetenceService.findByCompetence(i));
			loop++;
		}
		List<PersonCompetence> noDuplicatespcList = pcList;
		noDuplicatespcList.add(pcList.get(0));
		for (int i = 0; i < pcList.size(); i++) {
			for (int j = 0; j < noDuplicatespcList.size(); j++) {
				if(noDuplicatespcList.get(i).equals(pcList.get(j))) {
					noDuplicatespcList.remove(i);
				}
				
			}
			
		}
		
		model.addAttribute("personCompetences", noDuplicatespcList);
		model.addAttribute("searchStr", searchStr);

		return "/search/index";
	}

}