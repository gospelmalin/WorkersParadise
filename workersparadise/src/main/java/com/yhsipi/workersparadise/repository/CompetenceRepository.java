package com.yhsipi.workersparadise.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yhsipi.workersparadise.entities.Competence;

public interface CompetenceRepository extends JpaRepository<Competence, Integer> {
	
	public Optional<Competence> findById(int id);

	@Query(nativeQuery = true, value = "SELECT * FROM competence c WHERE c.competence_name LIKE %:searchStr%")
	public List<Competence> findByCompetenceName(String searchStr);

}
