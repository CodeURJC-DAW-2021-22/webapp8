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
import webapp8.webandtech.service.ProductService;

@RestController
@CrossOrigin
@RequestMapping("api/products")
public class ProductsRestController {


    @Autowired
	private ProductService productService;	

	@Operation(summary = "Get a all Products")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "200", 
					description = "Found the Products", 
					content = {@Content(
							mediaType = "application/json"
							)}
					) 
	})
	@JsonView(Product.ProductDetails.class)
	@GetMapping("/")
	public List<Product> getAllProducts( @Parameter(description="page") @RequestParam(required = false) String page ){
		if(page != null) {
			return productService.getProductsPage(PageRequest.of(Integer.parseInt(page), 5)).getContent();
		}else {
			return productService.getAll();
		}
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
	@JsonView(Product.ProductDetails.class)
	@PostMapping("/")
	public ResponseEntity<Product> registerProduct( @Parameter(description="Object Type Product") @RequestBody Product product) throws IOException{
		if (productService.existsProduct(product.getTitle())) {
			return new ResponseEntity<Product>(product,HttpStatus.NOT_ACCEPTABLE);
		}
		product.setImg0(false);
		product.setImg1(false);
		product.setImg2(false);
		productService.save(product); 
		product = productService.getProductByTitle(product.getTitle());
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
	@JsonView(Product.ProductDetails.class)
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProduct ( @Parameter(description="id of Product to be searched") @PathVariable int id) throws IOException{
		Optional<Product> product = productService.getProductById(id);
		if(!product.isEmpty()) {
			return ResponseEntity.ok(product.get());
		}else {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "Create a Product")
	@ApiResponses(value = { 
			@ApiResponse(
					responseCode = "201", 
					description = "Successful Product creation", 
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
	@JsonView(Product.ProductDetails.class)
	@DeleteMapping("/{id}")
	public ResponseEntity<Product> deleteProduct( @Parameter(description="id of Product to be searched") @PathVariable int id){
		Optional<Product> product = productService.getProductById(id);
		if(product.isPresent()){
			productService.deleteProductbyid(product.get().getIdproduct());
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
	@JsonView(Product.ProductDetails.class)
	@PutMapping("/{id}")
	public ResponseEntity<Product> replaceProduct( @Parameter(description="id of Product to be searched") @PathVariable int id, @Parameter(description="Object Type Product") @RequestBody Product newproduct) throws IOException{
		if (productService.existsProduct(newproduct.getTitle())) {
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

	@Operation(summary = "Get a Image 0 Product by its id")
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
	@GetMapping("/{id}/image0")
	public ResponseEntity<Object> getImage0( @Parameter(description="id of Product to be searched") @PathVariable int id) throws SQLException{
		Optional<Product> product = productService.getProductById(id);
		if(product.isPresent()) {
			if(product.get().getImage0() != null) {
				Resource file = new InputStreamResource(product.get().getImage0().getBinaryStream());
				return ResponseEntity.ok()
						.header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
						.contentLength(product.get().getImage0().length())
						.body(file);
			}else {
				return ResponseEntity.noContent().build();
			}
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

	

}
