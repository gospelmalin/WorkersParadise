package com.yhsipi.workersparadise.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import entities.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

	public List<Address> findAllByOrderByIdAsc();
}
