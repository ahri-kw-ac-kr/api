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
	private int deep;
	
	@Column
	private int light;
	
	@Column
	private int turn;
	
	@Column
	private int wake;
	
	@Column
	private int turnHour;
	
	@Column
	private int total;

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
	public int getDeep() {
		return deep;
	}

	/**
	 * @param deep the deep to set
	 */
	public void setDeep(int deep) {
		this.deep = deep;
	}
	
	/**
	 * @return the light
	 */
	public int getLight() {
		return light;
	}

	/**
	 * @param light the light to set
	 */
	public void setLight(int light) {
		this.light = light;
	}
	
	/**
	 * @return the turn
	 */
	public int getTurn() {
		return turn;
	}

	/**
	 * @param turn the turn to set
	 */
	public void setTurn(int turn) {
		this.turn = turn;
	}
	
	/**
	 * @return the wake
	 */
	public int getWake() {
		return wake;
	}

	/**
	 * @param wake the wake to set
	 */
	public void setWake(int wake) {
		this.wake = wake;
	}
	
	/**
	 * @return the wake
	 */
	public int getTurnHour() {
		return turnHour;
	}

	/**
	 * @param wake the wake to set
	 */
	public void setTurnHour(int turnHour) {
		this.turnHour = turnHour;
	}
	
	/**
	 * @return the wake
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * @param wake the wake to set
	 */
	public void setTotal(int total) {
		this.total = total;
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