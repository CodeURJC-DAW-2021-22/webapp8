package webapp8.webandtech.controller;

import java.io.IOException;

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

// import webapp8.webandtech.service.AdminService;
import webapp8.webandtech.service.LoaderService;
import webapp8.webandtech.service.ProductService;
import webapp8.webandtech.service.UserService;

@Controller
@CrossOrigin
public class NavigationController {

    @Autowired
    private UserService userService;
	// @Autowired
    // private AdminService adminService;
	@Autowired
    private LoaderService loaderService;
	@Autowired
    private ProductService productService;

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
		if(request.getUserPrincipal() != null){
			model.addAttribute("user", request.getUserPrincipal().getName());
			model.addAttribute("login", (request.getUserPrincipal() != null));
		}else{
			model.addAttribute("login", false);
		}
	
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
		if(typeProduct != null){
			model.addAttribute("products", productService.getProductType(typeProduct));
			
			System.out.println(typeProduct);
		} else{
			model.addAttribute("products", productService.getPhones());
		}

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
		System.out.println(productService.getNewProucts());
		System.out.println(idproduct);
		model.addAttribute("product", productService.getProduct(idproduct));
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
		model.addAttribute("products", productService.getNewProucts());
		model.addAttribute("admin", request.isUserInRole("ADMIN"));
		model.addAttribute("userr", request.isUserInRole("USER"));

		return "perfil";
	}
}
