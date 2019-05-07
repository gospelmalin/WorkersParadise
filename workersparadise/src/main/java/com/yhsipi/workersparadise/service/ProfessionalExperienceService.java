package com.yhsipi.workersparadise.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhsipi.workersparadise.entities.ProfessionalExperience;
import com.yhsipi.workersparadise.repository.ProfessionalExperienceRepository;

@Service
public class ProfessionalExperienceService {

	@Autowired
	ProfessionalExperienceRepository professionalExperienceRepository;
	
	public List<ProfessionalExperience> findAll(){
		return professionalExperienceRepository.findAll();
	}
	
	public Optional<ProfessionalExperience> findOne(Integer id){
		return professionalExperienceRepository.findById(id);
	}
	
	public void saveProfessionalExperience(ProfessionalExperience professionalExperience) {
		professionalExperienceRepository.save(professionalExperience);
	}
	
	public void deleteProfessionalExperience(int id) {
		professionalExperienceRepository.deleteById(id);
	}
}
