package com.yhsipi.workersparadise.repository;

import com.yhsipi.workersparadise.entities.Company;
import com.yhsipi.workersparadise.entities.Competence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer>{

	public List<Company> findAllByOrderByCompanyNameAsc(); 
}
