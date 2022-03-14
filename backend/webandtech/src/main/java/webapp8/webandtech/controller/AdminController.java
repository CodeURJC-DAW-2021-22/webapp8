package webapp8.webandtech.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

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

import webapp8.webandtech.model.Order;
import webapp8.webandtech.model.OrderModel;
import webapp8.webandtech.model.Product;
import webapp8.webandtech.model.Statistics;
import webapp8.webandtech.model.User;
import webapp8.webandtech.service.AdminService;
import webapp8.webandtech.service.OrderService;
import webapp8.webandtech.service.ProductService;
import webapp8.webandtech.service.UserService;

@Controller
@CrossOrigin
public class AdminController {

    
    @Autowired
    private UserService userService;
	@Autowired
    private AdminService adminService;
	@Autowired
    private ProductService productService;
	@Autowired
    private OrderService orderService;


    @GetMapping("/admin/orders")
	private String getAdminOrders(Model model,HttpServletRequest request) throws IOException {
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());
		String userName = request.getUserPrincipal().getName();
		List<Order> orders = orderService.getAllOrders();
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
		model.addAttribute("adminsorders", orderModelData);
		model.addAttribute("admin", request.isUserInRole("ADMIN"));
		model.addAttribute("userr", request.isUserInRole("USER"));

		return "orderList";
	}
	@GetMapping("/admin/users")
	private String getUserList(Model model,HttpServletRequest request) throws IOException {
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());
		if(request.getUserPrincipal() != null){
			model.addAttribute("user", request.getUserPrincipal().getName());
			model.addAttribute("login", (request.getUserPrincipal() != null));
		}else{
			model.addAttribute("login", false);
		}
		model.addAttribute("userList", userService.getAllUsersPage());
		model.addAttribute("admin", request.isUserInRole("ADMIN"));
		model.addAttribute("userr", request.isUserInRole("USER"));

		return "userList";
	}
	@GetMapping("/admin/graphics")
	private String getGraphics(Model model,HttpServletRequest request) throws IOException {
		model = adminService.getData(model);
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());
		if(request.getUserPrincipal() != null){
			model.addAttribute("user", request.getUserPrincipal().getName());
			model.addAttribute("login", (request.getUserPrincipal() != null));
		}else{
			model.addAttribute("login", false);
		}
		model.addAttribute("userList", userService.getAllUsersPage());
		model.addAttribute("admin", request.isUserInRole("ADMIN"));
		model.addAttribute("userr", request.isUserInRole("USER"));

		return "graphics";
	}
	
	
}
