package webapp8.webandtech.model;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "users")
public class User {
	
	public interface Basic{}
	public interface Detailed extends User.Basic{}
	
	@JsonView(Basic.class)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int iduser;
	@Column
	private String email;
	@Column
	private String username;
	@Column
	private String pass;
	@Column
	private String name;
	@Lob
	@JsonIgnore
	private Blob userimg;
	@Column
	private boolean userprofile;
	@Column
	private String address;
	
	public User() {}
	public User(int iduser, String email, String username, String pass, String name, Blob userimg, boolean userprofile,
			String address) {
		super();
		this.iduser = iduser;
		this.email = email;
		this.username = username;
		this.pass = pass;
		this.name = name;
		this.userimg = userimg;
		this.userprofile = userprofile;
		this.address = address;
	}
	
	public int getIduser() {
		return iduser;
	}
	public void setIduser(int iduser) {
		this.iduser = iduser;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Blob getUserimg() {
		return userimg;
	}
	public void setUserimg(Blob userimg) {
		this.userimg = userimg;
	}
	public boolean isUserprofile() {
		return userprofile;
	}
	public void setUserprofile(boolean userprofile) {
		this.userprofile = userprofile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
