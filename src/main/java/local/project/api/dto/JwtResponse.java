package local.project.api.dto;

import java.io.Serializable;

import local.project.api.model.UserEntity;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;
	private final UserEntity user;

	public JwtResponse(String jwttoken, UserEntity user) {
		this.jwttoken = jwttoken;
		this.user = user;
	}
	

	public String getToken() {
		return this.jwttoken;
	}
	
	public UserEntity getUser() {
		return this.user;
	}
}