package com.yhsipi.workersparadise.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhsipi.workersparadise.entities.Type;
import com.yhsipi.workersparadise.repository.TypeRepository;


@Service
public class TypeService {
	
	@Autowired
	TypeRepository typeRepository;
	
	public List<Type> findAll(){
		return typeRepository.findAll();
	}
	
	public Optional<Type> findOne(Integer id){
		return typeRepository.findById(id);
	}
	
	public void saveType(Type type) {
		typeRepository.save(type);
	}
	
	public void deleteType(int id) {
		typeRepository.deleteById(id);
	}

}
