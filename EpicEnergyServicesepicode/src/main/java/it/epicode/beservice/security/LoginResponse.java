package it.epicode.beservice.security;

import java.util.Date;
import java.util.List;

public class LoginResponse {

	private String token;
	private final String type = "Bearer";
	private Long id;
	private String username;
	private String email;
	private List<String> roles;
	private Date expirationTime;

	public String getToken() {
		return token;
	}

	public String getType() {
		return type;
	}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public List<String> getRoles() {
		return roles;
	}

	public Date getExpirationTime() {
		return expirationTime;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public void setExpirationTime(Date expirationTime) {
		this.expirationTime = expirationTime;
	}

	public LoginResponse() {
		super();
	}

	public LoginResponse(String token, Long id, String username, String email, List<String> roles,
			Date expirationTime) {
		this.token = token;
		this.id = id;
		this.username = username;
		this.email = email;
		this.roles = roles;
		this.expirationTime = expirationTime;
	}

}
