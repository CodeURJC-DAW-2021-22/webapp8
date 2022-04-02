package webapp8.webandtech.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name="roles")
public class Rol {

	public interface Basic{}
	public interface Detailed extends Rol.Basic{}

	@JsonView(Basic.class)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idroles;
	@JsonView(Basic.class)
	@Column
	private String rol;
	@JsonView(Detailed.class)
	@OneToOne
	@JoinColumn(name = "iduser", referencedColumnName = "iduser")
	private User iduser;
	
	
	public Rol() {}
	public Rol(String rol, User iduser) {
		super();
		this.rol = rol;
		this.iduser = iduser;
	}
	public int getIdroles() {
		return idroles;
	}
	public void setIdroles(int idroles) {
		this.idroles = idroles;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public User getIduser() {
		return iduser;
	}
	public void setIduser(User iduser) {
		this.iduser = iduser;
	}
}
