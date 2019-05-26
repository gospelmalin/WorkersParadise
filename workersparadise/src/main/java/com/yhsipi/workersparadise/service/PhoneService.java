package com.yhsipi.workersparadise.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhsipi.workersparadise.entities.Phone;
import com.yhsipi.workersparadise.entities.PhonePK;
import com.yhsipi.workersparadise.repository.PhoneRepository;

@Service
public class PhoneService {

	@Autowired
	PhoneRepository phoneRepository;
	
	public List<Phone> findAll(){
		return phoneRepository.findAll();
	}
	
	public Optional<Phone> findOne(PhonePK pPK){
		return phoneRepository.findById(pPK);
	}
	
	public void savePhone(Phone phone) {
		phoneRepository.save(phone);
	}
	
	public void deletePhone(Phone phone) {
		phoneRepository.delete(phone);
	}
	
	public void deletePhone(PhonePK id) {
		phoneRepository.deleteById(id);
	}

	public List<Phone> findByPerson(int personId) {
		return phoneRepository.findByPerson(personId);
	}
	
}