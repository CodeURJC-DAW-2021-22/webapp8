package webapp8.webandtech.controller.api.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import webapp8.webandtech.service.ProductService;
import webapp8.webandtech.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("api/users")
public class UserRestControler {
    
	@Autowired
	private UserService userService;

	@Autowired
	private ProductService productsService;

	@Operation(summary = "Get a all users")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "200", 
					description = "Found the Users", 
					content = {@Content(
							mediaType = "application/json"
							)}
					) 
	})
	@JsonView(Users.Detailed.class)
	@GetMapping("/")
	public List<Users> getAllUsers(){
		return userService.getAll();
	}


}

