package com.yhsipi.workersparadise.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhsipi.workersparadise.entities.Competence;
import com.yhsipi.workersparadise.entities.ProfessionalExperience;
import com.yhsipi.workersparadise.repository.CompetenceRepository;

@Service
public class CompetenceService {

	@Autowired
	CompetenceRepository competenceRepository;
	/*
	public List<Competence> findAll(){
		return competenceRepository.findAll();
	}
	*/
	
	public List<Competence> findAll(){
		return competenceRepository.findAllByOrderByCompetenceNameAsc();
	}
	
	public Optional<Competence> findOne(Integer id){
		return competenceRepository.findById(id);
	}
	
	public void saveCompetence(Competence competence) {
		competenceRepository.save(competence);
	}
	
	public void deleteCompetence(int id) {
		competenceRepository.deleteById(id);
	}

	public List<Competence> findByCompetenceName(String searchStr) {
		return competenceRepository.findByCompetenceName(searchStr);
	}
}
