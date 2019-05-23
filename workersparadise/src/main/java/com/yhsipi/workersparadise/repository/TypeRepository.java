package com.yhsipi.workersparadise.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yhsipi.workersparadise.entities.Type;

public interface TypeRepository extends JpaRepository<Type, Integer> {
	
	public Optional<Type> findById(int id);

}
