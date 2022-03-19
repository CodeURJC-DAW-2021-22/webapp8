package webapp8.webandtech.controller;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
    @GetMapping("/users/newOrders")
    public void createNewOrders(HttpServletResponse response, HttpServletRequest request) throws IOException {
        // User user = userService.getUser(request.getUserPrincipal().getName());

        // ByteArrayInputStream bis = GeneratePdfReport.citiesReport(user);

        // var headers = new HttpHeaders();
        // headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");

        // return ResponseEntity
        //         .ok()
        //         .headers(headers)
        //         .contentType(MediaType.APPLICATION_PDF)
        //         .body(new InputStreamResource(bis));
    }
}
