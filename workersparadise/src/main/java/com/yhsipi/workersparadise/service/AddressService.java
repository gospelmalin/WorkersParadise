package com.yhsipi.workersparadise.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhsipi.workersparadise.entities.Address;
import com.yhsipi.workersparadise.entities.AddressPK;
import com.yhsipi.workersparadise.entities.Education;
import com.yhsipi.workersparadise.entities.EducationPK;
import com.yhsipi.workersparadise.repository.AddressRepository;

@Service
public class AddressService {
	
	@Autowired
	AddressRepository addressRepository;
	
	public List<Address> findAll() {
		return addressRepository.findAll();
	}
	
	public List<Address> findByPerson(int personId) {
		return addressRepository.findByPerson(personId);
	}
	
	public Optional<Address> findOne(AddressPK id) {
		return addressRepository.findById(id);
	}
	

	public void saveAddress(Address address) {
		addressRepository.save(address);
	}

	public void deleteAddress(Address address) {
		addressRepository.delete(address);
	}

}
