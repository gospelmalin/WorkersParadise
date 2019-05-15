package com.yhsipi.workersparadise.service;

import com.yhsipi.workersparadise.entities.Users;
import com.yhsipi.workersparadise.repository.PhoneRepository;
import com.yhsipi.workersparadise.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService{

    @Autowired
    UsersRepository userrepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public Users findByUsername(String username) {
        return userrepository.findByUsername(username);
    };

    public void saveUser(Users user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userrepository.save(user);
    }

    public List<Users> findAll() {
        return userrepository.findAll();
    }

/*
	@Autowired
	UsersRepository userRepository;

	public List<Users> findAll(){
		return userRepository.findAllByOrderByIdAsc();
	}

	public Optional<Users> findOne(Integer id) {
		return userRepository.findById(id);
	}

	public void saveUser(Users user) {
		userRepository.save(user);
	}

	public void deleteUser(int id){
		userRepository.deleteById(id);
	}
*/
}


