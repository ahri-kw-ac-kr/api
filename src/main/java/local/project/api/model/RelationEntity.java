package local.project.api.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "relation")
public class RelationEntity extends DefaultEntity {

	@ManyToOne(cascade = CascadeType.PERSIST)
	private UserEntity userA;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private UserEntity userB;

	/**
	 * @return the userA
	 */
	public UserEntity getUserA() { 
		return userA;
	}
	/**
	 * @param userA the userA to set
	 */
	public void setUserA(UserEntity userA) {
		this.userA = userA;
	}

	/**
	 * @return the userB
	 */
	public UserEntity getUserB() {
		return userB;
	}

	/**
	 * @param user_B the user_B to set
	 */
	public void setUserB(UserEntity userB) {
		this.userB = userB;
	}

	
}