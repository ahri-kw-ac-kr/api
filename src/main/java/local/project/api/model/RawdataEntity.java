package local.project.api.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "rawdata")
@JsonIgnoreProperties({"createdAt", "del"})
public class RawdataEntity extends DefaultEntity {

	@Column
	private int startTick;

	@Column
	private int endTick;

	@Column
	private int totalLux;

	@Column
	private short steps;

	@Column
	private short avgLux;

	@Column
	private short avgTemp;

	@Column
	private short vectorX;

	@Column
	private short vectorY;

	@Column
	private short vectorZ;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private UserEntity user;

	public int getStartTick() { 
		return startTick;
	}
	public void setStartTick(int startTick) {
		this.startTick = startTick;
	}

	public int getEndTick() { 
		return endTick;
	}
	public void setEndTick(int endTick) {
		this.endTick = endTick;
	}

	public int getTotalLux() { 
		return totalLux;
	}
	public void setTotalLux(int totalLux) {
		this.totalLux = totalLux;
	}

	public short getSteps() { 
		return steps;
	}
	public void setSteps(short steps) {
		this.steps = steps;
	}

	public short getAvgLux() { 
		return avgLux;
	}
	public void setAvgLux(short avgLux) {
		this.avgLux = avgLux;
	}

	public short getAvgTemp() { 
		return avgTemp;
	}
	public void setAvgTemp(short avgTemp) {
		this.avgTemp = avgTemp;
	}

	public short getVectorX() { 
		return vectorX;
	}
	public void setVectorX(short vectorX) {
		this.vectorX = vectorX;
	}

	public short getVectorY() { 
		return vectorY;
	}
	public void setVectorY(short vectorY) {
		this.vectorY = vectorY;
	}

	public short getVectorZ() { 
		return vectorZ;
	}
	public void setVectorZ(short vectorZ) {
		this.vectorZ = vectorZ;
	}

	public UserEntity getUser() {
		return user;
	}
	public void setUser(UserEntity user) {
		this.user = user;
	}
	
}