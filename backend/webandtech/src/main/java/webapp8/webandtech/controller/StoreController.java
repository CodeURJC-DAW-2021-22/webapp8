package webapp8.webandtech.controller;



import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import webapp8.webandtech.model.CarShop;
import webapp8.webandtech.model.Order;
import webapp8.webandtech.model.Product;
import webapp8.webandtech.model.Rating;
import webapp8.webandtech.model.User;
import webapp8.webandtech.service.OrderService;
import webapp8.webandtech.service.PDFGeneratorService;
import webapp8.webandtech.service.ProductService;
import webapp8.webandtech.service.RatingService;
import webapp8.webandtech.service.UserService;


@Controller
@CrossOrigin
public class StoreController {

    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private CarShop carShop;
    @Autowired
    private RatingService ratingService;

    private final PDFGeneratorService pdfGeneratorService;
    
    public StoreController(PDFGeneratorService pdfGeneratorService) {
        this.pdfGeneratorService = pdfGeneratorService;
    }

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
        User user = userService.getUser(request.getUserPrincipal().getName());
        String idsProducts = "";
        List<Product> carts = carShop.getCarShop();
        float price = 0;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        dtf.format(LocalDateTime.now());
        String date = dtf.format(LocalDateTime.now());
        for (Product cart : carts) {
            String id = Integer.toString(cart.getIdproduct());
            idsProducts = idsProducts+id+"/";
            price = price + cart.getPrice();
        }
        Order order = new Order();
		order.setPrice(price);
		order.setIduser(user);
		order.setIdproducts(idsProducts);
		order.setOrderdate(date);
        orderService.saveOrder(order);
        carShop.getCarShop().clear();
        response.sendRedirect("/index");
    
    }
    @GetMapping("/users/bill")
    public void createNewBill(HttpServletResponse response, HttpServletRequest request) throws IOException {
        User user = userService.getUser(request.getUserPrincipal().getName());
        String idsProducts = "";
        List<Product> carts = carShop.getCarShop();
        float price = 0;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        dtf.format(LocalDateTime.now());
        String date = dtf.format(LocalDateTime.now());
        for (Product cart : carts) {
            String id = Integer.toString(cart.getIdproduct());
            idsProducts = idsProducts+id+"/";
            price = price + cart.getPrice();
        }
        Order order = new Order();
		order.setPrice(price);
		order.setIduser(user);
		order.setIdproducts(idsProducts);
		order.setOrderdate(date);
        //new pdf
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        this.pdfGeneratorService.export(response, order, carts, user);
        
        
    }
}
