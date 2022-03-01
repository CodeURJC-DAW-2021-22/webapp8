package webapp8.webandtech.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import webapp8.webandtech.model.ProductType;



public interface ProductTypeRepository extends JpaRepository<ProductType, Integer>{
	
	public Optional<ProductType> findBytypename(String typename);
	public Optional<ProductType> findByidproducttype(String producttype);
	public List<ProductType> findAll();
	boolean existsIdproducttypesByTypename(String typename);

}