package it.epicode.beservice.controller;

 import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import io.swagger.v3.oas.annotations.Operation;
import it.epicode.beservice.model.Role;
import it.epicode.beservice.model.RoleType;
import it.epicode.beservice.model.User;
import it.epicode.beservice.model.UserDetailsImpl;
import it.epicode.beservice.repositories.RoleRepository;
import it.epicode.beservice.repositories.UserRepository;
import it.epicode.beservice.security.JwtUtils;
import it.epicode.beservice.security.LoginRequest;
import it.epicode.beservice.security.LoginResponse;
import it.epicode.beservice.security.SignupRequest;
import it.epicode.beservice.security.SignupResponse;

@RestController
@RequestMapping("/api")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	UserRepository userRepository;
	@Autowired
	JwtUtils jwtUtils;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	RoleRepository roleRepository;

	@Operation(summary = "LOGIN")
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		authentication.getAuthorities();

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new LoginResponse(jwt, userDetails.getId(), userDetails.getUsername(),
				userDetails.getEmail(), roles, userDetails.getExpirationTime()));
	}
	@PostMapping("/loginform")
	public ModelAndView authenticateUserForm(LoginRequest loginRequest) {
		ModelAndView loginView = new ModelAndView();	
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		authentication.getAuthorities();

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());
		if(roles.contains("ROLE_ADMIN")) {
			loginView.setViewName("elencoadmin");
			return loginView; 
		}
		loginView.setViewName("elenco");
		return loginView;
	}

	@Operation(summary = "SIGN UP")
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new SignupResponse("Errore: Username già in uso!"));
		}
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new SignupResponse("Errore: Email già in uso!"));
		}
		User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()), signUpRequest.getNome(), signUpRequest.getCognome());
		
		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();
		if (strRoles == null) {
			Role userRole = roleRepository.findByRoleType(RoleType.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Errore: Role non trovato!"));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "ROLE_ADMIN":
					Role adminRole = roleRepository.findByRoleType(RoleType.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Errore: Role non trovato!"));
					roles.add(adminRole);
					break;
				default:
					Role userRole = roleRepository.findByRoleType(RoleType.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Errore: Role non trovato!"));
					roles.add(userRole);
				}
			});
		}
		user.setRoles(roles);
		userRepository.save(user);
		return ResponseEntity.ok(new SignupResponse("User registrato con successo!"));
	}
	
	@PostMapping("/signupform")
	public ModelAndView registerUserForm(SignupRequest signUpRequest) {
		ModelAndView signupView = new ModelAndView();		
		
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			signupView.setViewName("errorusername");
			return signupView;
		}
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			signupView.setViewName("erroremail");
			return signupView;
		}
		User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()), signUpRequest.getNome(), signUpRequest.getCognome());
		
		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();
		if (strRoles == null) {
			Role userRole = roleRepository.findByRoleType(RoleType.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Errore: Role non trovato!"));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByRoleType(RoleType.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Errore: Role non trovato!"));
					roles.add(adminRole);
					break;
				default:
					Role userRole = roleRepository.findByRoleType(RoleType.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Errore: Role non trovato!"));
					roles.add(userRole);
				}
			});
		}
		user.setRoles(roles);
		userRepository.save(user);
		signupView.setViewName("elenco");
		return signupView;
	}
	
}
