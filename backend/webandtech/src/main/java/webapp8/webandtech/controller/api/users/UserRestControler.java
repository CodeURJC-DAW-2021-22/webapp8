package webapp8.webandtech.controller.api.users;

import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import webapp8.webandtech.model.Order;
import webapp8.webandtech.model.Product;
import webapp8.webandtech.model.User;
import webapp8.webandtech.service.OrderService;
import webapp8.webandtech.service.UserService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@CrossOrigin
@RequestMapping("api/users")
public class UserRestControler {
    
	@Autowired
	private UserService userService;
	
	@Autowired
	private OrderService orderService;


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
	@JsonView(User.Detailed.class)
	@GetMapping("/{id}")
	public ResponseEntity<User> getUsersById(@Parameter(description="id of user to be searched") @PathVariable int id){
		Optional<User> user = userService.getUserId(id);
		if(!user.isEmpty()){
			return ResponseEntity.ok(user.get());
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	@Operation(summary = "Get ordes by iduser")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "200", 
					description = "Found the Orders", 
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
	@JsonView(Order.Detailed.class)
	@GetMapping("/{id}/orders")
	public List<Order> getOrdersUsers(@Parameter(description="id of user to be searched") @PathVariable int id, @Parameter(description="page") @RequestParam(required = false) String page){
		Optional<User> user = userService.getUserId(id);
		if(!user.isEmpty()){
			User use = user.get();
			List<Order> orders = orderService.getMoreUserOrders(use, PageRequest.of(Integer.parseInt(page), 10,Sort.by("idorder").descending())).getContent();
			System.out.println(orders);
			return orders;
		}else {
			return orderService.getAll();
		}
	}

	@Operation(summary = "Create a order")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "201", 
					description = "Successful order creation", 
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
	@JsonView(User.Detailed.class)
	@PostMapping("/{id}/orders")
	public ResponseEntity<Order> orderUser(@Parameter(description="id of user to be searched") @PathVariable int id, @Parameter(description="Object Json Type Users") @RequestBody List<Product> products) throws IOException{
		Optional<User> user = userService.getUserId(id);
		String idsProducts = "";
			List<Product> carts = products;
			float price = 0;
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			dtf.format(LocalDateTime.now());
			String date = dtf.format(LocalDateTime.now());
			for (Product cart : carts) {
				String idP = Integer.toString(cart.getIdproduct());
				idsProducts = idsProducts+idP+"/";
				price = price + cart.getPrice();
			}
			Order order = new Order();
			order.setPrice(price);
			order.setIduser(user.get());
			order.setIdproducts(idsProducts);
			order.setOrderdate(date);
			orderService.saveOrder(order);
		
		if (!user.isEmpty()){
			List<Order> orderList = orderService.getAll();
			order = orderList.get(0);
			URI location = fromCurrentRequest().path("/{id}").buildAndExpand(orderList.get(0).getIdorder()).toUri();
			return ResponseEntity.created(location).body(orderList.get(0));
		} else {
			return new ResponseEntity<>(order,HttpStatus.NOT_ACCEPTABLE);
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
	@JsonView(User.Detailed.class)
	@PostMapping("/")
	public ResponseEntity<User> registerUser(@Parameter(description="Object Json Type Users") @RequestBody User user) throws IOException{
		if(user.getEmail() != null) {
			if(userService.existEmail(user.getEmail())) {
				return new ResponseEntity<>(user,HttpStatus.NOT_ACCEPTABLE);
			}
		}
		if(!userService.existsUser(user.getUsername())) {
			userService.saveUser(user);
			user = userService.getUser(user.getUsername());
			URI location = fromCurrentRequest().path("/{id}").buildAndExpand(user.getIduser()).toUri();
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
	@JsonView(User.Detailed.class)
	@PutMapping("/{id}")
	public ResponseEntity<User> replaceUser(@Parameter(description="id of user to be searched") @PathVariable int id, @Parameter(description="Object Json Type Users") @RequestBody User user) throws IOException{
		Optional<User> use = userService.getUserId(id);
		if(!use.isEmpty()) {
			if(user.getEmail() != null) {
				if(userService.existEmail(user.getEmail())) {
					return new ResponseEntity<>(user,HttpStatus.NOT_ACCEPTABLE);
				}
			}
			if(user.getUsername() == null) {
				user.setUsername(use.get().getUsername());
				user.setIduser(id);
				userService.modifyDataUser(user, use.get().getUsername(),null);
				if(user.getPass() != null) {
					userService.modifyPass(user.getUsername(), user.getPass());
				}
				user = userService.getUserId(id).get();
				return ResponseEntity.ok(user);
			}else {
				if(!userService.existsUser(user.getUsername())) {
					user.setIduser(id);
					userService.modifyDataUser(user, use.get().getUsername(),null);
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
	@JsonView(User.Detailed.class)
	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@Parameter(description="id of user to be searched") @PathVariable int id){
		Optional<User> s = userService.getUserId(id);
		if(s.isPresent()){
			userService.deleteUserById(id);
			return ResponseEntity.ok(s.get());
		}else {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "Get a profile image user by id")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "200", 
					description = "Found the Image Profile", 
					content = {@Content(
							mediaType = "application/json"
							)}
					),
			@ApiResponse(
					responseCode = "204", 
					description = "Image not found", 
					content = @Content
					),
			@ApiResponse(
					responseCode = "404", 
					description = "User not found", 
					content = @Content
					)
	})
	@GetMapping("/{id}/imageProfile")
	public ResponseEntity<Object> getImageProfile(@Parameter(description="id of user to be searched") @PathVariable int id) throws SQLException{
		Optional<User> s = userService.getUserId(id);
		if(s.isPresent()) {
			if(s.get().getUserimg() != null) {
				Resource file = new InputStreamResource(s.get().getUserimg().getBinaryStream());
				return ResponseEntity.ok()
						.header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
						.contentLength(s.get().getUserimg().length())
						.body(file);
			}else {
				return ResponseEntity.noContent().build();
			}
		}else {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "create a profile image user by id")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "201", 
					description = "Create the ImageProfile", 
					content = {@Content(
							mediaType = "application/json"
							)}
					),
			@ApiResponse(
					responseCode = "204", 
					description = "Image not found", 
					content = @Content
					),
			@ApiResponse(
					responseCode = "404", 
					description = "User not found", 
					content = @Content
					)
	})
	@PostMapping("/{id}/imageProfile")
	public ResponseEntity<Object> uploadImageProfile( @Parameter(description="id of user to be searched") @PathVariable int id, @Parameter(description="user profile picture") @RequestParam MultipartFile image) throws SQLException, IOException{
		Optional<User> user = userService.getUserId(id);
		if(user.isPresent()) {
			if(image != null) {
				user.get().setUserimg(BlobProxy.generateProxy(image.getInputStream(), image.getSize()));
				userService.saveUser(user.get());
				User use = userService.getUser(user.get().getUsername());
				URI location = fromCurrentRequest().path("/{id}").buildAndExpand(use.getIduser()).toUri();
				return ResponseEntity.created(location).body(use);
			}else {
				return ResponseEntity.noContent().build();
			}
		}else {
			return ResponseEntity.notFound().build();
		}
	}

	



}
