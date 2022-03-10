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

// import webapp8.webandtech.service.AdminService;
import webapp8.webandtech.service.LoaderService;
import webapp8.webandtech.service.ProductService;
// import webapp8.webandtech.service.UserService;

@Controller
@CrossOrigin
public class NavigationController {

    // @Autowired
    // private UserService userService;
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

		return "index";
	}
    @GetMapping("/componentes")
	private String getComponentes(Model model,HttpServletRequest request) throws IOException {
		CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
		model.addAttribute("token", token.getToken());
		if(request.getUserPrincipal() != null){
			model.addAttribute("user", request.getUserPrincipal().getName());
			model.addAttribute("login", (request.getUserPrincipal() != null));
		}else{
			model.addAttribute("login", false);
		}
		model.addAttribute("products", productService.getComponents());
		model.addAttribute("admin", request.isUserInRole("ADMIN"));

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
		
		return "product";
	}


}
