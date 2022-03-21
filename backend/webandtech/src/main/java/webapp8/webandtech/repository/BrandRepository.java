package webapp8.webandtech.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import webapp8.webandtech.model.Brand;


public interface BrandRepository extends JpaRepository<Brand, Integer>{
	
	//public Brand findByIdproduct(Product idproduct);
	public Optional<Brand> findBybrandname(String brandname);
	public Optional<Brand> findByidbrand(String brandname);
	public List<Brand> findAll();
	boolean existsIdbrandsByBrandname(String brandname);
	//public Long deleteByIdproduct (Product idproduct);
}

