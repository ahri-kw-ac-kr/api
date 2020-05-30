package local.project.api.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "sleep")
@JsonIgnoreProperties({"updatedAt", "del"})
public class SleepEntity extends DefaultEntity {

	@Column
	private String sleepTime;

	@Column
	private String wakeTime;
	
	@Column
	private String deep;
	
	@Column
	private String light;
	
	@Column
	private String turn;
	
	@Column
	private String wake;

	@ManyToOne(cascade = CascadeType.MERGE)
	private UserEntity user;

	/**
	 * @return the sleepTime
	 */
	public String getSleepTime() {
		return sleepTime;
	}

	/**
	 * @param sleepTime the sleepTime to set
	 */
	public void setSleepTime(String sleepTime) {
		this.sleepTime = sleepTime;
	}

	/**
	 * @return the wakeTime
	 */
	public String getWakeTime() {
		return wakeTime;
	}

	/**
	 * @param wakeTime the wakeTime to set
	 */
	public void setWakeTime(String wakeTime) {
		this.wakeTime = wakeTime;
	}
	
	/**
	 * @return the deep
	 */
	public String getDeep() {
		return deep;
	}

	/**
	 * @param deep the deep to set
	 */
	public void setDeep(String deep) {
		this.deep = deep;
	}
	
	/**
	 * @return the light
	 */
	public String getLight() {
		return light;
	}

	/**
	 * @param light the light to set
	 */
	public void setLight(String light) {
		this.light = light;
	}
	
	/**
	 * @return the turn
	 */
	public String getTurn() {
		return turn;
	}

	/**
	 * @param turn the turn to set
	 */
	public void setTurn(String turn) {
		this.turn = turn;
	}
	
	/**
	 * @return the wake
	 */
	public String getWake() {
		return wake;
	}

	/**
	 * @param wake the wake to set
	 */
	public void setWake(String wake) {
		this.wake = wake;
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