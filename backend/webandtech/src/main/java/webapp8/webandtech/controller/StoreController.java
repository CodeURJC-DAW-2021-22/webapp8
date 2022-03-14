package webapp8.webandtech.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import webapp8.webandtech.model.CarShop;
import webapp8.webandtech.model.Product;
import webapp8.webandtech.service.ProductService;


@Controller
@CrossOrigin
public class StoreController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CarShop carShop;

    @PostMapping("/carShop")
	private void setCarShop(HttpServletRequest request, HttpServletResponse response, @RequestParam int idproduct) throws IOException {
        Product product = productService.getProduct(idproduct);
        carShop.getCarShop().add(product);

		System.out.println(carShop.getCarShop().size());


		response.sendRedirect("/index");;
	}
    @PostMapping("/deleteProductShopCar")
    public void deleteProductShopCar(HttpServletResponse response, HttpServletRequest request, @RequestParam int idproduct) throws IOException {
        Product product = productService.getProduct(idproduct);
        
        boolean car = carShop.getCarShop().remove(product);
    	response.sendRedirect("/checkout");
    }
}
