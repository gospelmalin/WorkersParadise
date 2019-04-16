package com.yhsipi.workersparadise.repository;

import java.util.List;

import com.yhsipi.workersparadise.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AddressRepository extends JpaRepository<Address, Integer> {

	public List<Address> findAllByOrderByIdAsc();
}
