package com.yhsipi.workersparadise.repository;

import java.util.List;
import java.util.Optional;

import com.yhsipi.workersparadise.entities.Address;
import com.yhsipi.workersparadise.entities.AddressPK;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface AddressRepository extends JpaRepository<Address, Integer> {

	//public List<Address> findAllByOrderByIdAsc(); //TODO till vad?
	
	public final static String FIND_BY_PERSON_QUERY = "SELECT a FROM Address a LEFT JOIN a.person p WHERE p.id = :id";
	
	// Find address by person
	 @Query(FIND_BY_PERSON_QUERY)
	 public List<Address> findByPerson(@Param("id") int id);

	public Optional<Address> findById(AddressPK id);
}
