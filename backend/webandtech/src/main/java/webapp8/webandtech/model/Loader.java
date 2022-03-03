package webapp8.webandtech.model;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;

import javax.annotation.PostConstruct;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.security.crypto.password.PasswordEncoder;

import webapp8.webandtech.repository.BrandRepository;
import webapp8.webandtech.repository.OrderRepository;
import webapp8.webandtech.repository.ProductRepository;
import webapp8.webandtech.repository.ProductTypeRepository;
import webapp8.webandtech.repository.RatingRepository;
import webapp8.webandtech.repository.RolRepository;
import webapp8.webandtech.repository.UserRepository;

public class Loader {
    private static final Path ImgFolder = Paths.get(System.getProperty("user.dir"), "src/main/resources/static/images/");

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private ProductTypeRepository productTypeRepo;
    
    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private RatingRepository ratingRepo;

    @Autowired
	private RolRepository rolRepo;

    @Autowired
	private BrandRepository brandRepo;

    @Autowired
	private PasswordEncoder encoder;




    public Loader() {

    }




    public Loader(OrderRepository orderRepo, ProductRepository productRepo, ProductTypeRepository productTypeRepo, 
            UserRepository userRepo, RolRepository rolRepo, RatingRepository ratingRepo, BrandRepository brandRepo, PasswordEncoder encoder) {
        super();
        this.orderRepo = orderRepo;
		this.productRepo = productRepo;
		this.userRepo = userRepo;
		this.ratingRepo = ratingRepo;
		this.encoder = encoder;
    }




    @PostConstruct
    public void Load() throws IOException{
        //User  img
        Path imagePathUser1 = ImgFolder.resolve("");
		Resource imageUser1 = new UrlResource(imagePathUser1.toUri());

        Blob img1 = BlobProxy.generateProxy(imageUser1.getInputStream(), imageUser1.contentLength());

        //User creations
        User User1 = new User(1,"user.1@hotmail.com","username1",encoder.encode("f"), "completname1", img1, true, "Madrid");


        if(userRepo.findAll().isEmpty()) {

            userRepo.save(User1);
        }
    }
}