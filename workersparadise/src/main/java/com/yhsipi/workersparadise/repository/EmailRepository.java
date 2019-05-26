package com.yhsipi.workersparadise.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yhsipi.workersparadise.entities.Email;
import com.yhsipi.workersparadise.entities.EmailPK;

public interface EmailRepository extends JpaRepository<Email, Integer>{
	
public final static String FIND_BY_PERSON_QUERY = "SELECT e FROM Email e LEFT JOIN e.person p WHERE p.id = :id";
	
	// Find emails by person
	 @Query(FIND_BY_PERSON_QUERY)
	 public List<Email> findByPerson(@Param("id") int id);

	public Optional<Email> findById(EmailPK id);
	
	public void deleteById(EmailPK id);

}
