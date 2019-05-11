package com.yhsipi.workersparadise.service;

import com.yhsipi.workersparadise.entities.Users;
import org.springframework.stereotype.Service;

@Service
public interface UserService{


    public Users findByUsername(String username);

    public void saveUser(Users user);
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


