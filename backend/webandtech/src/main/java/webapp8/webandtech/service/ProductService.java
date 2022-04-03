package webapp8.webandtech.service;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import webapp8.webandtech.model.Product;
import webapp8.webandtech.repository.ProductRepository;
import webapp8.webandtech.repository.RatingRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

	@Autowired
	private RatingRepository ratingRepository;



	//api methods
	public void deleteProductApiById(int id) {
		Product pref = productRepository.findByIdproduct(id);
		productRepository.deleteById(pref.getIdproduct());
	}

	public Optional<Product> getProductById(int id) {
		return productRepository.findById(id);
	}

	//normal methods
    public void save(Product product){
        productRepository.save(product);
    }

    public void saveProduct(Product product, MultipartFile image1, MultipartFile image2, MultipartFile image3) throws IOException {
		if (image1.isEmpty() == false) {
			product.setImage1(BlobProxy.generateProxy(image1.getInputStream(), image1.getSize()));
			product.setImg1(true);
		} else {
			product.setImg1(false);
		}
		if (image2.isEmpty() == false) {
			product.setImage1(BlobProxy.generateProxy(image2.getInputStream(), image2.getSize()));
			product.setImg2(true);
		} else {
			product.setImg2(false);
		}
		if (image3.isEmpty() == false) {
			product.setImage3(BlobProxy.generateProxy(image3.getInputStream(), image3.getSize()));
			product.setImg3(true);
		} else {
			product.setImg3(false);
		}
		productRepository.save(product);
	}

    public Page<Product> getProductsPage(Pageable page) {
		return productRepository.findAll(page);
	}

    public Product getProduct(int idproduct) {
		return productRepository.findById(idproduct).orElseThrow(() -> new NoSuchElementException("Product not found"));
	}
    
	public Product getProductByName(String nameproduct) {
		return productRepository.findBynameproduct(nameproduct);
	}

	public Page<Product> getComponentsPage(Pageable page){
		
		return productRepository.findByproductcategory("Componente", page);
	}

	public Page<Product> getPeripheralsPage(Pageable page){
		return productRepository.findByproductcategory("peripheral", page);
	}

	public Page<Product> getPhonesPage(Pageable page){
		return productRepository.findByproductcategory("phone", page);
	}
	
	public List<Product> getProductType(String productType){
		Page<Product> components = productRepository.findByproductType(productType,PageRequest.of(0, 10, Sort.by("idproduct").descending()));
		return components.getContent();
	}
	public Page<Product> getMoreProductType(Pageable page, String productType){
		Page<Product> components = productRepository.findByproductType(productType,page);
		return components;
	}
	public List<Product> getTop6ByOrderByIdDesc(){
		return productRepository.findTopByOrderByIdproductDesc();
	}
	public List<Product> getNewProucts(){
		Page<Product> products = productRepository.findAll(PageRequest.of(0, 6, Sort.by("idproduct").descending()));
		return products.getContent();
	}
	public List<Product> getComponents(){
		Page<Product> components = productRepository.findByproductcategory("Componente",PageRequest.of(0, 8, Sort.by("idproduct").descending()));
		return components.getContent();
	}
	public Page<Product> getMoreComponents(Pageable page){
		Page<Product> components = productRepository.findByproductcategory("Componente",page);
		return components;
	}
	public List<Product> getPeripherals(){
		Page<Product> peripherals = productRepository.findByproductcategory("Periferico",PageRequest.of(0, 8, Sort.by("idproduct").descending()));
		return peripherals.getContent();
	}
	public Page<Product> getMorePeripherals(Pageable page){
		Page<Product> peripherals = productRepository.findByproductcategory("Periferico",page);
		return peripherals;
	}
	public List<Product> getPhones(){
		Page<Product> phones = productRepository.findByproductcategory("telefono",PageRequest.of(0, 8, Sort.by("idproduct").descending()));
		return phones.getContent();
	}
	public Page<Product> getMorePhones(Pageable page){
		Page<Product> phones = productRepository.findByproductcategory("telefono",page);
		return phones;
	}
	public boolean existsProduct(String nameProduct) {         
		return productRepository.existsIdproductBynameproduct(nameProduct);     
	}

	@Modifying
	public void modifyDataProduct(Product product, MultipartFile image1, MultipartFile image2, MultipartFile image3) throws IOException {
		Product prev = productRepository.findById(product.getIdproduct()).orElseThrow(() -> new NoSuchElementException("Product not found"));
		
		if(image1 != null){
			if(!image1.isEmpty()) {
				prev.setImage1(BlobProxy.generateProxy(image1.getInputStream(), image1.getSize()));
				prev.setImg1(true);
			} else {
				prev.setImg1(false);
			}
		}
		if(image2 != null){
			if(!image2.isEmpty()) {
				prev.setImage2(BlobProxy.generateProxy(image2.getInputStream(), image2.getSize()));
				prev.setImg2(true);
			} else {
				prev.setImg2(false);
			}
		}
		if(image3 != null){
			if(!image3.isEmpty()) {
				prev.setImage3(BlobProxy.generateProxy(image3.getInputStream(), image3.getSize()));
				prev.setImg3(true);
			} else {
				prev.setImg3(false);
			}
		}
		
		
		if(product.getNameproduct() != null) {
			if(!product.getNameproduct().isEmpty()) {
				prev.setNameproduct(product.getNameproduct());
			}
		}
		if(product.getProductcategory() != null) {
			if(!product.getProductcategory().isEmpty()) {
				prev.setProductcategory(product.getProductcategory());
			}
		}
		if(product.getProductType() != null) {
			if(!product.getProductType().isEmpty()) {
				prev.setProductType(product.getProductType());
			}
		}
		
		prev.setPrice(product.getPrice());
		
		if(product.getDescription() != null) {
			if(!product.getDescription().isEmpty()) {
				prev.setDescription(product.getDescription());
			}
		}
		if(product.getProductbrand() != null) {
			if(!product.getProductbrand().isEmpty()) {
				prev.setProductbrand(product.getProductbrand());
			}
		}
		
		
		productRepository.save(prev);
	}

	@Transactional
	public void deleteProductById(int idproduct) {
		Product prev = productRepository.findById(idproduct).orElseThrow(() -> new NoSuchElementException("Product not found"));
    		ratingRepository.deleteByIdproduct(prev);
    		productRepository.deleteById(prev.getIdproduct());
	}

	
}
