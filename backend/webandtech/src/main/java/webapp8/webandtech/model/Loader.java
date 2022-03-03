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
        Path imagePathUser2 = ImgFolder.resolve("");
		Resource imageUser2 = new UrlResource(imagePathUser2.toUri());
        Path imagePathUser3 = ImgFolder.resolve("");
		Resource imageUser3 = new UrlResource(imagePathUser3.toUri());
        Path imagePathUser4 = ImgFolder.resolve("");
		Resource imageUser4 = new UrlResource(imagePathUser4.toUri());
        Path imagePathUser5 = ImgFolder.resolve("");
		Resource imageUser5 = new UrlResource(imagePathUser5.toUri());
        Path imagePathUser6 = ImgFolder.resolve("");
		Resource imageUser6 = new UrlResource(imagePathUser6.toUri());
        Path imagePathUser7 = ImgFolder.resolve("");
		Resource imageUser7 = new UrlResource(imagePathUser7.toUri());
        Path imagePathUser8 = ImgFolder.resolve("");
		Resource imageUser8 = new UrlResource(imagePathUser8.toUri());
        Path imagePathUser9 = ImgFolder.resolve("");
		Resource imageUser9 = new UrlResource(imagePathUser9.toUri());
        Path imagePathUser10 = ImgFolder.resolve("");
		Resource imageUser10 = new UrlResource(imagePathUser10.toUri());
        Path imagePathUser11 = ImgFolder.resolve("");
		Resource imageUser11 = new UrlResource(imagePathUser11.toUri());
        Path imagePathUser12 = ImgFolder.resolve("");
		Resource imageUser12 = new UrlResource(imagePathUser12.toUri());
        Path imagePathUser13 = ImgFolder.resolve("");
		Resource imageUser13 = new UrlResource(imagePathUser13.toUri());
        Path imagePathUser14 = ImgFolder.resolve("");
		Resource imageUser14 = new UrlResource(imagePathUser14.toUri());
        Path imagePathUser15 = ImgFolder.resolve("");
		Resource imageUser15 = new UrlResource(imagePathUser15.toUri());
        Path imagePathUser16 = ImgFolder.resolve("");
		Resource imageUser16 = new UrlResource(imagePathUser16.toUri());
        Path imagePathUser17 = ImgFolder.resolve("");
		Resource imageUser17 = new UrlResource(imagePathUser17.toUri());
        Path imagePathUser18 = ImgFolder.resolve("");
		Resource imageUser18 = new UrlResource(imagePathUser18.toUri());
        Path imagePathUser19 = ImgFolder.resolve("");
		Resource imageUser19 = new UrlResource(imagePathUser19.toUri());
        Path imagePathUser20 = ImgFolder.resolve("");
		Resource imageUser20 = new UrlResource(imagePathUser20.toUri());


        Blob img1 = BlobProxy.generateProxy(imageUser1.getInputStream(), imageUser1.contentLength());
        Blob img2 = BlobProxy.generateProxy(imageUser2.getInputStream(), imageUser2.contentLength());
        Blob img3 = BlobProxy.generateProxy(imageUser3.getInputStream(), imageUser3.contentLength());
        Blob img4 = BlobProxy.generateProxy(imageUser4.getInputStream(), imageUser4.contentLength());
        Blob img5 = BlobProxy.generateProxy(imageUser5.getInputStream(), imageUser5.contentLength());
        Blob img6 = BlobProxy.generateProxy(imageUser6.getInputStream(), imageUser6.contentLength());
        Blob img7 = BlobProxy.generateProxy(imageUser7.getInputStream(), imageUser7.contentLength());
        Blob img8 = BlobProxy.generateProxy(imageUser8.getInputStream(), imageUser8.contentLength());
        Blob img9 = BlobProxy.generateProxy(imageUser9.getInputStream(), imageUser9.contentLength());
        Blob img10 = BlobProxy.generateProxy(imageUser10.getInputStream(), imageUser10.contentLength());
        Blob img11 = BlobProxy.generateProxy(imageUser11.getInputStream(), imageUser11.contentLength());
        Blob img12 = BlobProxy.generateProxy(imageUser12.getInputStream(), imageUser12.contentLength());
        Blob img13 = BlobProxy.generateProxy(imageUser13.getInputStream(), imageUser13.contentLength());
        Blob img14 = BlobProxy.generateProxy(imageUser14.getInputStream(), imageUser14.contentLength());
        Blob img15 = BlobProxy.generateProxy(imageUser15.getInputStream(), imageUser15.contentLength());
        Blob img16 = BlobProxy.generateProxy(imageUser16.getInputStream(), imageUser16.contentLength());
        Blob img17 = BlobProxy.generateProxy(imageUser17.getInputStream(), imageUser17.contentLength());
        Blob img18 = BlobProxy.generateProxy(imageUser18.getInputStream(), imageUser18.contentLength());
        Blob img19 = BlobProxy.generateProxy(imageUser19.getInputStream(), imageUser19.contentLength());
        Blob img20 = BlobProxy.generateProxy(imageUser20.getInputStream(), imageUser20.contentLength());


        //User creations
        User User1 = new User(1,"user.1@hotmail.com","Jonathan",encoder.encode("f"), "Jonathan Joestar", img1, true, "Londres");
        User User2 = new User(2,"user.2@hotmail.com","Joseph",encoder.encode("f"), "Joseph Joestar", img2, true, "Londres");
        User User3 = new User(3,"user.3@hotmail.com","Jotaro",encoder.encode("f"), "Jotaro Kuujou", img3, true, "Egypt");
        User User4 = new User(4,"user.4@hotmail.com","Josuke",encoder.encode("f"), "Josuke Higashikata", img4, true, "Morioh");
        User User5 = new User(5,"user.5@hotmail.com","Giorno",encoder.encode("f"), "Giorno Giovanna", img5, true, "Napoles");
        User User6 = new User(6,"user.6@hotmail.com","Jolyne",encoder.encode("f"), "Jolyne Kujou", img6, true, "Florida");
        User User7 = new User(7,"user.7@hotmail.com","Anthonio",encoder.encode("f"), "Anthonio Zeppeli", img7, true, "Londres");
        User User8 = new User(8,"user.8@hotmail.com","Dio",encoder.encode("f"), "Dio Brando", img8, true, "Londres");
        User User9 = new User(9,"user.9@hotmail.com","Robert",encoder.encode("f"), "Robert Speedwagon", img9, true, "Londres");
        User User10 = new User(10,"user.10@hotmail.com","Caesar",encoder.encode("f"), "Caesar Zeppeli", img10, true, "Londres");
        User User11 = new User(11,"user.11@hotmail.com","Mohammed",encoder.encode("f"), "Mohammed Avdol", img11, true, "Egypt");
        User User12 = new User(12,"user.12@hotmail.com","Jean-Pierre",encoder.encode("f"), "Jean-Pierre Polnaref", img12, true, "Egypt");
        User User13 = new User(13,"user.13@hotmail.com","Noriaki",encoder.encode("f"), "Noriaki kakyouin", img13, true, "Egypt");
        User User14 = new User(14,"user.14@hotmail.com","Hirose",encoder.encode("f"), "Hirose Kouichi", img14, true, "Morioh");
        User User15 = new User(15,"user.15@hotmail.com","Kira",encoder.encode("f"), "Kira Yoshikage", img15, true, "Morioh");
        User User16 = new User(16,"user.16@hotmail.com","Rohan",encoder.encode("f"), "Rohan Kishibe", img16, true, "Morioh");
        User User17 = new User(17,"user.17@hotmail.com","Bruno",encoder.encode("f"), "Bruno Bucciarati", img17, true, "Napoles");
        User User18 = new User(18,"user.18@hotmail.com","Mista",encoder.encode("f"), "Mista guido", img18, true, "Napoles");
        User User19 = new User(19,"user.19@hotmail.com","Ghirga",encoder.encode("f"), "Ghirga Narancia", img19, true, "Napoles");
        User User20 = new User(20,"user.20@hotmail.com","Abbacchio",encoder.encode("f"), "Abbacchio Leone", img20, true, "Napoles");


        if(userRepo.findAll().isEmpty()) {
            userRepo.save(User1);
            userRepo.save(User2);
            userRepo.save(User3);
            userRepo.save(User4);
            userRepo.save(User5);
            userRepo.save(User6);
            userRepo.save(User7);
            userRepo.save(User8);
            userRepo.save(User9);
            userRepo.save(User10);
            userRepo.save(User11);
            userRepo.save(User12);
            userRepo.save(User13);
            userRepo.save(User14);
            userRepo.save(User15);
            userRepo.save(User16);
            userRepo.save(User17);
            userRepo.save(User18);
            userRepo.save(User19);
            userRepo.save(User20);
        }
    }
}