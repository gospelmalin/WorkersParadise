package com.yhsipi.workersparadise.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhsipi.workersparadise.entities.Education;
import com.yhsipi.workersparadise.repository.EducationRepository;

@Service
public class EducationService {
	
	@Autowired
	EducationRepository educationRepository;
	
	public List<Education> findAll() {
		return educationRepository.findAll();
	}
	
	public Optional<Education> findOne(Integer id) {
		return educationRepository.findById(id);
	}
	
	// TODO findByPerson(int personId)

	public void saveEducation(Education education) {
		educationRepository.save(education);
	}

	public void deleteEducation(int id) {
		educationRepository.deleteById(id);
	}

}
