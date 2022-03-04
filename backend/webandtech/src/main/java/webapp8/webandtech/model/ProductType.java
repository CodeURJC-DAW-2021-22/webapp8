package webapp8.webandtech.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="producttypes")
public class ProductType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idproducttype;
	@Column
	private String typename;
	
	
	public ProductType() {}

    public ProductType(int idproducttype, String typename) {
        super();
        this.idproducttype = idproducttype;
        this.typename = typename;
    }

    public int getIdproducttype() {
        return idproducttype;
    }


    public void setIdproducttype(int idproducttype) {
        this.idproducttype = idproducttype;
    }


    public String getTypename() {
        return typename;
    }


    public void setTypename(String typename) {
        this.typename = typename;
    }

    
}
