package webapp8.webandtech.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import webapp8.webandtech.model.Brand;
import webapp8.webandtech.model.Product;
import webapp8.webandtech.model.ProductType;

public interface ProductRepository extends JpaRepository<Product, Integer>{
    public Page<Product> findAll(Pageable page);
    public List<Product> findAll();
    public Product findByIdproduct(Integer idproduct);
    public Product findBynameproduct (String nameproduct);
    public Page<Product> findByproductType(String typename, Pageable page);
    public List<Product> findByproductType(String typename);
    public List<Product> findTopByOrderByIdproductDesc();
    public Page<Product> findByproductcategory(String productcategory, Pageable page);
    public List<Product> findByproductcategory(String productcategory);
    public Page<Product> findByproductbrand(Brand brandname, Pageable page);
    
    //public boolean existsidproductBynameproduct(String nameproduct);
}
