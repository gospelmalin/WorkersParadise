package com.yhsipi.workersparadise.repository;

import com.yhsipi.workersparadise.entities.About;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AboutRepository extends JpaRepository<About, Integer> {

	public final static String FIND_BY_PERSON_QUERY = "SELECT DISTINCT a FROM About a WHERE a.idPerson = :id";

	@Query(FIND_BY_PERSON_QUERY)
	public About findByPerson(@Param("id") int id);
}
