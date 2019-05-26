package com.yhsipi.workersparadise.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yhsipi.workersparadise.entities.Phone;
import com.yhsipi.workersparadise.entities.PhonePK;

public interface PhoneRepository extends JpaRepository<Phone, Integer> {

public final static String FIND_BY_PERSON_QUERY = "SELECT ph FROM Phone ph LEFT JOIN ph.person p WHERE p.id = :id";
	
	@Query(FIND_BY_PERSON_QUERY)
	public List<Phone> findByPerson(@Param("id") int id);
	
	public Optional<Phone> findById(PhonePK id);

	public void deleteById(PhonePK id);
}
