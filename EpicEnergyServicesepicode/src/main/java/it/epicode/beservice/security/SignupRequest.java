package it.epicode.beservice.security;

import java.util.Set;

import javax.validation.constraints.Email;

public class SignupRequest {
	private String username;
	@Email
	private String email;
	private Set<String> role;
	private String password;
	private String nome;
	private String cognome;

	public SignupRequest() {
	}

	public SignupRequest(String username, @Email String email, Set<String> role, String password, String nome,
			String cognome) {
		this.username = username;
		this.email = email;
		this.role = role;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public Set<String> getRole() {
		return role;
	}

	public String getPassword() {
		return password;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setRole(Set<String> role) {
		this.role = role;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

}
