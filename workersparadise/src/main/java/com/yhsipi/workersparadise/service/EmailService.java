package com.yhsipi.workersparadise.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhsipi.workersparadise.entities.Email;
import com.yhsipi.workersparadise.entities.EmailPK;
import com.yhsipi.workersparadise.repository.EmailRepository;

@Service
public class EmailService {
	
	@Autowired
	EmailRepository emailRepository;
	
	public List<Email> findAll() {
		return emailRepository.findAll();
	}
	
	public List<Email> findByPerson(int personId) {
		return emailRepository.findByPerson(personId);
	}
	
	public Optional<Email> findOne(EmailPK id) {
		return emailRepository.findById(id);
	}

	public void saveEmail(Email email) {
		emailRepository.save(email);
	}

	public void deleteEmail(Email email) {
		emailRepository.delete(email);
	}
	
	public void deleteEmail(EmailPK id) {
		emailRepository.deleteById(id);
	}

}
