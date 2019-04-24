package com.yhsipi.workersparadise.service;

import java.util.List;
import java.util.Optional;
import com.yhsipi.workersparadise.entities.ProfessionalExperience;
import com.yhsipi.workersparadise.entities.ProfessionalExperiencePK;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yhsipi.workersparadise.repository.ExperienceRepository;


@Service
public class ExperienceService {

	@Autowired
	ExperienceRepository experienceRepository;

	public List<ProfessionalExperience> findAll() {
		return experienceRepository.findAll();
	}
	public List<ProfessionalExperience> findByPerson(int personId) {
		return experienceRepository.findByPerson(personId);
	}

	public Optional<ProfessionalExperience> findOne(ProfessionalExperiencePK id) {
		return experienceRepository.findById(id);
	}

	public void saveExperience(ProfessionalExperience experience) {
		experienceRepository.save(experience);
	}

	public void deleteExperience(int id) {
		experienceRepository.deleteById(id);
	}
}
