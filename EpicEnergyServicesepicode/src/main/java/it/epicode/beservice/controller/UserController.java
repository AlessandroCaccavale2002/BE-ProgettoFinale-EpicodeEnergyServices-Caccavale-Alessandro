package it.epicode.beservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import it.epicode.beservice.model.User;
import it.epicode.beservice.service.UserService;


	@RestController
	@RequestMapping("/usercontroller")
	public class UserController {
		@Autowired
		private UserService userService;
		
	
		@Operation(summary = "MOSTRA TUTTI GLI UTENTI")
		@GetMapping("/userall")
		public List<User> fingUserAll(){
			List<User> listUser = userService.myFindAllUsers();
			return listUser;
		}
		
	
		@Operation(summary = "SALVA GLI UTENTI")
		@PostMapping("/usersave")
		public void salvaUser(@RequestBody User user) {
			userService.saveUser(user);
		}
		
	
		@Operation(summary = "TROVA TUTTI GLI UTENTI TRAMITE DATI")	
		@GetMapping(value = "/getallusersortbyname", produces = MediaType.APPLICATION_JSON_VALUE)
		public List<User> getAllCittaSortByName() {
		     return userService.findAllUserSorted();
		    } 
}
