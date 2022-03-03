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
        this.productTypeRepo = productTypeRepo;
		this.userRepo = userRepo;
        this.rolRepo = rolRepo;
		this.ratingRepo = ratingRepo;
        this.brandRepo = brandRepo;
		this.encoder = encoder;
    }




    @PostConstruct
    public void Load() throws IOException{
        //User  img
        Path imagePathUser1 = ImgFolder.resolve("imgUser1.jpg");
		Resource imageUser1 = new UrlResource(imagePathUser1.toUri());
        Path imagePathUser2 = ImgFolder.resolve("imgUser2.jpg");
		Resource imageUser2 = new UrlResource(imagePathUser2.toUri());
        Path imagePathUser3 = ImgFolder.resolve("imgUser3.jpg");
		Resource imageUser3 = new UrlResource(imagePathUser3.toUri());
        Path imagePathUser4 = ImgFolder.resolve("imgUser4.jpg");
		Resource imageUser4 = new UrlResource(imagePathUser4.toUri());
        Path imagePathUser5 = ImgFolder.resolve("imgUser5.jpg");
		Resource imageUser5 = new UrlResource(imagePathUser5.toUri());
        Path imagePathUser6 = ImgFolder.resolve("imgUser6.jpg");
		Resource imageUser6 = new UrlResource(imagePathUser6.toUri());
        Path imagePathUser7 = ImgFolder.resolve("imgUser7.jpg");
		Resource imageUser7 = new UrlResource(imagePathUser7.toUri());
        Path imagePathUser8 = ImgFolder.resolve("imgUser8.jpg");
		Resource imageUser8 = new UrlResource(imagePathUser8.toUri());
        Path imagePathUser9 = ImgFolder.resolve("imgUser9.jpg");
		Resource imageUser9 = new UrlResource(imagePathUser9.toUri());
        Path imagePathUser10 = ImgFolder.resolve("imgUser10.jpg");
		Resource imageUser10 = new UrlResource(imagePathUser10.toUri());
        Path imagePathUser11 = ImgFolder.resolve("imgUser11.jpg");
		Resource imageUser11 = new UrlResource(imagePathUser11.toUri());
        Path imagePathUser12 = ImgFolder.resolve("imgUser12.jpg");
		Resource imageUser12 = new UrlResource(imagePathUser12.toUri());
        Path imagePathUser13 = ImgFolder.resolve("imgUser13.jpg");
		Resource imageUser13 = new UrlResource(imagePathUser13.toUri());
        Path imagePathUser14 = ImgFolder.resolve("imgUser14.jpg");
		Resource imageUser14 = new UrlResource(imagePathUser14.toUri());
        Path imagePathUser15 = ImgFolder.resolve("imgUser15.jpg");
		Resource imageUser15 = new UrlResource(imagePathUser15.toUri());
        Path imagePathUser16 = ImgFolder.resolve("imgUser16.jpg");
		Resource imageUser16 = new UrlResource(imagePathUser16.toUri());
        Path imagePathUser17 = ImgFolder.resolve("imgUser17.jpg");
		Resource imageUser17 = new UrlResource(imagePathUser17.toUri());
        Path imagePathUser18 = ImgFolder.resolve("imgUser18.jpg");
		Resource imageUser18 = new UrlResource(imagePathUser18.toUri());
        Path imagePathUser19 = ImgFolder.resolve("imgUser19.jpg");
		Resource imageUser19 = new UrlResource(imagePathUser19.toUri());
        Path imagePathUser20 = ImgFolder.resolve("imgUser20.jpg");
		Resource imageUser20 = new UrlResource(imagePathUser20.toUri());


        Blob imgU1 = BlobProxy.generateProxy(imageUser1.getInputStream(), imageUser1.contentLength());
        Blob imgU2 = BlobProxy.generateProxy(imageUser2.getInputStream(), imageUser2.contentLength());
        Blob imgU3 = BlobProxy.generateProxy(imageUser3.getInputStream(), imageUser3.contentLength());
        Blob imgU4 = BlobProxy.generateProxy(imageUser4.getInputStream(), imageUser4.contentLength());
        Blob imgU5 = BlobProxy.generateProxy(imageUser5.getInputStream(), imageUser5.contentLength());
        Blob imgU6 = BlobProxy.generateProxy(imageUser6.getInputStream(), imageUser6.contentLength());
        Blob imgU7 = BlobProxy.generateProxy(imageUser7.getInputStream(), imageUser7.contentLength());
        Blob imgU8 = BlobProxy.generateProxy(imageUser8.getInputStream(), imageUser8.contentLength());
        Blob imgU9 = BlobProxy.generateProxy(imageUser9.getInputStream(), imageUser9.contentLength());
        Blob imgU10 = BlobProxy.generateProxy(imageUser10.getInputStream(), imageUser10.contentLength());
        Blob imgU11 = BlobProxy.generateProxy(imageUser11.getInputStream(), imageUser11.contentLength());
        Blob imgU12 = BlobProxy.generateProxy(imageUser12.getInputStream(), imageUser12.contentLength());
        Blob imgU13 = BlobProxy.generateProxy(imageUser13.getInputStream(), imageUser13.contentLength());
        Blob imgU14 = BlobProxy.generateProxy(imageUser14.getInputStream(), imageUser14.contentLength());
        Blob imgU15 = BlobProxy.generateProxy(imageUser15.getInputStream(), imageUser15.contentLength());
        Blob imgU16 = BlobProxy.generateProxy(imageUser16.getInputStream(), imageUser16.contentLength());
        Blob imgU17 = BlobProxy.generateProxy(imageUser17.getInputStream(), imageUser17.contentLength());
        Blob imgU18 = BlobProxy.generateProxy(imageUser18.getInputStream(), imageUser18.contentLength());
        Blob imgU19 = BlobProxy.generateProxy(imageUser19.getInputStream(), imageUser19.contentLength());
        Blob imgU20 = BlobProxy.generateProxy(imageUser20.getInputStream(), imageUser20.contentLength());

        //Product img
        Path imagePathProd1 = ImgFolder.resolve("auriculares1.jpg");
		Resource imageProd1 = new UrlResource(imagePathProd1.toUri());
        Path imagePathProd2 = ImgFolder.resolve("auriculares1_2.jpg");
		Resource imageProd2 = new UrlResource(imagePathProd2.toUri());
        Path imagePathProd3 = ImgFolder.resolve("auriculares1_4.jpg");
		Resource imageProd3 = new UrlResource(imagePathProd3.toUri());
        Path imagePathProd4 = ImgFolder.resolve("auriculares2.jpg");
		Resource imageProd4 = new UrlResource(imagePathProd4.toUri());
        Path imagePathProd5 = ImgFolder.resolve("auriculares2_1.jpg");
		Resource imageProd5 = new UrlResource(imagePathProd5.toUri());
        Path imagePathProd6 = ImgFolder.resolve("discoduro1.jpg");
		Resource imageProd6 = new UrlResource(imagePathProd6.toUri());
        Path imagePathProd7 = ImgFolder.resolve("discoduro1_1.jpg");
		Resource imageProd7 = new UrlResource(imagePathProd7.toUri());
        Path imagePathProd8 = ImgFolder.resolve("discoduro2.jpg");
		Resource imageProd8 = new UrlResource(imagePathProd8.toUri());
        Path imagePathProd9 = ImgFolder.resolve("discoduro2_2.jpg");
		Resource imageProd9 = new UrlResource(imagePathProd9.toUri());
        Path imagePathProd10 = ImgFolder.resolve("discoduro2_3.jpg");
		Resource imageProd10 = new UrlResource(imagePathProd10.toUri());
        Path imagePathProd11 = ImgFolder.resolve("discoduro3.jpg");
		Resource imageProd11 = new UrlResource(imagePathProd11.toUri());
        Path imagePathProd12 = ImgFolder.resolve("iPjone6_1.png");
		Resource imageProd12 = new UrlResource(imagePathProd12.toUri());
        Path imagePathProd13 = ImgFolder.resolve("iPhone6_2.jpg");
		Resource imageProd13 = new UrlResource(imagePathProd13.toUri());
        Path imagePathProd14 = ImgFolder.resolve("iPjone6s_1.png");
		Resource imageProd14 = new UrlResource(imagePathProd14.toUri());
        Path imagePathProd15 = ImgFolder.resolve("iPjone6s_2.png");
		Resource imageProd15 = new UrlResource(imagePathProd15.toUri());
        Path imagePathProd16 = ImgFolder.resolve("monitor1.jpg");
		Resource imageProd16 = new UrlResource(imagePathProd16.toUri());
        Path imagePathProd17 = ImgFolder.resolve("monitor1_1.jpg");
		Resource imageProd17 = new UrlResource(imagePathProd17.toUri());
        Path imagePathProd18 = ImgFolder.resolve("monitor2.jpg");
		Resource imageProd18 = new UrlResource(imagePathProd18.toUri());
        Path imagePathProd19 = ImgFolder.resolve("monitor2_1.jpg");
		Resource imageProd19 = new UrlResource(imagePathProd19.toUri());
        Path imagePathProd20 = ImgFolder.resolve("placabase1.jpg");
		Resource imageProd20 = new UrlResource(imagePathProd20.toUri());
        Path imagePathProd21 = ImgFolder.resolve("placabase1_1.jpg");
		Resource imageProd21 = new UrlResource(imagePathProd21.toUri());
        Path imagePathProd22 = ImgFolder.resolve("placabase1_3.jpg");
		Resource imageProd22 = new UrlResource(imagePathProd22.toUri());
        Path imagePathProd23 = ImgFolder.resolve("placabase2.jpg");
		Resource imageProd23 = new UrlResource(imagePathProd23.toUri());
        Path imagePathProd24 = ImgFolder.resolve("placabase2_1.jpg");
		Resource imageProd24 = new UrlResource(imagePathProd24.toUri());
        Path imagePathProd25 = ImgFolder.resolve("procesador1.jpg");
		Resource imageProd25 = new UrlResource(imagePathProd25.toUri());
        Path imagePathProd26 = ImgFolder.resolve("procesador1_1.jpg");
		Resource imageProd26 = new UrlResource(imagePathProd26.toUri());
        Path imagePathProd27 = ImgFolder.resolve("procesador2.jpg");
		Resource imageProd27 = new UrlResource(imagePathProd27.toUri());
        Path imagePathProd28 = ImgFolder.resolve("procesador3.jpg");
		Resource imageProd28 = new UrlResource(imagePathProd28.toUri());
        Path imagePathProd29 = ImgFolder.resolve("raton1.jpg");
		Resource imageProd29 = new UrlResource(imagePathProd29.toUri());
        Path imagePathProd30 = ImgFolder.resolve("raton1_1.jpg");
		Resource imageProd30 = new UrlResource(imagePathProd30.toUri());
        Path imagePathProd31 = ImgFolder.resolve("raton2.jpg");
		Resource imageProd31 = new UrlResource(imagePathProd31.toUri());
        Path imagePathProd32 = ImgFolder.resolve("raton2_1.jpg");
		Resource imageProd32 = new UrlResource(imagePathProd32.toUri());
        Path imagePathProd33 = ImgFolder.resolve("tarjetagrafica1_1.jpg");
		Resource imageProd33 = new UrlResource(imagePathProd33.toUri());
        Path imagePathProd34 = ImgFolder.resolve("tarjetagrafica1_2.jpg");
		Resource imageProd34 = new UrlResource(imagePathProd34.toUri());
        Path imagePathProd35 = ImgFolder.resolve("tarjetagrafica1_4.jpg");
		Resource imageProd35 = new UrlResource(imagePathProd35.toUri());
        Path imagePathProd36 = ImgFolder.resolve("tarjetagrafica2.jpg");
		Resource imageProd36 = new UrlResource(imagePathProd36.toUri());
        Path imagePathProd37 = ImgFolder.resolve("tarjetagrafica2_2.jpg");
		Resource imageProd37 = new UrlResource(imagePathProd37.toUri());
        Path imagePathProd38 = ImgFolder.resolve("tarjetagrafica2_3.jpg");
		Resource imageProd38 = new UrlResource(imagePathProd38.toUri());
        Path imagePathProd39 = ImgFolder.resolve("tarjetagrafica3.jpg");
		Resource imageProd39 = new UrlResource(imagePathProd39.toUri());
        Path imagePathProd40 = ImgFolder.resolve("teclado1.jpg");
		Resource imageProd40 = new UrlResource(imagePathProd40.toUri());
        Path imagePathProd41 = ImgFolder.resolve("teclado1_1.jpg");
		Resource imageProd41 = new UrlResource(imagePathProd41.toUri());
        Path imagePathProd42 = ImgFolder.resolve("teclado2.jpg");
		Resource imageProd42 = new UrlResource(imagePathProd42.toUri());


        Blob imgP1 = BlobProxy.generateProxy(imageProd1.getInputStream(), imageProd1.contentLength());
        Blob imgP2 = BlobProxy.generateProxy(imageProd2.getInputStream(), imageProd2.contentLength());
        Blob imgP3 = BlobProxy.generateProxy(imageProd3.getInputStream(), imageProd3.contentLength());
        Blob imgP4 = BlobProxy.generateProxy(imageProd4.getInputStream(), imageProd4.contentLength());
        Blob imgP5 = BlobProxy.generateProxy(imageProd5.getInputStream(), imageProd5.contentLength());
        Blob imgP6 = BlobProxy.generateProxy(imageProd6.getInputStream(), imageProd6.contentLength());
        Blob imgP7 = BlobProxy.generateProxy(imageProd7.getInputStream(), imageProd7.contentLength());
        Blob imgP8 = BlobProxy.generateProxy(imageProd8.getInputStream(), imageProd8.contentLength());
        Blob imgP9 = BlobProxy.generateProxy(imageProd9.getInputStream(), imageProd9.contentLength());
        Blob imgP10 = BlobProxy.generateProxy(imageProd10.getInputStream(), imageProd10.contentLength());
        Blob imgP11 = BlobProxy.generateProxy(imageProd11.getInputStream(), imageProd11.contentLength());
        Blob imgP12 = BlobProxy.generateProxy(imageProd12.getInputStream(), imageProd12.contentLength());
        Blob imgP13 = BlobProxy.generateProxy(imageProd13.getInputStream(), imageProd13.contentLength());
        Blob imgP14 = BlobProxy.generateProxy(imageProd14.getInputStream(), imageProd14.contentLength());
        Blob imgP15 = BlobProxy.generateProxy(imageProd15.getInputStream(), imageProd15.contentLength());
        Blob imgP16 = BlobProxy.generateProxy(imageProd16.getInputStream(), imageProd16.contentLength());
        Blob imgP17 = BlobProxy.generateProxy(imageProd17.getInputStream(), imageProd17.contentLength());
        Blob imgP18 = BlobProxy.generateProxy(imageProd18.getInputStream(), imageProd18.contentLength());
        Blob imgP19 = BlobProxy.generateProxy(imageProd19.getInputStream(), imageProd19.contentLength());
        Blob imgP20 = BlobProxy.generateProxy(imageProd20.getInputStream(), imageProd20.contentLength());
        Blob imgP21 = BlobProxy.generateProxy(imageProd21.getInputStream(), imageProd21.contentLength());
        Blob imgP22 = BlobProxy.generateProxy(imageProd22.getInputStream(), imageProd22.contentLength());
        Blob imgP23 = BlobProxy.generateProxy(imageProd23.getInputStream(), imageProd23.contentLength());
        Blob imgP24 = BlobProxy.generateProxy(imageProd24.getInputStream(), imageProd24.contentLength());
        Blob imgP25 = BlobProxy.generateProxy(imageProd25.getInputStream(), imageProd25.contentLength());
        Blob imgP26 = BlobProxy.generateProxy(imageProd26.getInputStream(), imageProd26.contentLength());
        Blob imgP27 = BlobProxy.generateProxy(imageProd27.getInputStream(), imageProd27.contentLength());
        Blob imgP28 = BlobProxy.generateProxy(imageProd28.getInputStream(), imageProd28.contentLength());
        Blob imgP29 = BlobProxy.generateProxy(imageProd29.getInputStream(), imageProd29.contentLength());
        Blob imgP30 = BlobProxy.generateProxy(imageProd30.getInputStream(), imageProd30.contentLength());
        Blob imgP31 = BlobProxy.generateProxy(imageProd31.getInputStream(), imageProd31.contentLength());
        Blob imgP32 = BlobProxy.generateProxy(imageProd32.getInputStream(), imageProd32.contentLength());
        Blob imgP33 = BlobProxy.generateProxy(imageProd33.getInputStream(), imageProd33.contentLength());
        Blob imgP34 = BlobProxy.generateProxy(imageProd34.getInputStream(), imageProd34.contentLength());
        Blob imgP35 = BlobProxy.generateProxy(imageProd35.getInputStream(), imageProd35.contentLength());
        Blob imgP36 = BlobProxy.generateProxy(imageProd36.getInputStream(), imageProd36.contentLength());
        Blob imgP37 = BlobProxy.generateProxy(imageProd37.getInputStream(), imageProd37.contentLength());
        Blob imgP38 = BlobProxy.generateProxy(imageProd38.getInputStream(), imageProd38.contentLength());
        Blob imgP39 = BlobProxy.generateProxy(imageProd39.getInputStream(), imageProd39.contentLength());
        Blob imgP40 = BlobProxy.generateProxy(imageProd40.getInputStream(), imageProd40.contentLength());
        Blob imgP41 = BlobProxy.generateProxy(imageProd41.getInputStream(), imageProd41.contentLength());
        Blob imgP42 = BlobProxy.generateProxy(imageProd42.getInputStream(), imageProd42.contentLength());

        //User creations
        User User1 = new User(1,"user.1@hotmail.com","Jonathan",encoder.encode("f"), "Jonathan Joestar", imgU1, true, "Londres");
        User User2 = new User(2,"user.2@hotmail.com","Joseph",encoder.encode("f"), "Joseph Joestar", imgU2, true, "Londres");
        User User3 = new User(3,"user.3@hotmail.com","Jotaro",encoder.encode("f"), "Jotaro Kuujou", imgU3, true, "Egypt");
        User User4 = new User(4,"user.4@hotmail.com","Josuke",encoder.encode("f"), "Josuke Higashikata", imgU4, true, "Morioh");
        User User5 = new User(5,"user.5@hotmail.com","Giorno",encoder.encode("f"), "Giorno Giovanna", imgU5, true, "Napoles");
        User User6 = new User(6,"user.6@hotmail.com","Jolyne",encoder.encode("f"), "Jolyne Kujou", imgU6, true, "Florida");
        User User7 = new User(7,"user.7@hotmail.com","Anthonio",encoder.encode("f"), "Anthonio Zeppeli", imgU7, true, "Londres");
        User User8 = new User(8,"user.8@hotmail.com","Dio",encoder.encode("f"), "Dio Brando", imgU8, true, "Londres");
        User User9 = new User(9,"user.9@hotmail.com","Robert",encoder.encode("f"), "Robert Speedwagon", imgU9, true, "Londres");
        User User10 = new User(10,"user.10@hotmail.com","Caesar",encoder.encode("f"), "Caesar Zeppeli", imgU10, true, "Londres");
        User User11 = new User(11,"user.11@hotmail.com","Mohammed",encoder.encode("f"), "Mohammed Avdol", imgU11, true, "Egypt");
        User User12 = new User(12,"user.12@hotmail.com","Jean-Pierre",encoder.encode("f"), "Jean-Pierre Polnaref", imgU12, true, "Egypt");
        User User13 = new User(13,"user.13@hotmail.com","Noriaki",encoder.encode("f"), "Noriaki kakyouin", imgU13, true, "Egypt");
        User User14 = new User(14,"user.14@hotmail.com","Hirose",encoder.encode("f"), "Hirose Kouichi", imgU14, true, "Morioh");
        User User15 = new User(15,"user.15@hotmail.com","Kira",encoder.encode("f"), "Kira Yoshikage", imgU15, true, "Morioh");
        User User16 = new User(16,"user.16@hotmail.com","Rohan",encoder.encode("f"), "Rohan Kishibe", imgU16, true, "Morioh");
        User User17 = new User(17,"user.17@hotmail.com","Bruno",encoder.encode("f"), "Bruno Bucciarati", imgU17, true, "Napoles");
        User User18 = new User(18,"user.18@hotmail.com","Mista",encoder.encode("f"), "Mista guido", imgU18, true, "Napoles");
        User User19 = new User(19,"user.19@hotmail.com","Ghirga",encoder.encode("f"), "Ghirga Narancia", imgU19, true, "Napoles");
        User User20 = new User(20,"user.20@hotmail.com","Abbacchio",encoder.encode("f"), "Abbacchio Leone", imgU20, true, "Napoles");


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