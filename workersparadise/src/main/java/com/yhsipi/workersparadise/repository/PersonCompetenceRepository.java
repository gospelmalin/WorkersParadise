package com.yhsipi.workersparadise.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yhsipi.workersparadise.entities.PersonCompetence;
import com.yhsipi.workersparadise.entities.ProfessionalExperience;

public interface PersonCompetenceRepository extends JpaRepository<PersonCompetence, Integer>{

public final static String FIND_BY_PERSON_QUERY = "SELECT pc FROM PersonCompetence pc LEFT JOIN pc.person p WHERE p.id = :id";
	
	// Find personal competence by person
	 @Query(FIND_BY_PERSON_QUERY)
	 public List<PersonCompetence> findByPerson(@Param("id") int id);

	public Optional<PersonCompetence> findById(int id);

	@Query(nativeQuery = true, value = "SELECT * FROM person_competence pc where pc.id_competence = :competenceId")
	public List<PersonCompetence> findByCompetence(@Param("competenceId")int competenceId);

}
