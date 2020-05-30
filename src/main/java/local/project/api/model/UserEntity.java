package local.project.api.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "user")
@JsonIgnoreProperties({"updatedAt", "createdAt", "number"})
public class UserEntity extends DefaultEntity {
	
	@Column(unique = true, nullable = false)
	private String username;

	@Column(nullable = false)
	private String fullname;

	@Column(nullable = false)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;

	@Transient
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private transient String newPassword;

	@Column(nullable = true)
	private String sex;

	@Column
	private String birth;

	@Column
	private String phone;
	
	@Column
	private String number;
	
	@Column
	private String sleep;
	
	@Column
	private String shareGPS;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(uniqueConstraints={@UniqueConstraint(columnNames={"user_entity_id","friend_id"})})
	private List<UserEntity> friend;

	/**
	 * @return the sleep
	 */
	public String getShareGPS() {
		return shareGPS;
	}

	/**
	 * @param sleep the sleep to set
	 */
	public void setShareGPS(String shareGPS) {
		this.shareGPS = shareGPS;
	}
	
	/**
	 * @return the sleep
	 */
	public String getSleep() {
		return sleep;
	}

	/**
	 * @param sleep the sleep to set
	 */
	public void setSleep(String sleep) {
		this.sleep = sleep;
	}
	
	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}
	
	/**
	 * @return the newPassword
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * @param newPassword the newPassword to set
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	/**
	 * @return the fullname
	 */
	public String getFullname() {
		return fullname;
	}

	/**
	 * @param fullname the fullname to set
	 */
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	@JsonIgnore
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return the birth
	 */
	public String getBirth() {
		return birth;
	}

	/**
	 * @param birth the birth to set
	 */
	public void setBirth(String birth) {
		this.birth = birth;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the friend
	 */
	@JsonIgnoreProperties({"updatedAt", "createdAt", "friend"})
	public List<UserEntity> getFriend() {
		List<UserEntity> myfriend=friend;
		if(friend!=null) {
			for(int i=0; i<friend.size(); i++) {
				if(friend.get(i).isDel()== true) {
					myfriend.remove((friend.get(i)));
				} 
			}
		}
		return myfriend;
	}

	/**
	 * @param friend the friend to set
	 */
	public void setFriend(List<UserEntity> friend) {
		this.friend = friend;
	}
}