package webapp8.webandtech.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import webapp8.webandtech.model.CarShop;
import webapp8.webandtech.model.Order;
import webapp8.webandtech.model.OrderModel;
import webapp8.webandtech.model.Product;
import webapp8.webandtech.model.Rating;
import webapp8.webandtech.model.User;
import webapp8.webandtech.service.LoaderService;
import webapp8.webandtech.service.OrderService;
import webapp8.webandtech.service.ProductService;
import webapp8.webandtech.service.RatingService;
import webapp8.webandtech.service.UserService;

@Controller
@CrossOrigin
public class NavigationController {

	
	@Autowired
    private CarShop carShop;
    @Autowired
    private UserService userService;
	@Autowired
    private RatingService ratingService;
	@Autowired
    private LoaderService loaderService;
	@Autowired
    private ProductService productService;
	@Autowired
    private OrderService orderService;

    @GetMapping("/")
	private void getInitialPage(Model model,HttpServletRequest request, HttpServletResponse response) throws IOException {
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());
		loaderService.Load();
		response.sendRedirect("/index");		
	}
    @GetMapping("/login")
	private String getSignIn(Model model,HttpServletRequest request, HttpSession sesion, HttpServletResponse response) throws IOException {
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());
		model.addAttribute("login", (request.getSession(false) != null));
		if(request.getUserPrincipal() != null){
			response.sendRedirect("/index");
		}
		
		return "login";
	}
    @GetMapping("/admin/newProduct")
	private String getNewProduct(Model model,HttpServletRequest request, HttpSession sesion, HttpServletResponse response) throws IOException {
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());
		if(request.getUserPrincipal() != null){
			model.addAttribute("user", request.getUserPrincipal().getName());
			model.addAttribute("login", (request.getUserPrincipal() != null));
		}else{
			model.addAttribute("login", false);
		}
		model.addAttribute("admin", request.isUserInRole("ADMIN"));
		model.addAttribute("userr", request.isUserInRole("USER"));
		
		return "newProduct";
	}
    @GetMapping("/index")
	private String getIndex(Model model,HttpServletRequest request) throws IOException {
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());
		if(request.getUserPrincipal() != null){
			model.addAttribute("user", request.getUserPrincipal().getName());
			model.addAttribute("login", (request.getUserPrincipal() != null));
		}else{
			model.addAttribute("login", false);
		}
		model.addAttribute("products", productService.getNewProucts());
		model.addAttribute("admin", request.isUserInRole("ADMIN"));
		model.addAttribute("userr", request.isUserInRole("USER"));

		return "index";
	}
    @GetMapping("/checkout")
	private String getCheckOut(Model model,HttpServletRequest request) throws IOException {
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());
		List<Product> productos = carShop.getCarShop();
		float totalPrice = 0;
		for (Product product : productos){
			totalPrice = totalPrice + product.getPrice();
		}
		if(request.getUserPrincipal() != null){
			model.addAttribute("user", request.getUserPrincipal().getName());
			model.addAttribute("login", (request.getUserPrincipal() != null));
		}else{
			model.addAttribute("login", false);
		}
		System.out.println(carShop.getCarShop());
		model.addAttribute("priceCar", totalPrice);
		model.addAttribute("shopCar", productos);
		model.addAttribute("admin", request.isUserInRole("ADMIN"));
		model.addAttribute("userr", request.isUserInRole("USER"));


		return "checkout";
	}
    @GetMapping("/components")
	private String getComponents(Model model, HttpServletRequest request, @RequestParam(required = false) String typeProduct) throws IOException {
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());
		if(request.getUserPrincipal() != null){
			model.addAttribute("user", request.getUserPrincipal().getName());
			model.addAttribute("login", (request.getUserPrincipal() != null));
		}else{
			model.addAttribute("login", false);
		}
		
		model.addAttribute("admin", request.isUserInRole("ADMIN"));
		model.addAttribute("userr", request.isUserInRole("USER"));
		model.addAttribute("typeProduct", typeProduct);
		if(typeProduct != null){
			model.addAttribute("products", productService.getProductType(typeProduct));
			model.addAttribute("type", typeProduct);
			model.addAttribute("category", "undefined");
			
			System.out.println(typeProduct);
		} else{
			model.addAttribute("products", productService.getComponents());
			model.addAttribute("type", "undefined");
			model.addAttribute("category", "Componente");
			System.out.println(model.getClass());
		}

		return "productsByFeatures";
	}
    @GetMapping("/peripherals")
	private String getPeripherals(Model model, HttpServletRequest request, @RequestParam(required = false) String typeProduct) throws IOException {
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());
		if(request.getUserPrincipal() != null){
			model.addAttribute("user", request.getUserPrincipal().getName());
			model.addAttribute("login", (request.getUserPrincipal() != null));
		}else{
			model.addAttribute("login", false);
		}
		
		model.addAttribute("admin", request.isUserInRole("ADMIN"));
		model.addAttribute("userr", request.isUserInRole("USER"));
		model.addAttribute("typeProduct", typeProduct);
		if(typeProduct != null){
			model.addAttribute("products", productService.getProductType(typeProduct));
			model.addAttribute("type", typeProduct);
			model.addAttribute("category", "undefined");
			System.out.println(typeProduct);
		} else{
			model.addAttribute("products", productService.getPeripherals());
			model.addAttribute("type", "undefined");
			model.addAttribute("category", "Periferico");
		}

		return "productsByFeatures";
	}

    @GetMapping("/phones")
	private String getPhones(Model model, HttpServletRequest request, @RequestParam(required = false) String typeProduct) throws IOException {
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());
		if(request.getUserPrincipal() != null){
			model.addAttribute("user", request.getUserPrincipal().getName());
			model.addAttribute("login", (request.getUserPrincipal() != null));
		}else{
			model.addAttribute("login", false);
		}
		
		model.addAttribute("admin", request.isUserInRole("ADMIN"));
		model.addAttribute("userr", request.isUserInRole("USER"));
		model.addAttribute("typeProduct", typeProduct);

		model.addAttribute("products", productService.getPhones());
		model.addAttribute("type", "undefined");
		model.addAttribute("category", "telefono");

		return "productsByFeatures";
	}

    @GetMapping("/products/{idproduct}")
	private String getProduct(Model model, HttpServletRequest request, @PathVariable int idproduct) throws IOException {
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());
		if(request.getUserPrincipal() != null){
			model.addAttribute("user", request.getUserPrincipal().getName());
			model.addAttribute("login", (request.getUserPrincipal() != null));
		}else{
			model.addAttribute("login", false);
		}
		Product product = productService.getProduct(idproduct);
		List<Rating> ratings = ratingService.getRating(product);
		
		model.addAttribute("ratings", ratings);
		model.addAttribute("product", product);
		model.addAttribute("admin", request.isUserInRole("ADMIN"));
		model.addAttribute("userr", request.isUserInRole("USER"));
		
		return "productElement";
	}

	@GetMapping("/users/profile/{username}")
	private String getUserProfile(Model model,HttpServletRequest request) throws IOException {
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());
		String userName = request.getUserPrincipal().getName();
		if(request.getUserPrincipal() != null){
			model.addAttribute("user", request.getUserPrincipal().getName());
			model.addAttribute("login", (request.getUserPrincipal() != null));
		}else{
			model.addAttribute("login", false);
		}
		model.addAttribute("usersdata", userService.getUser(userName));
		model.addAttribute("admin", request.isUserInRole("ADMIN"));
		model.addAttribute("userr", request.isUserInRole("USER"));

		return "perfil";
	}
	@GetMapping("/users/orders/{username}")
	private String getUserOrders(Model model,HttpServletRequest request) throws IOException {
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());
		String userName = request.getUserPrincipal().getName();
		User userData = userService.getUser(userName);
		List<Order> orders = orderService.getUserOrders(userData);
		List<OrderModel> orderModelData = new ArrayList<>();
		for (Order order : orders){
			String[] parts = order.getIdproducts().split("/");
			List<Product> products = new ArrayList<>();
			OrderModel ordermodel = new OrderModel();
			for (int i = 0; i < parts.length; i++){
				products.add(productService.getProduct(Integer. parseInt(parts[i])));
			}
			ordermodel.setOrder(order);
			ordermodel.setProducts(products);
			orderModelData.add(ordermodel);
		}
		if(request.getUserPrincipal() != null){
			model.addAttribute("user", request.getUserPrincipal().getName());
			model.addAttribute("login", (request.getUserPrincipal() != null));
		}else{
			model.addAttribute("login", false);
		}
		model.addAttribute("usersdata", userService.getUser(userName));
		model.addAttribute("usersorders", orderModelData);
		model.addAttribute("admin", request.isUserInRole("ADMIN"));
		model.addAttribute("userr", request.isUserInRole("USER"));

		return "transacciones";
	}
	@GetMapping("/contact")
	private String getContact(Model model,HttpServletRequest request) throws IOException {
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());
		String userName = request.getUserPrincipal().getName();
		if(request.getUserPrincipal() != null){
			model.addAttribute("user", request.getUserPrincipal().getName());
			model.addAttribute("login", (request.getUserPrincipal() != null));
		}else{
			model.addAttribute("login", false);
		}
		model.addAttribute("usersdata", userService.getUser(userName));
		model.addAttribute("admin", request.isUserInRole("ADMIN"));
		model.addAttribute("userr", request.isUserInRole("USER"));

		return "contact";
	}
	@GetMapping("/users/security/{username}")
	private String getUserSecurity(Model model,HttpServletRequest request) throws IOException {
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());
		String userName = request.getUserPrincipal().getName();
		if(request.getUserPrincipal() != null){
			model.addAttribute("user", request.getUserPrincipal().getName());
			model.addAttribute("login", (request.getUserPrincipal() != null));
		}else{
			model.addAttribute("login", false);
		}
		model.addAttribute("usersdata", userService.getUser(userName));
		model.addAttribute("admin", request.isUserInRole("ADMIN"));
		model.addAttribute("userr", request.isUserInRole("USER"));

		return "seguridad";
	}

	@GetMapping("/products/modifyProduct/{idproduct}")
	private String getModifyProduct(Model model,HttpServletRequest request, @PathVariable int idproduct, HttpSession sesion, HttpServletResponse response) throws IOException {
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());
		if(request.getUserPrincipal() != null){
			model.addAttribute("user", request.getUserPrincipal().getName());
			model.addAttribute("login", (request.getUserPrincipal() != null));
		}else{
			model.addAttribute("login", false);
		}
		
		Product product = productService.getProduct(idproduct);

		if (product.getProductcategory().equals("Componente")){
			model.addAttribute("componente", "componente");
		} else if (product.getProductcategory().equals("Periferico")){
			model.addAttribute("periferico", "Periferico");
		} else {
			model.addAttribute("telefono", "telefono");
		}

		if (product.getProductType().equals("Placa base")){
			model.addAttribute("placa", "placa");
		} else if (product.getProductType().equals("Disco duro")){
			model.addAttribute("disco", "disco"); 
		} else if (product.getProductType().equals("Procesador")){
			model.addAttribute("prcesador", "prcesador");
		} else if (product.getProductType().equals("Tarjeta grafica")){
			model.addAttribute("tarjeta", "tarjeta");
		} else if (product.getProductType().equals("Teclado")){
			model.addAttribute("teclado", "teclado");
		} else if (product.getProductType().equals("Raton")){
			model.addAttribute("raton", "raton");
		} else if (product.getProductType().equals("Monitor")){
			model.addAttribute("monitor", "monitor");
		} else if (product.getProductType().equals("Auriculares")){
			model.addAttribute("auriculares", "auriculares");
		} else {
			model.addAttribute("telefono", "telefono");
		} 
		model.addAttribute("product", product);
		model.addAttribute("admin", request.isUserInRole("ADMIN"));
		model.addAttribute("userr", request.isUserInRole("USER"));
		
		return "modifyProduct";
	}

}
