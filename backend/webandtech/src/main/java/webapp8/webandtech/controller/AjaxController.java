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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import webapp8.webandtech.model.Product;
import webapp8.webandtech.service.ProductService;

@RestController
@CrossOrigin
public class AjaxController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products/moreProducts")
	private Page<Product> getMoreProducts(Pageable page){
		return productService.getProductsPage(page);
	}
    @GetMapping("/products/getMoreProductsPage")
	private Page<Product> getMoreProductsPage(Pageable page, @RequestParam(required = false) String productType, @RequestParam(required = false) String productcategory){
		Page<Product> products;
		if(!(productType.equals("undefined"))){
			products = productService.getMoreProductType(page,productType);
		} else{
			if(productcategory.equals("Componente")){
				products = productService.getMoreComponents(page);
			} else if(productcategory.equals("Periferico")){
				products = productService.getMorePeripherals(page);
			} else{
				products = productService.getMorePhones(page);
			}
		}
		return products;
	}
    
}
