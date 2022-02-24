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
@Table(name = "products")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idproduct;
	@Column
	private String nameproduct;
	@Column
	private float price;
	@Column(columnDefinition = "TEXT")
	private String description;
	@Column
	private float ratingProducto;
	@Column
	private String productType;
	@Column
	private String productcategory;
	@Column
	private String productbrand;
	@Lob
	@JsonIgnore
	private Blob image1;
	@Lob
	@JsonIgnore
	private Blob image2;
	@Lob
	@JsonIgnore
	private Blob image3;
	
	public Product() {}

	public Product(int idproduct, String nameproduct, float price, String description, float ratingProducto,
			String productType, String productcategory, String productbrand, Blob image1, Blob image2, Blob image3) {
		super();
		this.idproduct = idproduct;
		this.nameproduct = nameproduct;
		this.price = price;
		this.description = description;
		this.ratingProducto = ratingProducto;
		this.productType = productType;
		this.productcategory = productcategory;
		this.productbrand = productbrand;
		this.image1 = image1;
		this.image2 = image2;
		this.image3 = image3;
	}

	public int getIdproduct() {
		return idproduct;
	}

	public void setIdproduct(int idproduct) {
		this.idproduct = idproduct;
	}

	public String getNameproduct() {
		return nameproduct;
	}

	public void setNameproduct(String nameproduct) {
		this.nameproduct = nameproduct;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getRatingProducto() {
		return ratingProducto;
	}

	public void setRatingProducto(float ratingProducto) {
		this.ratingProducto = ratingProducto;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductcategory() {
		return productcategory;
	}

	public void setProductcategory(String productcategory) {
		this.productcategory = productcategory;
	}

	public String getProductbrand() {
		return productbrand;
	}

	public void setProductbrand(String productbrand) {
		this.productbrand = productbrand;
	}

	public Blob getImage1() {
		return image1;
	}

	public void setImage1(Blob image1) {
		this.image1 = image1;
	}

	public Blob getImage2() {
		return image2;
	}

	public void setImage2(Blob image2) {
		this.image2 = image2;
	}

	public Blob getImage3() {
		return image3;
	}

	public void setImage3(Blob image3) {
		this.image3 = image3;
	}
	


}
