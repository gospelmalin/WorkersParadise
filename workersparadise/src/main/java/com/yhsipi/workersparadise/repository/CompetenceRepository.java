package com.yhsipi.workersparadise.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yhsipi.workersparadise.entities.Competence;
//import com.yhsipi.workersparadise.entities.CompetencePK;

public interface CompetenceRepository extends JpaRepository<Competence, Integer> {
	
	public Optional<Competence> findById(int id);
	//public Optional<Competence> findById(CompetencePK id);
	
	public List<Competence> findAllByOrderByCompetenceNameAsc();

}
