package it.epicode.beservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
	
	
    public Page<User> findAllUserPageable(Pageable pageable){
    	return userRepository.findAll(pageable);
    }
    // Ordinamento
    public List<User> findAllUserSorted() {
        return userRepository.findByOrderByUsernameAsc();
    }

	// Paginazione e Ordinamento
    public List<User> findAllUserPageSizeSort(Integer page, Integer size, String sort) {
        Pageable paging = PageRequest.of(page, size, Sort.by(sort));
        Page<User> pagedResult = userRepository.findAll(paging);
        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<User>();
        }}
}
