package com.yhsipi.workersparadise.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yhsipi.workersparadise.entities.Certification;
import com.yhsipi.workersparadise.entities.CertificationPK;
import com.yhsipi.workersparadise.entities.EducationPK;

public interface CertificationRepository extends JpaRepository<Certification, Integer>{

public final static String FIND_BY_PERSON_QUERY = "SELECT c FROM Certification c LEFT JOIN c.person p WHERE p.id = :id";
	
	// Find certification by person
	 @Query(FIND_BY_PERSON_QUERY)
	 public List<Certification> findByPerson(@Param("id") int id);

	public Optional<Certification> findById(CertificationPK id);

	public void deleteById(CertificationPK id);
	
	@Query(nativeQuery = true, value ="SELECT * FROM certification c WHERE c.certification_name LIKE %:searchStr%")
	public List<Certification> findSearchResult(@Param("searchStr") String searchStr);
	
}
