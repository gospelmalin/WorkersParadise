package com.yhsipi.workersparadise.service;

import java.io.IOException;
import java.util.List;

import java.util.Optional;

import com.yhsipi.workersparadise.entities.Person;
import org.codehaus.groovy.tools.shell.IO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yhsipi.workersparadise.repository.PersonRepository;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


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
	public Person storeFile(MultipartFile file) {
		// Normalize file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			// Check if the file's name contains invalid characters
			if(fileName.contains("..")) {
				throw new IOException("Sorry! Filename contains invalid path sequence " + fileName);
			}
			return personRepository.save(file);

		} catch (IOException ex) {
			System.out.println(ex);
		}
	}

	public Person getFile(String fileId) {
		return PersonRepository.findById(fileId)
				.orElseThrow(() -> new IOException("File not found with id " + fileId));
	}
	*/
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

