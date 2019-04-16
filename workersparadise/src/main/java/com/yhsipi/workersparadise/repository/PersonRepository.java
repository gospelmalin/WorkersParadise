package com.yhsipi.workersparadise.repository;

import com.yhsipi.workersparadise.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer>{

}
