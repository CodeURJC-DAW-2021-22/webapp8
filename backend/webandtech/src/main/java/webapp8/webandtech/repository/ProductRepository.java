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
    public Product findByidproduct(Integer idproduct);
    public Product findBynameproduct (String nameproduct);
    public List<Product> findByproductType(ProductType typename);
    public Page<Product> findByproductcategory(String productcategory);
    public Page<Product> findByproductbrand(Brand brandname);
    //public boolean existsidproductBynameproduct(String nameproduct);
}
