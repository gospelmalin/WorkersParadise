package com.yhsipi.workersparadise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import entities.Person;

public interface PersonRepository extends JpaRepository<Person, Integer>{

}
