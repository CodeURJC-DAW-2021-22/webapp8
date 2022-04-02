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

}