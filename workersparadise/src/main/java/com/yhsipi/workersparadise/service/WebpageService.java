package com.yhsipi.workersparadise.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhsipi.workersparadise.entities.Webpage;
import com.yhsipi.workersparadise.entities.WebpagePK;
import com.yhsipi.workersparadise.repository.WebpageRepository;

@Service
public class WebpageService {
	
	@Autowired
	WebpageRepository webpageRepository;
	
	public List<Webpage> findAll() {
		return webpageRepository.findAll();
	}
	
	public List<Webpage> findByPerson(int personId) {
		return webpageRepository.findByPerson(personId);
	}
	
	public Optional<Webpage> findOne(WebpagePK id) {
		return webpageRepository.findById(id);
	}

	public void saveWebpage(Webpage webpage) {
		webpageRepository.save(webpage);
	}

	public void deleteWebpage(Webpage webpage) {
		webpageRepository.delete(webpage);
	}
	
	public void deleteWebpage(WebpagePK id) {
		webpageRepository.deleteById(id);
	}

}
