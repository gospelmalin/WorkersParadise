package com.yhsipi.workersparadise.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhsipi.workersparadise.entities.Phone;
import com.yhsipi.workersparadise.repository.PhoneRepository;

@Service
public class PhoneService {

	@Autowired
	PhoneRepository phoneRepository;
	
	public List<Phone> findAll(){
		return phoneRepository.findAll();
	}
	
	public Optional<Phone> findOne(Integer id){
		return phoneRepository.findById(id);
	}
	
	public void savePhone(Phone phone) {
		phoneRepository.save(phone);
	}
	
	public void deletePhone(int id) {
		phoneRepository.deleteById(id);
	}
}
