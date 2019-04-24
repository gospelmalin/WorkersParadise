package com.yhsipi.workersparadise.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.yhsipi.workersparadise.entities.ProfessionalExperience;
import com.yhsipi.workersparadise.entities.ProfessionalExperiencePK;

public interface ExperienceRepository extends JpaRepository<ProfessionalExperience, Integer>{

	 public final static String FIND_BY_PERSON_QUERY = "SELECT e " + 
             "FROM ProfessionalExperience e LEFT JOIN e.person p " +
             "WHERE p.id = :id";

    /**
     * Find experience by person.
     */
	 @Query(FIND_BY_PERSON_QUERY)
	 public List<ProfessionalExperience> findByPerson(@Param("id") int id);

	public Optional<ProfessionalExperience> findById(ProfessionalExperiencePK id);
}