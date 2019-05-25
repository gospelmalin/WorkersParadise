package com.yhsipi.workersparadise.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhsipi.workersparadise.entities.Education;
import com.yhsipi.workersparadise.entities.EducationPK;
import com.yhsipi.workersparadise.repository.EducationRepository;

@Service
public class EducationService {
	
	@Autowired
	EducationRepository educationRepository;
	
	public List<Education> findAll() {
		return educationRepository.findAll();
	}
	
	public List<Education> findByPerson(int personId) {
		return educationRepository.findByPerson(personId);
	}
	
	public Optional<Education> findOne(EducationPK id) {
		return educationRepository.findById(id);
	}
	

	public void saveEducation(Education education) {
		educationRepository.save(education);
	}

	public void deleteEducation(Education education) {
		educationRepository.delete(education);
	}
	
	public void deleteEducation(EducationPK id) {
		educationRepository.deleteById(id);
	}

}
