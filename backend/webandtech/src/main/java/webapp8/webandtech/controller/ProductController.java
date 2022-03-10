package webapp8.webandtech.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import webapp8.webandtech.model.Product;
import webapp8.webandtech.model.ProductType;
import webapp8.webandtech.service.ProductService;

@RestController
@CrossOrigin
public class ProductController {
		
	@Autowired
	 private ProductService productService;
	
	@PostMapping("/productUpload")
	private void productUpload(HttpServletResponse response, Product product, @RequestParam(required = false) MultipartFile image1, @RequestParam(required = false) MultipartFile image2, @RequestParam(required = false) MultipartFile image3) throws IOException {
		productService.saveProduct (product, image1, image2, image3);
		response.sendRedirect("/index");
	}

	@GetMapping("/componentPage")
	private Page<Product> getComponentsPage(Pageable page){
		return productService.getComponentsPage(page);		
	}

	@GetMapping("/peipheralPage")
	private Page<Product> getPeripheralsPage(Pageable page){
		return productService.getPeripheralsPage(page);		
	}

	@GetMapping("/phonePage")
	private Page<Product> getPhonesPage(Pageable page){
		return productService.getPhonesPage(page);		
	}

	@GetMapping("/products/{productType}")
	private List<Product> getProuctTypePage(ProductType productType){
		return productService.getProductType(productType);		
	}

	@GetMapping("/moreProducts")
	private Page<Product> getMoreProducts(Pageable page){
		return productService.getProductsPage(page);
	}
	
	@GetMapping("/products")
	private Page<Product> getProducts(Pageable page){
		return productService.getProductsPage(page);
	}
	
	@GetMapping("/productImg1/{idproduct}")
    private ResponseEntity<Object> getProductImg1(@PathVariable int idproduct) throws SQLException{
		Product product = productService.getProduct(idproduct);
    	Resource file = new InputStreamResource(product.getImage1().getBinaryStream());
    	return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
				.contentLength(product.getImage1().length())
				.body(file);
    }
	
	@GetMapping("/productImg2/{idproduct}")
    private ResponseEntity<Object> getProductImg2( @PathVariable int idproduct) throws SQLException{
		Product product = productService.getProduct(idproduct);
		Resource file = new InputStreamResource(product.getImage2().getBinaryStream());
    	return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
				.contentLength(product.getImage2().length())
				.body(file);
    }
	
	@GetMapping("/productImg3/{idproduct}")
    private ResponseEntity<Object> getProductImg3( @PathVariable int idproduct) throws SQLException{
		Product product = productService.getProduct(idproduct);
		Resource file = new InputStreamResource(product.getImage3().getBinaryStream());
    	return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
				.contentLength(product.getImage3().length())
				.body(file);
    }
}
