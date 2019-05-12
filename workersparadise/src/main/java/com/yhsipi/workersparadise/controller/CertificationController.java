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

import com.yhsipi.workersparadise.entities.Certification;
import com.yhsipi.workersparadise.entities.CertificationPK;
import com.yhsipi.workersparadise.service.CertificationService;
import com.yhsipi.workersparadise.service.PersonService;

@Controller
@RequestMapping(value = "/certifications")
public class CertificationController {
	
	@Autowired
	private CertificationService certificationService;
	@Autowired
	private PersonService personService;

	
	// FindAll
		@RequestMapping(value = "/")
		public String getCertifications(Model model) {
			model.addAttribute("certifications", certificationService.findAll());
			return "/certification/index";
		}
		
		// TODO
		// FindAllByPerson --> All other (add, edit, delete) should be based on this)
		
		@RequestMapping(value = "/person/{id}")
		public String getCertificationsByPerson(@PathVariable String id, Model model) {
			int personId = Integer.parseInt(id);
			model.addAttribute("certifications", certificationService.findByPerson(personId));
			return "/certification/index";
		}
		
		//add
		 @GetMapping("/person/{id}/add")
		    public String addCertificationFormForPerson(Model model) {
		    	System.out.println("adding certification...");
		    	Certification c = new Certification();
		    	System.out.println(c.toString());
		    	model.addAttribute("certification", c);
		        return "/certification/addedit";
		    }
		
		//Edit
		 @GetMapping("/edit/{personid}/{certificationid}")
		    public String editCertification(@PathVariable int personid, @PathVariable int certificationid, Model model) {
		      CertificationPK cpk = new CertificationPK();
		      cpk.setIdCertification(certificationid);
		      cpk.setIdPerson(personid);
		      System.out.println("Detta är personId för den som ska ha certifiering ändrad: " + cpk.getIdPerson());
		      model.addAttribute("certification", certificationService.findOne(cpk).get());
		        return "/certification/addedit";
		    }   
		
		//Save
		 @PostMapping("/add")
		    public String saveCertification(@Valid Certification certification, BindingResult result, Model model){
		    	System.out.println("Nu påbörjas save-metoden");
		    	CertificationPK cPK = certification.getId();
		    	System.out.println("jag har ett CertificationPK: " + cPK);
		    	System.out.println("certificationID: " + cPK.getIdCertification() + " för person: " + cPK.getIdPerson());
		    	
		    	
		    	if (result.hasErrors()) {
		    		return "/certification/addedit";
		    	}
		    	
		    	certificationService.saveCertification(certification);
		    	
		        return "redirect:/certifications/";
		    }
		
	    // Delete
		@RequestMapping(value = "/remove/{personid}/{certificationid}")
		public String deleteCertification(@PathVariable int personid, @PathVariable int certificationid) {
			CertificationPK cpk = new CertificationPK();
			cpk.setIdCertification(certificationid);
			cpk.setIdPerson(personid);
			certificationService.deleteCertification(certificationService.findOne(cpk).get());		
			return "redirect:/certifications/";
		}
	
}
