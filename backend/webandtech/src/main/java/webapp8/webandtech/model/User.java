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

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idusers;
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

}
