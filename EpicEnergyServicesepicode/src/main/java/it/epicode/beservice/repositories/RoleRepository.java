package it.epicode.beservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.epicode.beservice.model.Role;
import it.epicode.beservice.model.RoleType;


public interface RoleRepository extends JpaRepository<Role, Long>{

	Optional<Role> findByRoleType(RoleType roletype);
	
}
