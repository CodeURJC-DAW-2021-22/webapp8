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
	@JsonView(Users.Detailed.class)
	@DeleteMapping("/{id}")
	public ResponseEntity<Users> deleteUser(@Parameter(description="id of user to be searched") @PathVariable int id){
		Optional<Users> s = userService.getUserId(id);
		if(s.isPresent()){
			userService.deleteUserById(id);
			return ResponseEntity.ok(s.get());
		}else {
			return ResponseEntity.notFound().build();
		}
	}

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
	@JsonView(Users.Detailed.class)
	@GetMapping("/customers")
	public List<Users> getUsers( @Parameter(description="page") @RequestParam(required = false) String page){
		if(page != null) {
			return userService.getCustomers(PageRequest.of(Integer.parseInt(page), 5)).getContent();
		}else {
			return userService.getAllUsers();
		}
	}

	@Operation(summary = "Get a all users type companies")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "200", 
					description = "Found the Users type companies", 
					content = {@Content(
							mediaType = "application/json"
							)}
					) 
	})
	@JsonView(Users.Detailed.class)
	@GetMapping("/companies")
	public List<Users> getCompanies( @Parameter(description="page") @RequestParam(required = false) String page){
		if(page != null) {
			return userService.getCompanies(PageRequest.of(Integer.parseInt(page), 5)).getContent();
		}else {
			return userService.getAllCompanies();
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
		Optional<Users> s = userService.getUserId(id);
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

	@Operation(summary = "Get a profile theme image user by id")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "200", 
					description = "Found the Image Profile Theme", 
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
	@GetMapping("/{id}/imageThemeProfile")
	public ResponseEntity<Object> getImageThemeProfile( @Parameter(description="id of user to be searched") @PathVariable int id) throws SQLException{
		Optional<Users> s = userService.getUserId(id);
		if(s.isPresent()) {
			if(s.get().getImageprofile() != null) {
				Resource file = new InputStreamResource(s.get().getImageprofile().getBinaryStream());
				return ResponseEntity.ok()
						.header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
						.contentLength(s.get().getImageprofile().length())
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
		Optional<Users> user = userService.getUserId(id);
		if(user.isPresent()) {
			if(image != null) {
				user.get().setUserimg(BlobProxy.generateProxy(image.getInputStream(), image.getSize()));
				userService.saveUser(user.get());
				URI location = fromCurrentRequest().build().toUri();
				return ResponseEntity.created(location).build();
			}else {
				return ResponseEntity.noContent().build();
			}
		}else {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "create a profile image theme user by id")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "201", 
					description = "Create the ImageProfile Theme", 
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
	@PostMapping("/{id}/imageThemeProfile")
	public ResponseEntity<Object> uploadImageThemeProfile( @Parameter(description="id of user to be searched") @PathVariable int id, @Parameter(description="user theme page picture") @RequestParam MultipartFile image) throws SQLException, IOException{
		Optional<Users> user = userService.getUserId(id);
		if(user.isPresent()) {
			if(image != null) {
				user.get().setImageprofile(BlobProxy.generateProxy(image.getInputStream(), image.getSize()));
				userService.saveUser(user.get());
				URI location = fromCurrentRequest().build().toUri();
				return ResponseEntity.created(location).build();
			}else {
				return ResponseEntity.noContent().build();
			}
		}else {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "get all posts by user")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "200", 
					description = "found all posts by user id", 
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
	@JsonView(Post.PostDetails.class)
	@GetMapping("/{id}/posts")
	public ResponseEntity<List<Post>> getAllPost( @Parameter(description="id of user to be searched") @PathVariable int id, @Parameter(description="page") @RequestParam(required = false) String page){
		Optional<Users> user = userService.getUserId(id);
		if(!user.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		if(page != null) {
			return ResponseEntity.ok(postsService.getPostsByUser(id,page,5).getContent());
		}else {
			return ResponseEntity.ok(postsService.getAllPostsByUser(id));
		}
	}

	@Operation(summary = "get all Products by user")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "200", 
					description = "found all products by user id", 
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
	@JsonView(Product.Simple.class)
	@GetMapping("/{id}/products")
	public ResponseEntity<List<Product>> getAllProducts( @Parameter(description="id of user to be searched") @PathVariable int id, @Parameter(description="page") @RequestParam(required = false) String page){
		Optional<Users> user = userService.getUserId(id);
		if(!user.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		if(page != null) {
			return ResponseEntity.ok( productsService.getProductsByUser(id,page,5).getContent() );
		}else {
			return ResponseEntity.ok(productsService.getAllProductsByUser(id));
		}
	}

	@Operation(summary = "Get a followings by id users")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "200", 
					description = "Found the followings", 
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
	@JsonView(UsersRelations.Basic.class)
	@GetMapping("/{id}/followings")
	public ResponseEntity<List<UsersRelations>> getUserRelations( @Parameter(description="id of relation to be searched") @PathVariable int id){
		Optional<Users> user = userService.getUserId(id);
		if(user.isPresent()) {
			return ResponseEntity.ok(userService.getFollowing(user.get().getUsername()));
		}else {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "Get a followers by id users")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "200", 
					description = "Found the followers", 
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
	@JsonView(UsersRelations.Basic.class)
	@GetMapping("/{id}/followers")
	public ResponseEntity<List<UsersRelations>> getRelationsUser( @Parameter(description="id of relation to be searched") @PathVariable int id){
		Optional<Users> user = userService.getUserId(id);
		if(user.isPresent()) {
			return  ResponseEntity.ok(userService.getFollowers(user.get().getUsername()));
		}else {
			return  ResponseEntity.notFound().build();
		}
	}
	
	@Operation(summary = "Get a Bookmarks by id users")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "200", 
					description = "Found the bookmarks", 
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
	@GetMapping("/{id}/bookmarks")
	public ResponseEntity<List<ListProducts>> getBookmarks(@Parameter(description="id of user to be searched") @PathVariable int id){
		Optional<Users> user = userService.getUserId(id);
		if(!user.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(productsService.getBookmarksByUser(id));
	}
	
	@Operation(summary = "Get a Likes by id users")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "200", 
					description = "Found the likes", 
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
	@GetMapping("/{id}/likes")
	public ResponseEntity<List<LikeAPost>> getLike(@Parameter(description="id of user to be searched") @PathVariable int id){
		Optional<Users> user = userService.getUserId(id);
		if(!user.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(postsService.getLikesByUser(id));
	}


}
