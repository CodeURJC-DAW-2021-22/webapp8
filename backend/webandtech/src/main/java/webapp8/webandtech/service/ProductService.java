package webapp8.webandtech.service;

import java.io.IOException;
import java.util.NoSuchElementException;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import webapp8.webandtech.repository.ProductRepository;
import webapp8.webandtech.model.Product;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

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

    public Page<Product> getProductsPage(org.springframework.data.domain.Pageable page) {
		return productRepository.findAll(page);
	}

    public Product getProduct(int idproduct) {
		return productRepository.findById(idproduct).orElseThrow(() -> new NoSuchElementException("Product not found"));
	}
}
