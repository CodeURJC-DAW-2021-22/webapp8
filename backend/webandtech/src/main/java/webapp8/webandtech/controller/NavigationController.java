package webapp8.webandtech.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import webapp8.webandtech.service.AdminService;
import webapp8.webandtech.service.UserService;

@Controller
@CrossOrigin
public class NavigationController {

    @Autowired
    private UserService userService;
    private AdminService adminService;

    @GetMapping("/")
	private void getInitialPage(HttpServletResponse response) throws IOException {
		response.sendRedirect("/index");		
	}
    @GetMapping("/login")
	private String getSignIn(Model model,HttpServletRequest request) throws IOException {
		//userService.loadDataBase();
		return "login";
	}
    @GetMapping("/index")
	private String getIndex(Model model,HttpServletRequest request) throws IOException {
		//userService.loadDataBase();
		return "index";
	}


}
