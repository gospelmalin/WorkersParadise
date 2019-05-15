package com.yhsipi.workersparadise.repository;

import com.yhsipi.workersparadise.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsersRepository extends JpaRepository<Users,Integer> {

	//public List<Users> findAllByOrderByIdAsc();

	Users findByUsername(String username);


}