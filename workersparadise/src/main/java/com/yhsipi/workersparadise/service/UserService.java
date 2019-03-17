package com.yhsipi.workersparadise.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhsipi.workersparadise.model.User;
import com.yhsipi.workersparadise.repository.UserRepository;

@Service
public class UserService{
	
	@Autowired
	UserRepository userRepository;
	
	public List<User> findAll(){
		return userRepository.findAllByOrderByIdAsc();
	}
	
	public Optional<User> findOne(Integer id) {
		return userRepository.findById(id);
	}
	
	public void saveUser(User user) {
		userRepository.save(user);
	}
	
	public void deleteUser(int id){
		userRepository.deleteById(id);
	}
}

