package local.project.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user")

public class UserEntity extends DefaultEntity {	
	@Column(unique=true, nullable=false)
	private String loginid;

	@Column(nullable=false)
	private String username;

	@Column(nullable=false)
	@JsonIgnore
	private String password;

	@Column
	private char sex;

	@Column
	private String birth;

	@Column
	private String phone;



	public String getLoginid() {
		return loginid;
	}

	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}


	public String getUsername() {
		return username;
	}	

	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public char getSex() {
		return sex;

	}

	public void setSex(char sex){
		this.sex = sex;
	}


	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth){
		this.birth = birth;
	}

	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}