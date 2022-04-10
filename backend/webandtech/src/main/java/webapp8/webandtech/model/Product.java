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
@Table(name = "products")
public class Product {
	
	public interface Basic{}
	public interface Detailed extends Product.Basic{}

	@JsonView(Basic.class)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idproduct;
	@JsonView(Basic.class)
	@Column
	private String nameproduct;
	@JsonView(Basic.class)
	@Column
	private float price;
	@JsonView(Basic.class)
	@Column(columnDefinition = "TEXT")
	private String description;
	@JsonView(Basic.class)
	@Column
	private float ratingProducto;
	@JsonView(Detailed.class)
	@Column
	private String productType;
	@JsonView(Detailed.class)
	@Column
	private String productcategory;
	@JsonView(Detailed.class)
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
	@Column
	private boolean img1;
	@Column
	private boolean img2;
	@Column
	private boolean img3;
	
	public Product() {}

	public Product(int idproduct, String nameproduct, float price, String description, float ratingProducto,
			String productType, String productcategory, String productbrand, Blob image1, Blob image2, Blob image3, boolean img1, boolean img2, boolean img3) {
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
		this.img1 = img1;
		this.img2 = img2;
		this.img3 = img3;
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

	public boolean getImg1() {
		return img1;
	}

	public void setImg1(boolean img1) {
		this.img1 = img1;
	}

	public boolean getImg2() {
		return img2;
	}

	public void setImg2(boolean img2) {
		this.img2 = img2;
	}

	public boolean getImg3() {
		return img3;
	}

	public void setImg3(boolean img3) {
		this.img3 = img3;
	}
	


}
