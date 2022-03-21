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
@Table(name = "orders")
public class Order {

	public interface Basic{}
	public interface Detailed extends Order.Basic{}

	@JsonView(Basic.class)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idorder;
	@JsonView(Basic.class)
	@Column
	private float price;
	@JsonView(Detailed.class)
	@OneToOne
	@JoinColumn(name = "iduser", referencedColumnName = "iduser")
	private User iduser;
	@JsonView(Basic.class)
	@Column
	private String idproducts;
	@JsonView(Basic.class)
	@Column
	private String orderdate;
	
	public Order() {}

	public Order(int idorder, float price, User iduser, String idproducts, String date) {
		super();
		this.idorder = idorder;
		this.price = price;
		this.iduser = iduser;
		this.idproducts = idproducts;
		this.orderdate = date;
	}

	public int getIdorder() {
		return idorder;
	}

	public void setIdorder(int idorder) {
		this.idorder = idorder;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public User getIduser() {
		return iduser;
	}

	public void setIduser(User iduser) {
		this.iduser = iduser;
	}

	public String getIdproducts() {
		return idproducts;
	}

	public void setIdproducts(String idproducts) {
		this.idproducts = idproducts;
	}

	public String getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(String date) {
		this.orderdate = date;
	}
	
	
	
}
