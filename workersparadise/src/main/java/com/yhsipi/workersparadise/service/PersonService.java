package com.yhsipi.workersparadise.service;

import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yhsipi.workersparadise.repository.PersonRepository;
import entities.Person;

@Service
public class PersonService {
	
	@Autowired
	PersonRepository personRepository;
	
	public List<Person> findAll() {
		return personRepository.findAll();
	}
	
	public Optional<Person> findOne(Integer id) {
		return personRepository.findById(id);
	}

	public void savePerson(Person person) {
		personRepository.save(person);
	}

	public void deletePerson(int id) {
		personRepository.deleteById(id);
	}
	
	/*
	public void saveOrUpdate(Person person) {

		if (personRepository.findById(person.getIdPerson())==null) {
			personRepository.save(person);
		} 

		else {
			personRepository.update(person);
		}
		 
	}*/

}
