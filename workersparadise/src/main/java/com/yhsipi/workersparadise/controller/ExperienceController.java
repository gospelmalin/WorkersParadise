package com.yhsipi.workersparadise.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.transaction.Transactional;
import javax.validation.Valid;
import com.yhsipi.workersparadise.entities.ProfessionalExperience;
import com.yhsipi.workersparadise.entities.ProfessionalExperiencePK;
import com.yhsipi.workersparadise.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.yhsipi.workersparadise.service.CompanyService;
import com.yhsipi.workersparadise.service.ExperienceService;
import com.yhsipi.workersparadise.service.UserService;

@Controller
@RequestMapping(value = "/experience")
public class ExperienceController {

	@Autowired
	private ExperienceService experienceService;

	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private UserService userService;

	
	public ExperienceController() {
		super();
	}

	@InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); //yyyy-MM-dd'T'HH:mm:ssZ example
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

	// Den gamla controllerna, den kan vi ta bort senare om den inte ska användas
	// FindAll
	@RequestMapping(value = "/all")
	public String getExperiences(Model model) {

		model.addAttribute("experiences", experienceService.findAll());
		
		return "/experience/index";
	}

	
	// Till dashboard, Ta reda på den inloggade användarens person och hämta dennes erfarenheter
	@RequestMapping(value = "/")
	public String getExperiencesByPerson(Model model) {
		
		// Get logged in user
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Users user = userService.findByUsername(authentication.getName());
		
		// models based on user
		model.addAttribute("experiences", experienceService.findByPerson(user.person.getIdPerson()));
		model.addAttribute("person", user.person);
		return "/experience/dashboard";
	}

	// Skapa nya erfarenheter
	// Add -> AddForm
	@GetMapping("/add")
	public String addExperience(Model model) {
			
		// Models
		model.addAttribute("experience",  new ProfessionalExperience());
		model.addAttribute("companies", companyService.findAll());
		
		return "/experience/edit";
	}

	// Gamla controllern, kan tas bort senare
	// Edit -> AddForm
	@GetMapping("/edit/{personid}/{experienceid}")
	public String editExperience(@PathVariable int personid, @PathVariable int experienceid, Model model) {
		
		ProfessionalExperiencePK pk = new ProfessionalExperiencePK();
		pk.setIdProfExperience(experienceid);
		pk.setIdPerson(personid);

		ProfessionalExperience experience = experienceService.findOne(pk).get();
		
		model.addAttribute("experience", experience);
		model.addAttribute("company", experience.getCompany());
		model.addAttribute("companies", companyService.findAll());
		
		return "/experience/edit";

	}

	
	// Ändra, uppdtatera erfarenheter, visa bara den inloggades data
	// Edit -> AddForm
	@GetMapping("/edit/{experienceid}")
	public String editExperience(@PathVariable int experienceid, Model model) {
		
		// logged in user
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Users user = userService.findByUsername(authentication.getName());
		
		// experiencePK @PathId @loggedinuserPersonId
		ProfessionalExperiencePK pk = new ProfessionalExperiencePK();
		pk.setIdProfExperience(experienceid);
		pk.setIdPerson(user.person.getIdPerson());

		// Experience
		ProfessionalExperience experience = experienceService.findOne(pk).get();
		
		// models based on loggedin user
		model.addAttribute("experience", experienceService.findOne(pk).get());
		model.addAttribute("company", experience.getCompany());
		model.addAttribute("companies", companyService.findAll());
		
		return "/experience/edit";

	}
	 
	// Spara erfarenhet eller skapa ny
	// Save
	@PostMapping("/save")
	public String saveExperience(@Valid ProfessionalExperience experience, BindingResult result, Model model) {

		// om person är null så hämta person från inloggad user
		if (experience.getPerson()== null) {
			
			System.out.println("\n################ Save new Experience ################\n# ");
			
			// user
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			Users user = userService.findByUsername(authentication.getName());
			
			ProfessionalExperiencePK upk = experience.getId();
			upk.setIdPerson(user.person.getIdPerson());
			experience.setId(upk);
		}
		
		// Felhantering
		if (result.hasErrors()) {
			
			ProfessionalExperiencePK pk = experience.getId();
			
			// log
			System.out.println("\n################ Save Erorrs in Experience ################\n#" + experienceService.findOne(pk).get() + "\n# ");
			
			// log all errors
			for (ObjectError error : result.getAllErrors()) {
				System.out.println("# " + error.toString());
			} 
						
			// models for retry
			model.addAttribute("experience", experienceService.findOne(pk).get());
			model.addAttribute("companies", companyService.findAll());
			return "/experience/edit";
		}

		// spara om det om allt är ok
		experienceService.saveExperience(experience);

		return "redirect:/experience/";
	}

	// Ta bort en erfarenhet
	@Transactional // <- Viktig för att delete ska fungera
	@RequestMapping(value = "/remove/{id}")
	public String deleteExperience(@PathVariable int id) {
		
		// experience
		ProfessionalExperiencePK pk = new ProfessionalExperiencePK();
		pk.setIdProfExperience(id);
		
		// user, person id
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Users user = userService.findByUsername(authentication.getName());
		pk.setIdPerson(user.person.getIdPerson());
		
		// Delete
		experienceService.deleteExperience(pk);
		
		return "redirect:/experience/";
	}
}
