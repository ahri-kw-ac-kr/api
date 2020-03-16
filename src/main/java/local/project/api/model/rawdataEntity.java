package local.project.api.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "rawdata")
public class rawdataEntity extends DefaultEntity {

	@Column
	private int s_tick, e_tick, t_lux;
	@Column

	private short steps, avg_lux, avg_k, vector_x, vector_y, vector_z;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private UserEntity user;

	public int getS_tick() { 
		return s_tick;
	}
	public void setS_tick(int s_tick) {
		this.s_tick = s_tick;
	}

	public int getE_tick() { 
		return e_tick;
	}
	public void setE_tick(int e_tick) {
		this.e_tick = e_tick;
	}

	public int getT_lux() { 
		return t_lux;
	}
	public void setT_lux(int t_lux) {
		this.t_lux = t_lux;
	}

	public short getSteps() { 
		return steps;
	}
	public void setSteps(short steps) {
		this.steps = steps;
	}

	public short getAvg_lux() { 
		return avg_lux;
	}
	public void setAvg_lux(short avg_lux) {
		this.avg_lux = avg_lux;
	}

	public short getAvg_k() { 
		return avg_k;
	}
	public void setAvg_k(short avg_k) {
		this.avg_k = avg_k;
	}

	public short getVector_x() { 
		return vector_x;
	}
	public void setVector_x(short vector_x) {
		this.vector_x = vector_x;
	}

	public short getVector_y() { 
		return vector_y;
	}
	public void setVector_y(short vector_y) {
		this.vector_y = vector_y;
	}

	public short getVector_z() { 
		return vector_z;
	}
	public void setVector_z(short vector_z) {
		this.vector_z = vector_z;
	}

	public UserEntity getUser() {
		return user;
	}
	public void setUser(UserEntity user) {
		this.user = user;
	}
	
}