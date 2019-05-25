package com.yhsipi.workersparadise.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhsipi.workersparadise.entities.Address;
import com.yhsipi.workersparadise.entities.PersonCompetence;
import com.yhsipi.workersparadise.entities.ProfessionalExperience;
import com.yhsipi.workersparadise.repository.PersonCompetenceRepository;

@Service
public class PersonCompetenceService {
	
	@Autowired
	PersonCompetenceRepository personCompetenceRepository;
	
	public List<PersonCompetence> findAll(){
		return personCompetenceRepository.findAll();
	}
	
	public List<PersonCompetence> findByPerson(int personId) {
		return personCompetenceRepository.findByPerson(personId);
	}
	
	
	public Optional<PersonCompetence> findOne(Integer id){
		return personCompetenceRepository.findById(id);
	}
	
	public void savePersonCompetence(PersonCompetence personCompetence) {
		personCompetenceRepository.save(personCompetence);
	}
	
	public void deletePersonCompetence(int id) {
		personCompetenceRepository.deleteById(id);
	}

	public List<PersonCompetence> findByCompetence(int competenceId) {
		return personCompetenceRepository.findByCompetence(competenceId);
	}

	

}
