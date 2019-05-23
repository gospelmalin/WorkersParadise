package com.yhsipi.workersparadise.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yhsipi.workersparadise.entities.Webpage;
import com.yhsipi.workersparadise.entities.WebpagePK;

public interface WebpageRepository extends JpaRepository<Webpage, Integer>{
	
public final static String FIND_BY_PERSON_QUERY = "SELECT w FROM Webpage w LEFT JOIN w.person p WHERE p.id = :id";
	
	// Find webpages by person
	 @Query(FIND_BY_PERSON_QUERY)
	 public List<Webpage> findByPerson(@Param("id") int id);

	public Optional<Webpage> findById(WebpagePK id);

}
