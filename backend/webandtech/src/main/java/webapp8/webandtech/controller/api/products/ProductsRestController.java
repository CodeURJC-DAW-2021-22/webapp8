package webapp8.webandtech.controller.api.products;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import webapp8.webandtech.model.Product;
import webapp8.webandtech.model.Rating;
import webapp8.webandtech.service.ProductService;
import webapp8.webandtech.service.RatingService;

@RestController
@CrossOrigin
@RequestMapping("api/products")
public class ProductsRestController {


    @Autowired
	private ProductService productService;	
	
    @Autowired
	private RatingService ratingService;

	@Operation(summary = "Get New Six Products")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "200", 
					description = "Found the Products", 
					content = {@Content(
							mediaType = "application/json"
							)}
					) 
	})
	@JsonView(Product.Detailed.class)
	@GetMapping("/")
	public List<Product> getAllProducts(){
		
		return productService.getNewProucts();
		
	}


	@Operation(summary = "Create a Products")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "201", 
					description = "Successful Products creation", 
					content = {@Content(
							mediaType = "application/json"
							)}
					),
			@ApiResponse(
					responseCode = "406", 
					description = "Not Acceptable Post title exists", 
					content = @Content
					) 
	})
	@JsonView(Product.Detailed.class)
	@PostMapping("/")
	public ResponseEntity<Product> registerProduct( @Parameter(description="Object Type Product") @RequestBody Product product) throws IOException{
		if (productService.existsProduct(product.getNameproduct())) {
			return new ResponseEntity<Product>(product,HttpStatus.NOT_ACCEPTABLE);
		}
		product.setImg1(false);
		product.setImg2(false);
		product.setImg3(false);
		productService.save(product); 
		product = productService.getProductByName(product.getNameproduct());
		URI location = fromCurrentRequest().path("/{id}").buildAndExpand(product.getIdproduct()).toUri();
		return ResponseEntity.created(location).body(product);
	}

    @Operation(summary = "Get a products by its id")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "200", 
					description = "Found the Product", 
					content = {@Content(
							mediaType = "application/json"
							)}
					),
			@ApiResponse(
					responseCode = "404", 
					description = "Product not found", 
					content = @Content
					) 
	})
	@JsonView(Product.Detailed.class)
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProduct ( @Parameter(description="id of Product to be searched") @PathVariable int id) throws IOException{
		Optional<Product> product = productService.getProductById(id);
		if(!product.isEmpty()) {
			return ResponseEntity.ok(product.get());
		}else {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "Delete a Product")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "201", 
					description = "Successful Product delete", 
					content = {@Content(
							mediaType = "application/json"
							)}
					),
			@ApiResponse(
					responseCode = "404", 
					description = "Product not found", 
					content = @Content
					) 
	})
	@JsonView(Product.Detailed.class)
	@DeleteMapping("/{id}")
	public ResponseEntity<Product> deleteProduct( @Parameter(description="id of Product to be searched") @PathVariable int id){
		Optional<Product> product = productService.getProductById(id);
		if(product.isPresent()){
			productService.deleteProductApiById(product.get().getIdproduct());
			return ResponseEntity.ok(product.get());
		}else {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "Modify a Product")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "201", 
					description = "Successful Product modification", 
					content = {@Content(
							mediaType = "application/json"
							)}
					),
			@ApiResponse(
					responseCode = "404", 
					description = "Product not found", 
					content = @Content
					),
			@ApiResponse(
					responseCode = "406", 
					description = "Not Acceptable Post title exists", 
					content = @Content
					) 
	})

	@JsonView(Product.Detailed.class)
	@PutMapping("/{id}")
	public ResponseEntity<Product> replaceProduct( @Parameter(description="id of Product to be searched") @PathVariable int id, @Parameter(description="Object Type Product") @RequestBody Product newproduct) throws IOException{
		if (productService.existsProduct(newproduct.getNameproduct())) {
			return new ResponseEntity<Product>(newproduct,HttpStatus.NOT_ACCEPTABLE);
		}
		Optional<Product> product = productService.getProductById(id);
		if(!product.isEmpty()) {
			newproduct.setIdproduct(id);
			productService.saveProduct(newproduct);
			newproduct = productService.getProductById(id).get();
			return ResponseEntity.ok(newproduct);
		}else {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "Get a Image 1 Product by its id")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "200", 
					description = "Found the Image Product", 
					content = {@Content(
							mediaType = "application/json"
							)}
					),
			@ApiResponse(
					responseCode = "404", 
					description = "Product not found", 
					content = @Content
					),
			@ApiResponse(
					responseCode = "204", 
					description = "Image not found", 
					content = @Content
					)
	})
	@GetMapping("/{id}/image1")
	public ResponseEntity<Object> getImage1( @Parameter(description="id of Product to be searched") @PathVariable int id) throws SQLException{
		Optional<Product> product = productService.getProductById(id);
		if(product.isPresent()) {
			if(product.get().getImage1() != null) {
				Resource file = new InputStreamResource(product.get().getImage1().getBinaryStream());
				return ResponseEntity.ok()
						.header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
						.contentLength(product.get().getImage1().length())
						.body(file);
			}else {
				return ResponseEntity.noContent().build();
			}
		}else {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "Get a Image 2 Product by its id")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "200", 
					description = "Found the Image Product", 
					content = {@Content(
							mediaType = "application/json"
							)}
					),
			@ApiResponse(
					responseCode = "404", 
					description = "Product not found", 
					content = @Content
					),
			@ApiResponse(
					responseCode = "204", 
					description = "Image not found", 
					content = @Content
					)
	})
	@GetMapping("/{id}/image2")
	public ResponseEntity<Object> getImage2( @Parameter(description="id of Product to be searched") @PathVariable int id) throws SQLException{
		Optional<Product> product = productService.getProductById(id);
		if(product.isPresent()) {
			if(product.get().getImage2() != null) {
				Resource file = new InputStreamResource(product.get().getImage2().getBinaryStream());
				return ResponseEntity.ok()
						.header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
						.contentLength(product.get().getImage2().length())
						.body(file);
			}else {
				return ResponseEntity.noContent().build();
			}
		}else {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "Get a Image 3 Product by its id")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "200", 
					description = "Found the Image Product", 
					content = {@Content(
							mediaType = "application/json"
							)}
					),
			@ApiResponse(
					responseCode = "404", 
					description = "Product not found", 
					content = @Content
					),
			@ApiResponse(
					responseCode = "204", 
					description = "Image not found", 
					content = @Content
					)
	})
	@GetMapping("/{id}/image3")
	public ResponseEntity<Object> getImage3( @Parameter(description="id of Product to be searched") @PathVariable int id) throws SQLException{
		Optional<Product> product = productService.getProductById(id);
		if(product.isPresent()) {
			if(product.get().getImage3() != null) {
				Resource file = new InputStreamResource(product.get().getImage3().getBinaryStream());
				return ResponseEntity.ok()
						.header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
						.contentLength(product.get().getImage3().length())
						.body(file);
			}else {
				return ResponseEntity.noContent().build();
			}
		}else {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "Create a Image 1 Product by its id")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "201", 
					description = "Create the Image Product", 
					content = {@Content(
							mediaType = "application/json"
							)}
					),
			@ApiResponse(
					responseCode = "404", 
					description = "Product not found", 
					content = @Content
					),
			@ApiResponse(
					responseCode = "204", 
					description = "Image not found", 
					content = @Content
					)
	})
	@PostMapping("/{id}/image1")
	public ResponseEntity<Object> uploadImage1( @Parameter(description="id of Product to be searched") @PathVariable int id, @Parameter(description="Image 1 Product") @RequestParam() MultipartFile image) throws SQLException, IOException{
		Optional<Product> product = productService.getProductById(id);
		if(product.isPresent()) {
			if(image != null) {
				product.get().setImage1(BlobProxy.generateProxy(image.getInputStream(), image.getSize()));
				product.get().setImg1(true);
				productService.saveProduct(product.get());
				URI location = fromCurrentRequest().build().toUri();
				return ResponseEntity.created(location).build();
			}else {
				return ResponseEntity.noContent().build();
			}
		}else {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "Create a Image 2 Product by its id")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "201", 
					description = "Create the Image Product", 
					content = {@Content(
							mediaType = "application/json"
							)}
					),
			@ApiResponse(
					responseCode = "404", 
					description = "Product not found", 
					content = @Content
					),
			@ApiResponse(
					responseCode = "204", 
					description = "Image not found", 
					content = @Content
					)
	})
	@PostMapping("/{id}/image2")
	public ResponseEntity<Object> uploadImage2( @Parameter(description="id of Product to be searched") @PathVariable int id, @Parameter(description="Image 2 Product") @RequestParam() MultipartFile image) throws SQLException, IOException{
		Optional<Product> product = productService.getProductById(id);
		if(product.isPresent()) {
			if(image != null) {
				product.get().setImage2(BlobProxy.generateProxy(image.getInputStream(), image.getSize()));
				product.get().setImg2(true);
				productService.saveProduct(product.get());
				URI location = fromCurrentRequest().build().toUri();
				return ResponseEntity.created(location).build();
			}else {
				return ResponseEntity.noContent().build();
			}
		}else {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "Create a Image 3 Product by its id")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "201", 
					description = "Create the Image Product", 
					content = {@Content(
							mediaType = "application/json"
							)}
					),
			@ApiResponse(
					responseCode = "404", 
					description = "Product not found", 
					content = @Content
					),
			@ApiResponse(
					responseCode = "204", 
					description = "Image not found", 
					content = @Content
					)
	})
	@PostMapping("/{id}/image3")
	public ResponseEntity<Object> uploadImage3( @Parameter(description="id of Product to be searched") @PathVariable int id, @Parameter(description="Image 3 Product") @RequestParam() MultipartFile image) throws SQLException, IOException{
		Optional<Product> product = productService.getProductById(id);
		if(product.isPresent()) {
			if(image != null) {
				product.get().setImage3(BlobProxy.generateProxy(image.getInputStream(), image.getSize()));
				product.get().setImg3(true);
				productService.saveProduct(product.get());
				URI location = fromCurrentRequest().build().toUri();
				return ResponseEntity.created(location).build();
			}else {
				return ResponseEntity.noContent().build();
			}
		}else {
			return ResponseEntity.notFound().build();
		}
	}	

	@Operation(summary = "Get a peripheral")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "200", 
					description = "Found the Product", 
					content = {@Content(
							mediaType = "application/json"
							)}
					),
			@ApiResponse(
					responseCode = "404", 
					description = "Product not found", 
					content = @Content
					) 
	})
	@JsonView(Product.Detailed.class)
	@GetMapping("/peripherals")
	public List<Product> getPeripheralsPage ( @Parameter(description="page") @RequestParam(required = false) String page, @Parameter(description="typeProduct") @RequestParam(required = false) String typeProduct) throws IOException{
		
		if(typeProduct != null) {
			return productService.getMoreProductType(PageRequest.of(Integer.parseInt(page), 10,Sort.by("idproduct").ascending()), typeProduct).getContent();
		}else {
			return productService.getPeripheralsPage(PageRequest.of(Integer.parseInt(page), 10,Sort.by("idproduct").ascending())).getContent();
		}
	}
	@Operation(summary = "Get a component")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "200", 
					description = "Found the Product", 
					content = {@Content(
							mediaType = "application/json"
							)}
					),
			@ApiResponse(
					responseCode = "404", 
					description = "Product not found", 
					content = @Content
					) 
	})
	@JsonView(Product.Detailed.class)
	@GetMapping("/components")
	public List<Product> getComponentsPage ( @Parameter(description="page") @RequestParam(required = false) String page, @Parameter(description="typeProduct") @RequestParam(required = false) String typeProduct) throws IOException{
		
		if(typeProduct != null) {
			return productService.getMoreProductType(PageRequest.of(Integer.parseInt(page), 10,Sort.by("idproduct").ascending()), typeProduct).getContent();
		}else {
			return productService.getComponentsPage(PageRequest.of(Integer.parseInt(page), 10,Sort.by("idproduct").ascending())).getContent();
		}
	}

	@Operation(summary = "Get a phone")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "200", 
					description = "Found the Product", 
					content = {@Content(
							mediaType = "application/json"
							)}
					),
			@ApiResponse(
					responseCode = "404", 
					description = "Product not found", 
					content = @Content
					) 
	})
	@JsonView(Product.Detailed.class)
	@GetMapping("/phones")
	public List<Product> getPhonesPage ( @Parameter(description="page") @RequestParam(required = false) String page, @Parameter(description="typeProduct") @RequestParam(required = false) String typeProduct) throws IOException{
		
		if(typeProduct != null) {
			return productService.getMoreProductType(PageRequest.of(Integer.parseInt(page), 10,Sort.by("idproduct").ascending()), typeProduct).getContent();
		}else {
			return productService.getPhonesPage(PageRequest.of(Integer.parseInt(page), 10,Sort.by("idproduct").ascending())).getContent();
		}
	}
	
	@Operation(summary = "Ratings")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "200", 
					description = "Found the Image Product", 
					content = {@Content(
							mediaType = "application/json"
							)}
					),
			@ApiResponse(
					responseCode = "404", 
					description = "Product not found", 
					content = @Content
					),
			@ApiResponse(
					responseCode = "204", 
					description = "Image not found", 
					content = @Content
					)
	})
	@GetMapping("/{id}/ratings")
	public List<Rating> getProductRatings( @Parameter(description="id of Product to be searched") @PathVariable int id, @Parameter(description="page") @RequestParam(required = false) String page) throws SQLException{
		Optional<Product> product = productService.getProductById(id);
		if(!product.isEmpty()) { 
			List<Rating> productList = ratingService.getMoreRating(PageRequest.of(Integer.parseInt(page), 10,Sort.by("idproduct").ascending()), product.get()).getContent();
			return productList;
		}else {
				return null;
			}
		
	}



}
