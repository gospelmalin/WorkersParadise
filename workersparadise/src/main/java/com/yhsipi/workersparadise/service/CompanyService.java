package com.yhsipi.workersparadise.service;

import java.util.List;

import java.util.Optional;

import com.yhsipi.workersparadise.entities.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yhsipi.workersparadise.repository.CompanyRepository;


@Service
public class CompanyService {

	@Autowired
	CompanyRepository companyRepository;

	public List<Company> findAll() {
		return companyRepository.findAllByOrderByCompanyNameAsc();
	}

	public Optional<Company> findOne(Integer id) {
		return companyRepository.findById(id);
	}

	public void saveCompany(Company company) {
		companyRepository.save(company);
	}

	public void deleteCompany(int id) {
		companyRepository.deleteById(id);
	}
}
