package it.epicode.beservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.beservice.model.User;
import it.epicode.beservice.service.UserService;


	@RestController
	@RequestMapping("/usercontroller")
	public class UserController {
		@Autowired
		private UserService userService;
		
		@GetMapping("/userall")
		public List<User> fingUserAll(){
			List<User> listUser = userService.myFindAllUsers();
			return listUser;
		}
		
		
		@PostMapping("/usersave")
		public void salvaUser(@RequestBody User user) {
			userService.saveUser(user);
		}
		
			
		@GetMapping(value = "/getallusersortbyname", produces = MediaType.APPLICATION_JSON_VALUE)
		public List<User> getAllCittaSortByName() {
		     return userService.findAllUserSorted();
		    } 
}
