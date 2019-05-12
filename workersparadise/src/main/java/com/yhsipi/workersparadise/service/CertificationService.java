package com.yhsipi.workersparadise.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhsipi.workersparadise.entities.Certification;
import com.yhsipi.workersparadise.entities.CertificationPK;
import com.yhsipi.workersparadise.repository.CertificationRepository;


@Service
public class CertificationService {

	@Autowired
	CertificationRepository certificationRepository;
	
	public List<Certification> findAll() {
		return certificationRepository.findAll();
	}
	
	public List<Certification> findByPerson(int personId) {
		return certificationRepository.findByPerson(personId);
	}
	
	public Optional<Certification> findOne(CertificationPK id) {
		return certificationRepository.findById(id);
	}
	

	public void saveCertification(Certification certification) {
		certificationRepository.save(certification);
	}

	public void deleteCertification(Certification certification) {
		certificationRepository.delete(certification);
	}
	
}
