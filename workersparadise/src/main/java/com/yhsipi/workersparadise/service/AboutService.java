package com.yhsipi.workersparadise.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhsipi.workersparadise.entities.About;
import com.yhsipi.workersparadise.repository.AboutRepository;

@Service
public class AboutService {

	@Autowired
	AboutRepository aboutRepository;
	
	public List<About> findAll(){
		return aboutRepository.findAll();
	}
	
	public Optional<About> findOne(Integer id) {
		return aboutRepository.findById(id);
	}
	
	public void saveAbout(About about) {
		aboutRepository.save(about);
	}
	
	public void deleteAbout(int aboutId) {
		aboutRepository.deleteById(aboutId);
	}
}
