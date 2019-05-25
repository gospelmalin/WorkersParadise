package com.yhsipi.workersparadise.controller;

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

import com.yhsipi.workersparadise.entities.Certification;
import com.yhsipi.workersparadise.entities.CertificationPK;
import com.yhsipi.workersparadise.entities.Education;
import com.yhsipi.workersparadise.entities.EducationPK;
import com.yhsipi.workersparadise.entities.Users;
import com.yhsipi.workersparadise.service.CertificationService;
import com.yhsipi.workersparadise.service.UserService;

@Controller
@RequestMapping(value = "/certifications")
public class CertificationController {
	
	@Autowired
	private CertificationService certificationService;
	@Autowired
	private UserService userService;
	
	public CertificationController() {
		super();
	}

	// Old controller - remove if not to be used?
	// FindAll
		@RequestMapping(value = "/all")
		public String getCertifications(Model model) {
			model.addAttribute("certifications", certificationService.findAll());
			return "/certification/index";
		}
	
		//FindAll by logged in person - Get the logged in user and get his/her educations
		@RequestMapping(value = "/")
		public String getCertificationsByPerson(Model model) {
			
			// Get logged in user
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			Users user = userService.findByUsername(authentication.getName());
			
			// models based on user
			model.addAttribute("certifications", certificationService.findByPerson(user.person.getIdPerson()));
			model.addAttribute("person", user.person);
			return "/certification/index";
		}
		
		// Create new education
		@GetMapping("/add")
		public String addCertification(Model model) {
				
		// Models
		model.addAttribute("certification",  new Certification());
		
		return "/certification/addedit";
		}
		
		
		// Old - remove if not used?
		// FindAllByPerson --> All other (add, edit, delete) should be based on this)
		@RequestMapping(value = "/person/{id}")
		public String getCertificationsByPerson(@PathVariable String id, Model model) {
			int personId = Integer.parseInt(id);
			model.addAttribute("certifications", certificationService.findByPerson(personId));
			return "/certification/index";
		}
		
		// Old - remove if not used
		//add
		 @GetMapping("/person/{id}/add")
		    public String addCertificationFormForPerson(Model model) {
		    	System.out.println("adding certification...");
		    	Certification c = new Certification();
		    	System.out.println(c.toString());
		    	model.addAttribute("certification", c);
		        return "/certification/addedit";
		    }
		
		 // Old - remove if not to be used
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
		 
		//Edit - update educations - show data for logged in person only
			@GetMapping("/edit/{certificationid}")
			public String editCertification(@PathVariable int certificationid, Model model) {
				
				// logged in user
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				Users user = userService.findByUsername(authentication.getName());
				
				// certificationPK @PathId @loggedinuserPersonId
				CertificationPK pk = new CertificationPK();
				pk.setIdCertification(certificationid);
				pk.setIdPerson(user.person.getIdPerson());

				// Certification
				Certification certification = certificationService.findOne(pk).get();
				
				// models based on loggedin user
				model.addAttribute("certification", certificationService.findOne(pk).get());
				
				return "/certification/addedit";

			}
			
			 // Save certification or create new
		    // Save
		    @PostMapping("/save")
			public String saveCertification(@Valid Certification certification, BindingResult result, Model model) {

				// if person is null get person from logged in user
				if (certification.getPerson()== null || certification.getPerson().getIdPerson()==0) {
					
					System.out.println("\n################ Save new Experience ################\n# ");
					
					// user
					Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
					Users user = userService.findByUsername(authentication.getName());
					
					CertificationPK epk = certification.getId();
					epk.setIdPerson(user.person.getIdPerson());
					certification.setId(epk);
				}
				
				// Error handling
				if (result.hasErrors()) {
					
					CertificationPK pk = certification.getId();
					
					// log
					System.out.println("\n################ Error when saving Certification ################\n#" + certificationService.findOne(pk).get() + "\n# ");
					
					// log all errors
					for (ObjectError error : result.getAllErrors()) {
						System.out.println("# " + error.toString());
					} 
								
					// models for retry
					model.addAttribute("certification", certificationService.findOne(pk).get());
					return "/certification/addedit";
				}

				// Save if all is OK
				certificationService.saveCertification(certification);

				return "redirect:/certifications/";
			}
		    
		    // Delete a certification
		    @Transactional // <- Important to get delete to work
			@RequestMapping(value = "/remove/{id}")
			public String deleteCertification(@PathVariable int id) {
				
				// certification
		    	CertificationPK pk = new CertificationPK();
				pk.setIdCertification(id);
				
				// user, person id
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				Users user = userService.findByUsername(authentication.getName());
				pk.setIdPerson(user.person.getIdPerson());
				
				// Delete
				certificationService.deleteCertification(pk);
				
				return "redirect:/certifications/";
			}
/*
		// Old - remove if not to be used	
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
		    */
		
		// Old - remove?
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
