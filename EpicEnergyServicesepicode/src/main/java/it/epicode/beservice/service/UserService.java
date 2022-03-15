package it.epicode.beservice.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.epicode.beservice.model.User;
import it.epicode.beservice.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public List<User> myFindAllUsers() {
		return userRepository.findAll();
	}

	public User findUserByUsername(String username) {
		return userRepository.findUserUsername(username);
	}
	
	public User myFindUserById(Long id) {
		return userRepository.getById(id);
	}

	public void saveUser(User user) {
		userRepository.save(user);
	}

	public List<User> findAllUserSorted() {
		return userRepository.findAll();
	}
	
}
