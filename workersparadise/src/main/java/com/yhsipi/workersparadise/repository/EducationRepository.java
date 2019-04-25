package com.yhsipi.workersparadise.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.yhsipi.workersparadise.entities.Education;


public interface EducationRepository extends JpaRepository<Education, Integer>{

	
	// TODO Find education by person
}
