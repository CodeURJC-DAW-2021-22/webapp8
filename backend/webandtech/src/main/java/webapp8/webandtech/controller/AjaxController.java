package webapp8.webandtech.controller;





import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import webapp8.webandtech.model.Order;
import webapp8.webandtech.model.Product;
import webapp8.webandtech.model.User;
import webapp8.webandtech.service.OrderService;
import webapp8.webandtech.service.ProductService;
import webapp8.webandtech.service.UserService;

@RestController
@CrossOrigin
public class AjaxController {
    @Autowired
    private ProductService productService;
	@Autowired
    private OrderService orderService;
	@Autowired
    private UserService userService;

    @GetMapping("/products/moreProducts")
	private Page<Product> getMoreProducts(Pageable page){
		return productService.getProductsPage(page);
	}
    @GetMapping("/products/getMoreProductsPage")
	private Page<Product> getMoreProductsPage(Pageable page, @RequestParam(required = false) String productType, @RequestParam(required = false) String productcategory){
		Page<Product> products;
		if(!(productType.equals("undefined"))){
			products = productService.getMoreProductType(page,productType);
		} else{
			if(productcategory.equals("Componente")){
				products = productService.getMoreComponents(page);
			} else if(productcategory.equals("Periferico")){
				products = productService.getMorePeripherals(page);
			} else{
				products = productService.getMorePhones(page);
			}
		}
		return products;
	}
	@GetMapping("/admin/getMoreOrders")
	private Page<Order> getOrders(Pageable page){
		return orderService.getMoreAllOrders(page);
	}
	@GetMapping("/admin/getMoreUsers")
	private Page<User> getMoreUsers(Pageable page){
		return userService.getUsersPage(page);
	}

	@GetMapping("/users/getMoreOrders")
	private Page<Order> getMoreUserOrders(Pageable page, @RequestParam String username){
		User user = userService.getUser(username);
		return orderService.getMoreUserOrders(user, page);
		
	}
	
}
