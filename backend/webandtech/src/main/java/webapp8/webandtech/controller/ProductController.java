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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import webapp8.webandtech.model.Product;
import webapp8.webandtech.service.ProductService;

@Controller
@CrossOrigin
public class ProductController {
		
	@Autowired
	 private ProductService productService;
	
	@PostMapping("/admin/productUpload")
	private void productUpload(Product product, HttpServletResponse response, @RequestParam(required = false) MultipartFile image11, @RequestParam(required = false) MultipartFile image22, @RequestParam(required = false) MultipartFile image33) throws IOException {
		System.out.println(product);
		productService.saveProduct (product, image11, image22, image33);
		response.sendRedirect("/index");
	}

	@PostMapping("/admin/productModify")
	private void productModify(Product product, HttpServletResponse response, @RequestParam(required = false) MultipartFile image11, @RequestParam(required = false) MultipartFile image22, @RequestParam(required = false) MultipartFile image33) throws IOException {
		System.out.println(product);
		productService.modifyDataProduct(product, image11, image22, image33);
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

	@GetMapping("/types/{productType}")
	private List<Product> getProuctTypePage(String productType){
		return productService.getProductType(productType);		
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

	
	@PostMapping("/admin/deleteProduct")
	public void deleteProduct (HttpServletResponse response, @RequestParam int idproduct) throws IOException {
		productService.deleteProductById(idproduct);
		response.sendRedirect("/index");
	}

    
}
