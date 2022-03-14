package webapp8.webandtech.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import webapp8.webandtech.model.Product;
import webapp8.webandtech.model.Rating;
import webapp8.webandtech.model.User;

public interface RatingRepository extends JpaRepository<Rating, Integer>{
    public List<Rating> findByidproduct(Product idproduct);
    public Page<Rating> findByidproduct(Product idproduct, Pageable page);
	public List<Rating> findByiduser(User iduser);
	public Rating findByIdproductAndIduser(Product idproduct, User iduser);
	public Long deleteByIduser(User iduser);
	public Long deleteByIdproduct (Product idproduct);
	//public boolean existsIdratingByIduserAndIdproduct(User iduser, Product idproduct);

}
