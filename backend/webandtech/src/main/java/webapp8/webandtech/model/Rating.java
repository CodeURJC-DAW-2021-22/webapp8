package webapp8.webandtech.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name = "ratings")
public class Rating {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idrating;
	@Column
	private float reviewrating;
	@Column(columnDefinition = "TEXT")
	private String ratingtext;
	@OneToOne
	@JoinColumn(name = "idproduct", referencedColumnName = "idproduct")
	private Product idproduct;
	@OneToOne
	@JoinColumn(name = "iduser", referencedColumnName = "iduser")
	private User iduser;
	
	public Rating() {}
	public Rating(int idrating, float reviewrating, String ratingtext, Product idproduct, User iduser) {
		super();
		this.idrating = idrating;
		this.reviewrating = reviewrating;
		this.ratingtext = ratingtext;
		this.idproduct = idproduct;
		this.iduser = iduser;
	}
	
	public int getIdrating() {
		return idrating;
	}
	public void setIdrating(int idrating) {
		this.idrating = idrating;
	}
	public float getReviewrating() {
		return reviewrating;
	}
	public void setReviewrating(float reviewrating) {
		this.reviewrating = reviewrating;
	}
	public String getRatingtext() {
		return ratingtext;
	}
	public void setRatingtext(String ratingtext) {
		this.ratingtext = ratingtext;
	}
	public Product getIdproduct() {
		return idproduct;
	}
	public void setIdproduct(Product idproduct) {
		this.idproduct = idproduct;
	}
	public User getIduser() {
		return iduser;
	}
	public void setIduser(User iduser) {
		this.iduser = iduser;
	}
	
	

}
