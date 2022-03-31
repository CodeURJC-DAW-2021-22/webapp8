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

	@Operation(summary = "Get a user by its id")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "200", 
					description = "Found the User", 
					content = {@Content(
							mediaType = "application/json"
							)}
					),
			@ApiResponse(
					responseCode = "404", 
					description = "User not found", 
					content = @Content
					) 
	})
	@JsonView(Users.Detailed.class)
	@GetMapping("/{id}")
	public ResponseEntity<Users> getUsersById(@Parameter(description="id of user to be searched") @PathVariable int id){
		Optional<Users> user = userService.getUserId(id);
		if(!user.isEmpty()){
			return ResponseEntity.ok(user.get());
		}else {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "Create a user")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "201", 
					description = "Successful user creation", 
					content = {@Content(
							mediaType = "application/json"
							)}
					),
			@ApiResponse(
					responseCode = "406 ", 
					description = "Not Acceptable user creation the username or email is token", 
					content = {@Content(
							mediaType = "application/json"
							)}
					)
	})
	@JsonView(Users.Detailed.class)
	@PostMapping("/")
	public ResponseEntity<Users> registerUser(@Parameter(description="Object Json Type Users") @RequestBody Users user) throws IOException{
		if(user.getEmail() != null) {
			if(userService.existEmail(user.getEmail())) {
				return new ResponseEntity<>(user,HttpStatus.NOT_ACCEPTABLE);
			}
		}
		if(!userService.existsUser(user.getUsername())) {
			Resource imagedefault = new ClassPathResource("/static/images/avatar.png");
			user.setUserimg(BlobProxy.generateProxy(imagedefault.getInputStream(), imagedefault.contentLength()));
			userService.saveUser(user);
			user = userService.getUser(user.getUsername());
			URI location = fromCurrentRequest().path("/{id}").buildAndExpand(user.getIdusers()).toUri();
			return ResponseEntity.created(location).body(user);
		}else {
			return new ResponseEntity<>(user,HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@Operation(summary = "Modify a user")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "201", 
					description = "Successful user modification", 
					content = {@Content(
							mediaType = "application/json"
							)}
					),
			@ApiResponse(
					responseCode = "404", 
					description = "User not found", 
					content = @Content
					),
			@ApiResponse(
					responseCode = "406 ", 
					description = "Not Acceptable user creation the username or email is token", 
					content = {@Content(
							mediaType = "application/json"
							)}
					)
	})
	@JsonView(Users.Detailed.class)
	@PutMapping("/{id}")
	public ResponseEntity<Users> replaceUser(@Parameter(description="id of user to be searched") @PathVariable int id, @Parameter(description="Object Json Type Users") @RequestBody Users user) throws IOException{
		Optional<Users> use = userService.getUserId(id);
		if(!use.isEmpty()) {
			if(user.getEmail() != null) {
				if(userService.existEmail(user.getEmail())) {
					return new ResponseEntity<>(user,HttpStatus.NOT_ACCEPTABLE);
				}
			}
			if(user.getUsername() == null) {
				user.setUsername(use.get().getUsername());
				user.setIdusers(id);
				userService.modifyDataUser(user, use.get().getUsername(),null,null);
				if(user.getPass() != null) {
					userService.modifyPass(user.getUsername(), user.getPass());
				}
				user = userService.getUserId(id).get();
				return ResponseEntity.ok(user);
			}else {
				if(!userService.existsUser(user.getUsername())) {
					user.setIdusers(id);
					userService.modifyDataUser(user, use.get().getUsername(),null,null);
					if(user.getPass() != null) {
						userService.modifyPass(user.getUsername(), user.getPass());
					}
					user = userService.getUserId(id).get();
					return ResponseEntity.ok(user);
				}else {
					return new ResponseEntity<>(user,HttpStatus.NOT_ACCEPTABLE);
				}
			}
		}else {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "Delete a user")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "200", 
					description = "Successful user delete", 
					content = {@Content(
							mediaType = "application/json"
							)}
					),
			@ApiResponse(
					responseCode = "404", 
					description = "User not found", 
					content = @Content
					) 
	})

}
