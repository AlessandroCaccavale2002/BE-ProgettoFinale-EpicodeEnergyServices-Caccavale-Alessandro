package it.epicode.beservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.beservice.model.Role;
import it.epicode.beservice.repositories.RoleRepository;

@Service
public class RoleService {

	@Autowired
	RoleRepository roleRepository;
	
	public void salvaRuolo(Role role) {
		roleRepository.save(role);
	}
	
}
