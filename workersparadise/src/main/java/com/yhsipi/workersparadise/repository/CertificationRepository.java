package com.yhsipi.workersparadise.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yhsipi.workersparadise.entities.Certification;
import com.yhsipi.workersparadise.entities.CertificationPK;

public interface CertificationRepository extends JpaRepository<Certification, Integer>{

public final static String FIND_BY_PERSON_QUERY = "SELECT c FROM Certification c LEFT JOIN c.person p WHERE p.id = :id";
	
	// Find certification by person
	 @Query(FIND_BY_PERSON_QUERY)
	 public List<Certification> findByPerson(@Param("id") int id);

	public Optional<Certification> findById(CertificationPK id);
	
}
