package local.project.api.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "User")
public class UserEntity extends DefaultEntity {

	@Column
	private String User_id;
	@Column
	private String User_pw;
	@Column
	private String User_name;
	@Column
	private String User_birth;
	@Column
	private char User_sex;
	@Column
	private String User_ph;



	@ManyToOne(cascade = CascadeType.PERSIST)
	private UserEntity user;

	/**
	 * @return the title
	 */
	public String getid() { 
		return User_id;
	}
	/**
	 * @param title the title to set
	 */
	public void setid(String User_id) {
		this.User_id = User_id;
	}

	/**
	 * @return the content
	 */
	public String getpw() {
		return User_pw;
	}

	/**
	 * @param content the content to set
	 */
	public void setpw(String User_pw) {
		this.User_pw = User_pw;
	}

	/**
	 * @return the name
	 */
	public String getname() {
		return User_name;
	}

	public void setname(String User_name) {
		this.User_name = User_name;
	}


	public String getbirth() {
		return User_birth;
		/*사용자 생일 입력하면 나이도 추가해주는 기능 필요*/
	}

	public void setbirth(String User_birth) {
		this.User_birth = User_birth;
	}

	public char getsex() {
		return User_sex;
	}

	public void setsex(char User_sex) {
		this.User_sex = User_sex;
	}

	public String getph() {
		return User_ph;
	}

	public void setph(String User_ph) {
		this.User_ph = User_ph;
	}
	

	/**
	 * @return the user
	 */
	public UserEntity getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(UserEntity user) {
		this.user = user;
	}
	
}