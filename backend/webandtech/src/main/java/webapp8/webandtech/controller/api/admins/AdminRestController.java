package webapp8.webandtech.controller.api.admins;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import webapp8.webandtech.model.Order;
import webapp8.webandtech.model.Statistics;
import webapp8.webandtech.model.User;
import webapp8.webandtech.service.AdminService;
import webapp8.webandtech.service.OrderService;
import webapp8.webandtech.service.UserService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@CrossOrigin
@RequestMapping("api/admins")
public class AdminRestController {
    
    @Autowired
	private UserService userService;

    @Autowired
	private OrderService orderService;

    @Autowired
	private AdminService adminService;


	@Operation(summary = "Get a all users type customers")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "200", 
					description = "Found the Users type customers", 
					content = {@Content(
							mediaType = "application/json"
							)}
					) 
	})
	@JsonView(User.Detailed.class)
	@GetMapping("/customers")
	public List<User> getUsers( @Parameter(description="page") @RequestParam(required = false) String page){
		if(page != null) {
			return userService.getCustomers(PageRequest.of(Integer.parseInt(page), 10,Sort.by("username").ascending())).getContent();
		}else {
			return userService.getAll();
		}
	}
	@Operation(summary = "Get a all orders")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "200", 
					description = "Found the orders", 
					content = {@Content(
							mediaType = "application/json"
							)}
					) 
	})
	@JsonView(Order.Detailed.class)
	@GetMapping("/orders")
	public List<Order> getOrders( @Parameter(description="page") @RequestParam(required = false) String page){
		if(page != null) {

			return orderService.getMoreAllOrders(PageRequest.of(Integer.parseInt(page), 10,Sort.by("idorder").ascending())).getContent();
		}else {
			return orderService.getAll();
		}
	}

    @Operation(summary = "Get a all Statistics")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "200", 
					description = "Found the statistics", 
					content = {@Content(
							mediaType = "application/json"
							)}
					) 
	})
	@GetMapping("/statistics")
	public Statistics getStatistics() {
		return adminService.getStatics();
	}

}
