package webapp8.webandtech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import webapp8.webandtech.model.Product;
import webapp8.webandtech.model.Rating;
import webapp8.webandtech.repository.RatingRepository;

@Service
public class RatingService {

    @Autowired
	private RatingRepository ratingRepository;

    public void save(Rating rating){
        ratingRepository.save(rating);
    }
    public List<Rating> getRating(Product idProduct){
		Page<Rating> components = ratingRepository.findByIdproduct(idProduct,PageRequest.of(0, 10, Sort.by("idproduct").descending()));
		return components.getContent();
	}
	public Page<Rating> getMoreRating(Pageable page, Product idProduct){
		Page<Rating> components = ratingRepository.findByIdproduct(idProduct,page);
		return components;
	}
}
