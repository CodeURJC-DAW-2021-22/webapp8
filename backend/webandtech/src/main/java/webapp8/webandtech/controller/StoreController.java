package webapp8.webandtech.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import webapp8.webandtech.model.CarShop;
import webapp8.webandtech.model.Product;
import webapp8.webandtech.model.Rating;
import webapp8.webandtech.model.User;
import webapp8.webandtech.repository.UserRepository;
import webapp8.webandtech.service.ProductService;
import webapp8.webandtech.service.RatingService;
import webapp8.webandtech.service.UserService;


@Controller
@CrossOrigin
public class StoreController {

    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private CarShop carShop;
    @Autowired
    private RatingService ratingService;

    @PostMapping("/carShop")
	private void setCarShop(HttpServletRequest request, HttpServletResponse response, @RequestParam int idproduct) throws IOException {
        Product product = productService.getProduct(idproduct);
        carShop.getCarShop().add(product);

		System.out.println(carShop.getCarShop().size());


		response.sendRedirect("/index");;
	}
    @PostMapping("/users/deleteProductShopCar")
    public void deleteProductShopCar(HttpServletResponse response, HttpServletRequest request, @RequestParam int idproduct) throws IOException {
        Product product = productService.getProduct(idproduct);
        
        carShop.getCarShop().removeIf(carShop -> carShop.getIdproduct() == product.getIdproduct());
    	response.sendRedirect("/checkout");
    }
    @PostMapping("/users/newRating")
    public void createNewRating(Rating rating, HttpServletResponse response, HttpServletRequest request, @RequestParam int idproduct, @RequestParam String ratingtext) throws IOException {
        User user = userService.getUser(request.getUserPrincipal().getName());
        Product product = productService.getProduct(idproduct);
        rating.setIduser(user);
        rating.setIdproduct(product);
        ratingService.save(rating);
    	response.sendRedirect("/products/"+idproduct);
    }
    @PostMapping("/users/newOrders")
    public void createNewOrders(List<Product> products, HttpServletResponse response, HttpServletRequest request, @RequestParam String user) throws IOException {
        User userData = userService.getUser(user);
        products.size();

    	response.sendRedirect("/index");
    }
}
