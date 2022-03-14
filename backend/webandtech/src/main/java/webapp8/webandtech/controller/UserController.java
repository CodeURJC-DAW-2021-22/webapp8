package webapp8.webandtech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import webapp8.webandtech.model.User;
import webapp8.webandtech.service.UserService;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@CrossOrigin
public class UserController {

    @Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder encoder;


    @GetMapping("/fetchAllUsers")
    public Page<User> fetchAll() {
        return userService.getUsersPage();
    }
    
    @GetMapping("/getUserPage")
    public Page<User> getUserPage(Pageable page) {
        return userService.getUsersPage(page);
    }
    
    @PostMapping("/registerUser")
	private void registerUser(User user,HttpServletResponse response , HttpServletRequest sesion, @RequestParam(required = false) MultipartFile image) throws IOException, SQLException {
    	System.out.println(user.getEmail());
		userService.registerUsers(user, image);
    	response.sendRedirect("/login");
    }
    @GetMapping("/imageprofile")
    private ResponseEntity<Object> downloadImage(HttpServletRequest request) throws SQLException, IOException{
    	User s = userService.getUser(request.getUserPrincipal().getName());
    	Resource file = new InputStreamResource(s.getUserimg().getBinaryStream());
    	return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
				.contentLength(s.getUserimg().length())
				.body(file);
    }
    
    @GetMapping("/imageprofile/{username}")
    private ResponseEntity<Object> downloadImageProfile(@PathVariable String username) throws SQLException, IOException{
    	User s = userService.getUser(username);
    	Resource file = new InputStreamResource(s.getUserimg().getBinaryStream());
    	return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
				.contentLength(s.getUserimg().length())
				.body(file);
    }
    
    @GetMapping("/moreUsers")
    public Page<User> getMoreUsers(Pageable page){    	
    	return userService.getCustomers(page);
    }

    @PostMapping("/modifyUser")
    public void modifyUserSetting(User user,HttpServletResponse response, HttpServletRequest request, @RequestParam(required = false) MultipartFile image) throws IOException, ServletException {
    	userService.usernameIsToken(user.getUsername());
    	userService.modifyDataUser(user, request.getUserPrincipal().getName(), image);
    	request.logout();
    	response.sendRedirect("/login");
    }
    
    @PostMapping("/users/changePassword")
    public void modifyPassword(HttpServletResponse response, HttpServletRequest request, @RequestParam String oldpassword,  @RequestParam String newpassword,  @RequestParam String repeatpassword) throws IOException, ServletException {
    	User prev = userService.getUser(request.getUserPrincipal().getName());
    	String page = "/error";
    	if(encoder.matches(oldpassword, prev.getPass())) {
    		if(newpassword.equals(repeatpassword)) {
    			userService.modifyPass(request.getUserPrincipal().getName(), newpassword);
				request.logout();
    	    	page = "/login";
    		}
    		
    	}
    	response.sendRedirect(page);
    }
    
    @PostMapping("/deleteUser")
    public void deleteUser(HttpServletResponse response, HttpServletRequest request, @RequestParam String email, @RequestParam String pass, @RequestParam String explication) throws IOException {
    	User prev = userService.getUser(request.getUserPrincipal().getName());
    	if(email.equals(prev.getEmail())) {
    		if(pass.equals(prev.getPass())) {
    			userService.deleteUser(request.getUserPrincipal().getName());
    	    	response.sendRedirect("/index");
    		}
    	}
    	response.sendRedirect("/error");
    }

}
