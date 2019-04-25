package com.yhsipi.workersparadise.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.yhsipi.workersparadise.entities.Education;
import com.yhsipi.workersparadise.entities.EducationPK;


public interface EducationRepository extends JpaRepository<Education, Integer>{

	public final static String FIND_BY_PERSON_QUERY = "SELECT ed FROM Education ed LEFT JOIN ed.person p WHERE p.id = :id";
	
	// Find education by person
	 @Query(FIND_BY_PERSON_QUERY)
	 public List<Education> findByPerson(@Param("id") int id);

	public Optional<Education> findById(EducationPK id);
}
