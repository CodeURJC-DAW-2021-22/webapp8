package webapp8.webandtech.service;

import java.io.IOException;
import java.sql.Blob;


import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import webapp8.webandtech.model.Brand;
import webapp8.webandtech.model.Order;
import webapp8.webandtech.model.Product;
import webapp8.webandtech.model.ProductType;
import webapp8.webandtech.model.Rating;
import webapp8.webandtech.model.Rol;
import webapp8.webandtech.model.User;
import webapp8.webandtech.repository.BrandRepository;
import webapp8.webandtech.repository.OrderRepository;
import webapp8.webandtech.repository.ProductRepository;
import webapp8.webandtech.repository.ProductTypeRepository;
import webapp8.webandtech.repository.RatingRepository;
import webapp8.webandtech.repository.RolRepository;
import webapp8.webandtech.repository.UserRepository;

@Service
public class LoaderService {

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


    public void Load() throws IOException{
        //User  img
        Resource imageUser1 = new ClassPathResource("/static/images/imgUser1.jpg");
		Resource imageUser2 = new ClassPathResource("/static/images/imgUser2.jpg");
		Resource imageUser3 = new ClassPathResource("/static/images/imgUser3.jpg");
		Resource imageUser4 = new ClassPathResource("/static/images/imgUser4.jpg");
		Resource imageUser5 = new ClassPathResource("/static/images/imgUser5.jpg");
		Resource imageUser6 = new ClassPathResource("/static/images/imgUser6.jpg");
		Resource imageUser7 = new ClassPathResource("/static/images/imgUser7.jpg");
		Resource imageUser8 = new ClassPathResource("/static/images/imgUser8.jpg");
		Resource imageUser9 = new ClassPathResource("/static/images/imgUser9.jpg");
		Resource imageUser10 = new ClassPathResource("/static/images/imgUser10.jpg");
		Resource imageUser11 = new ClassPathResource("/static/images/imgUser11.jpg");
		Resource imageUser12 = new ClassPathResource("/static/images/imgUser12.jpg");
		Resource imageUser13 = new ClassPathResource("/static/images/imgUser13.jpg");
		Resource imageUser14 = new ClassPathResource("/static/images/imgUser14.jpg");
		Resource imageUser15 = new ClassPathResource("/static/images/imgUser15.jpg");
		Resource imageUser16 = new ClassPathResource("/static/images/imgUser16.jpg");
		Resource imageUser17 = new ClassPathResource("/static/images/imgUser17.jpg");
		Resource imageUser18 = new ClassPathResource("/static/images/imgUser18.jpg");
		Resource imageUser19 = new ClassPathResource("/static/images/imgUser19.jpg");
		Resource imageUser20 = new ClassPathResource("/static/images/imgUser20.jpg");


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
        Resource imageProd1 = new ClassPathResource("/static/images/auriculares1.jpg");
        Resource imageProd2 = new ClassPathResource("/static/images/auriculares1_2.jpg");
        Resource imageProd3 = new ClassPathResource("/static/images/auriculares1_4.jpg");
        Resource imageProd4 = new ClassPathResource("/static/images/auriculares2.jpg");
        Resource imageProd5 = new ClassPathResource("/static/images/auriculares2_1.jpg");
        Resource imageProd6 = new ClassPathResource("/static/images/discoduro1.jpg");
        Resource imageProd7 = new ClassPathResource("/static/images/discoduro1_1.jpg");
        Resource imageProd8 = new ClassPathResource("/static/images/discoduro2.jpg");
        Resource imageProd9 = new ClassPathResource("/static/images/discoduro2_2.jpg");
        Resource imageProd10 = new ClassPathResource("/static/images/discoduro2_3.jpg");
        Resource imageProd11 = new ClassPathResource("/static/images/iPhone6_1.png");
        Resource imageProd12 = new ClassPathResource("/static/images/iPhone6_2.jpg");
        Resource imageProd13 = new ClassPathResource("/static/images/iPhone6s_1.png");
        Resource imageProd14 = new ClassPathResource("/static/images/iPhone6s_2.png");
        Resource imageProd15 = new ClassPathResource("/static/images/monitor1.jpg");
        Resource imageProd16 = new ClassPathResource("/static/images/monitor1_1.jpg");
        Resource imageProd17 = new ClassPathResource("/static/images/monitor2.jpg");
        Resource imageProd18 = new ClassPathResource("/static/images/monitor2_1.jpg");
        Resource imageProd19 = new ClassPathResource("/static/images/placabase1.jpg");
        Resource imageProd20 = new ClassPathResource("/static/images/placabase1_1.jpg");
        Resource imageProd21 = new ClassPathResource("/static/images/placabase1_3.jpg");
        Resource imageProd22 = new ClassPathResource("/static/images/placabase2.jpg");
        Resource imageProd23 = new ClassPathResource("/static/images/placabase2_1.jpg");
        Resource imageProd24 = new ClassPathResource("/static/images/procesador1.jpg");
        Resource imageProd25 = new ClassPathResource("/static/images/procesador1_1.jpg");
        Resource imageProd26 = new ClassPathResource("/static/images/procesador2.jpg");
        Resource imageProd27 = new ClassPathResource("/static/images/raton1.jpg");
        Resource imageProd28 = new ClassPathResource("/static/images/raton1_1.jpg");
        Resource imageProd29 = new ClassPathResource("/static/images/raton2.jpg");
        Resource imageProd30 = new ClassPathResource("/static/images/raton2_1.jpg");
        Resource imageProd31 = new ClassPathResource("/static/images/tarjetagrafica1_1.jpg");
        Resource imageProd32 = new ClassPathResource("/static/images/tarjetagrafica1_2.jpg");
        Resource imageProd33 = new ClassPathResource("/static/images/tarjetagrafica1_4.jpg");
        Resource imageProd34 = new ClassPathResource("/static/images/tarjetagrafica2.jpg");
        Resource imageProd35 = new ClassPathResource("/static/images/tarjetagrafica2_2.jpg");
        Resource imageProd36 = new ClassPathResource("/static/images/tarjetagrafica2_3.jpg");
        Resource imageProd37 = new ClassPathResource("/static/images/teclado1.jpg");
        Resource imageProd38 = new ClassPathResource("/static/images/teclado1_1.jpg");
        Resource imageProd39 = new ClassPathResource("/static/images/teclado2.jpg");
       
        

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


        //User creations
        User User1 = new User(1, "user.1@hotmail.com","Jonathan",encoder.encode("f"), "Jonathan Joestar", imgU1, true, "Londres");
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


        //Rol creation for users - "USER"
        Rol Rol1 = new Rol("USER", User1);
		Rol Rol2 = new Rol("USER", User2);
		Rol Rol3 = new Rol("USER", User3);
		Rol Rol4 = new Rol("USER", User4);
		Rol Rol5 = new Rol("USER", User5);
		Rol Rol6 = new Rol("USER", User6);
		Rol Rol7 = new Rol("USER", User7);
		Rol Rol8 = new Rol("USER", User8);
		Rol Rol9 = new Rol("USER", User9);
		Rol Rol10 = new Rol("USER", User10);
		Rol Rol11 = new Rol("USER", User11);
		Rol Rol12 = new Rol("USER", User12);
		Rol Rol13 = new Rol("USER", User13);
		Rol Rol14 = new Rol("USER", User14);
		Rol Rol15 = new Rol("USER", User15);
		Rol Rol16 = new Rol("USER", User16);
		Rol Rol17 = new Rol("USER", User17);
		Rol Rol18 = new Rol("USER", User18);
		Rol Rol19 = new Rol("USER", User19);
		Rol Rol20 = new Rol("USER", User20);


        //rol creation for users - "Admin"
        Rol Rol21 = new Rol("ADMIN", User1);
		Rol Rol22 = new Rol("ADMIN", User2);
		Rol Rol23 = new Rol("ADMIN", User3);
		Rol Rol24 = new Rol("ADMIN", User4);
		Rol Rol25 = new Rol("ADMIN", User5);


        //Order creation
        Order order1 = new Order(1, 299, User6, "2/3/7", "1/2/2022");
        Order order2 = new Order(2, 353, User6, "1/6/13/15", "2/2/2022");
        Order order3 = new Order(3, 736, User7, "4/5/7/11/12/13/18", "3/2/2022");
        Order order4 = new Order(4, 234, User7, "2/3/7", "4/2/2022");
        Order order5 = new Order(5, 525, User8, "1/3/14/16/17", "5/2/2022");
        Order order6 = new Order(6, 99, User9, "2/3/7", "6/2/2022");
        Order order7 = new Order(7, 25, User9, "4", "7/2/2022");
        Order order8 = new Order(8, 64, User9, "7", "8/2/2022");
        Order order9 = new Order(9, 120, User10, "2/3/7", "9/2/2022");
        Order order10 = new Order(10, 56, User10, "5", "10/2/2022");
        Order order11 = new Order(11, 25, User11, "4", "11/2/2022");
        Order order12 = new Order(12, 400, User12, "1/3/7/14/16", "12/2/2022");
        Order order13 = new Order(13, 36, User13, "8", "13/2/2022");
        Order order14 = new Order(14, 64, User14, "7", "14/2/2022");
        Order order15 = new Order(15, 99, User15, "2/3/7", "15/2/2022");
        Order order16 = new Order(16, 18, User16, "2/3/7", "16/2/2022");
        Order order17 = new Order(17, 36, User17, "8", "17/2/2022");
        Order order18 = new Order(18, 199, User18, "2/3/5/8", "18/2/2022");
        Order order19 = new Order(19, 299, User19, "2/3/7", "19/2/2022");
        Order order20 = new Order(20, 56, User20, "5", "20/2/2022");


        //ProductType creation
        ProductType prodType1 = new ProductType(1, "placas bases");
        ProductType prodType2 = new ProductType(2, "discos duros");
        ProductType prodType3 = new ProductType(3, "procesadores");
        ProductType prodType4 = new ProductType(4, "tarjetas graficas");
        ProductType prodType5 = new ProductType(5, "monitores");
        ProductType prodType6 = new ProductType(6, "auriculares");
        ProductType prodType7 = new ProductType(7, "teclados");
        ProductType prodType8 = new ProductType(8, "ratones");
        ProductType prodType9 = new ProductType(9, "telefonos"); 


        //Brand creation
        Brand brand1 = new Brand(1, "forgeon");
        Brand brand2 = new Brand(2, "tempest");
        Brand brand3 = new Brand(3, "alcyon x");
        Brand brand4 = new Brand(4, "apple");
        Brand brand5 = new Brand(5, "PcCom");
        Brand brand6 = new Brand(6, "samsung");
        Brand brand7 = new Brand(7, "msi");
        Brand brand8 = new Brand(8, "gigabyte");
        Brand brand9 = new Brand(9, "ryzen");
        Brand brand10 = new Brand(10, "intel");
        Brand brand11 = new Brand(11, "newskill");


        //Product creations
        Product prod1 = new Product(1,"Forgeon General Auriculares Gaming Inalámbricos PC/PS4/PS5/Xbox/Xbox X/Switch Negros", 146,
                "Desde Forgeon presentamos los nuevos auriculares Forgeon General Wireless Headset 2,4 GHz. Unos auriculares con unos"
                +"acabados de primera, acompañados de la más novedosa tecnología de triple conexión que te da la libertad que necesitas" 
                +"para jugar cómodamente. Esto unido a sus extras y a su peso de tan solo 350 g hace de estos auriculares imprescindibles"
                +"en tu setup gaming ¡No te quedes sin ellos!", 4, "auriculares", "perifericos", "forgeon", imgP1, imgP2, imgP3, true, true, true);
        Product prod2 = new Product(2,"Tempest GHS110 Knight Auriculares Gaming RGB PC", 16, "Desde Forgeon presentamos los nuevos"
                +"auriculares Tempest GHS110 Knight 2,4 GHz. Unos auriculares con unos acabados de primera, acompañados de la más novedosa"
                +"tecnología de triple conexión que te da la libertad que necesitas para jugar cómodamente. Esto unido a sus extras y a su"
                +"peso de tan solo 350 g hace de estos auriculares imprescindibles en tu setup gaming ¡No te quedes sin ellos!", 3, 
                "auriculares", "perifericos", "tempest", imgP4, imgP5, null, true, true, false);
        Product prod3 = new Product(3,"Kingston A400 SSD 480GB", 500, "La unidad A400 de estado sólido de Kingston ofrece enormes mejoras"
                +"en la velocidad de respuesta, sin actualizaciones adicionales del hardware. Brinda lapsos de arranque, carga y de"
                +"transferencia de archivos increíblemente más breves en comparación con las unidades de disco duro mecánico.", 3, 
                "discos duros", "componentes", "forgeon", imgP6, imgP7, null, true, true, false);
        Product prod4 = new Product(1,"Nfortec Alcyon X 512GB SSD M.2 NVMe", 52, "Si estás pensando en subir de nivel y mejorar tu equipo"
                +"PC gaming, Nfortec Alcyon SSD está disponible en capacidades de 256 y 512 GB, a la altura de los mejores SSD del mercado"
                +"actual de componentes.", 3, "discos duros", "componentes", "alcyon x", imgP8, imgP9, imgP10, true, true, true);
        Product prod5 = new Product(1,"IPhone 6", 146, "El iPhone 6 es un teléfono inteligente de gama alta desarrollado por Apple Inc."
                +"Funciona con el sistema operativo iOS 12. Fue presentado el 9 de septiembre de 2014 y lanzado a la venta el 19 de octubre de 2014."
                +"La serie iPhone 6 es en conjunto un sucesor del iPhone 5s y el iPhone 5C", 3, "telefono", "telefono", "apple", imgP11, imgP12, null, 
                true, true, false);
        Product prod6 = new Product(1,"IPhone 6s", 799, "El iPhone 6s es un teléfono inteligente de gama alta diseñado por Apple Inc."
                +"con procesador de dos núcleos a 1.85 GHz, 2 GB de memoria RAM y pantalla de 4,7 pulgadas. Es parte de la serie iPhone y fue"
                +"anunciado el 9 de septiembre de 2015. El iPhone 6s y 6s Plus son conjuntamente sucesores del iPhone 6 y iPhone 6 Plus de 2014.", 
                4, "telefono", "telefono", "apple", imgP13, imgP14, null, true, true, false);
        Product prod7 = new Product(1,"PcCom Discovery 27 pulgadas LED FullHD 165Hz FreeSync Curvo", 239, "Desde PCCOM presentamos el Monitor"
                +"Discovery de 27 pulgadas. Un exclusivo modelo de 27 pulgadas curvo 1500R que te ofrece una experiencia gaming totalmente envolvente"
                +"gracias a su resolución Full HD y tasa de refresco de hasta 165 Hz con 1ms de respuesta MPRT. Si quieres disfrutar de las buenas"
                +"características de este monitor, no puede faltar en tu setup gaming.", 4, "monitores", "perifericos", "PcCom", imgP15, imgP16, null, 
                true, true, false);
        Product prod8 = new Product(1,"Samsung S22F350FHU 22 pulgadas Full HD LED", 36, "Te presentamos el monitor S22F350FHU de 22 de Samsung. Un"
                +"monitor Full HD de tan sólo 10mm de grosor, 'Modo Juego' para disfrutar de una experiencia gaming única y el 'Eye Saver Mode' que"
                +"reduce la emisión de ondas de luz azul que fatigan tus retinas.", 3, "monitores", "perifericos", "samsung", imgP17, imgP18, null, 
                true, true, false);     
        Product prod9 = new Product(1,"MSI B560M PRO-VDH", 99, "La serie PRO ayuda a los usuarios a trabajar de forma más inteligente al ofrecer una"
                +"experiencia eficiente y productiva. Con una funcionalidad estable y un ensamblaje de alta calidad, las placas base de la serie PRO"
                +"brindan no solo flujos de trabajo profesionales optimizados, sino también menos resolución de problemas y longevidad.", 4, 
                "placas bases", "componentes", "msi", imgP19, imgP20, imgP21, true, true, true);     
        Product prod10 = new Product(1,"Gigabyte H310M S2H 2.0", 36, "Te presentamos la 310M S2H 2.0 de Gigabyte, una placa base con socket LGA 1151"
                +"y chipset B360.", 3, "placas bases", "componentes", "gigabyte", imgP22, imgP23, null, true, true, false);  
        Product prod11 = new Product(1,"MSI B560M PRO-VDH", 99, "La serie PRO ayuda a los usuarios a trabajar de forma más inteligente al ofrecer"
                +"una experiencia eficiente y productiva. Con una funcionalidad estable y un ensamblaje de alta calidad, las placas base de la serie"
                +"PRO brindan no solo flujos de trabajo profesionales optimizados, sino también menos resolución de problemas y longevidad.", 4, 
                "procesadores", "componentes", "ryzen", imgP24, imgP25, null, true, true, false);
        Product prod12 = new Product(1,"Intel Celeron G5905 3.50GHz", 37, "Los nuevos procesadores Intel® Core™ de 10ª generación ofrecen mejoras"
                +"de rendimiento notables para conseguir una productividad mejorada y un entretenimiento impresionante, incluyendo Intel® Wi-Fi 6 (Gig+),"
                +"tecnología Thunderbolt™ 3, HDR 4K, optimización de sistema inteligente y mucho más.", 3, "procesadores", "componentes", "intel", imgP26, 
                null, null, true, true, false);
        Product prod13 = new Product(1,"Forgeon Darrowspike Ratón Gaming RGB Wireless 19000DPI Negro", 99, "Presentamos el nuevo mouse gaming Forgeon"
                +"Darrowspike Wireless. Este mouse inalámbrico ofrece unas prestaciones inigualables a un precio muy competitivo. Con el receptor"
                +"inalámbrico incorporado sencillamente con conectarlo empezarás a disfrutar de tus mejores partidas dejando de lado esos cables que"
                +"dificultan el movimiento", 5, "ratones", "perifericos", "forgeon", imgP27, imgP28, null, true, true, false);
        Product prod14 = new Product(1,"Tempest MS-300 RGB Soldier Ratón Gaming 4000DPI", 9, "Presentamos el nuevo mouse gaming Forgeon Darrowspike Wireless."
                +"Este mouse inalámbrico ofrece unas prestaciones inigualables a un precio muy competitivo. Con el receptor inalámbrico incorporado"
                +"sencillamente con conectarlo empezarás a disfrutar de tus mejores partidas dejando de lado esos cables que dificultan el movimiento.", 3, 
                "ratones", "perifericos", "tempest", imgP29, imgP30, null, true, true, false);    
        Product prod15 = new Product(1,"MSI GeForce RTX 3070 Ti VENTUS 3X OC 8GB GDDR6X", 999, "Disfruta de los mayores éxitos de ventas de hoy como nunca"
                +"antes con la fidelidad visual del trazado de rayos en tiempo real y el rendimiento definitivo de DLSS con tecnología de IA.", 5, 
                "tarjetas graficas", "componentes", "msi", imgP31, imgP32, imgP33, true, true, true);  
        Product prod16 = new Product(1,"Asus Radeon R7 240 2GB GDDR5 Low Profile", 31, "Disfruta de los mayores éxitos de ventas de hoy como nunca antes"
                +"con la fidelidad visual del trazado de rayos en tiempo real y el rendimiento definitivo de DLSS con tecnología de IA.", 3, 
                "tarjetas graficas", "componentes", "forgeon", imgP34, imgP35, imgP36, true, true, true);    
        Product prod17 = new Product(1,"Newskill Suiko Teclado Mecánico Gaming Full RGB Switch Kailh Blue", 64, "Newskill Suiko destaca por su novedoso"
                +"sistema RGB desde el que podrás crear un halo de retroiluminación a su alrededor con efectos realmente impresionantes para tu zona de"
                +"juego. Suiko forma parte de la gama de teclados mecánicos fabricados con switches premium. La tecnología Kailh estará a nuestro servicio"
                +"con sus versiones Red, Blue y Brown ofreciendo una excelente velocidad de respuesta en cada click. Para mejorar la experiencia en cada"
                +"partida hemos incorporado a la gama Suiko un reposamuñecas anexo acolchado que nos dará esa comodidad extra que buscamos.", 4, "teclados", 
                "perifericos", "newskill", imgP37, imgP38, null, true, true, false);
        Product prod18 = new Product(1,"Logitech MK295 Silent Wireless Combo De Ratón Y Teclado Inalámbricos", 24, "Mantén la concentración y haz más cosas"
                +"con la combinación silenciosa de teclado y ratón inalámbricos Logitech MK295, ambos dispositivos diseñados para eliminar el exceso de"
                +"ruido molesto para ti y la gente alrededor. Demos la bienvenida a una nueva experiencia de teclado y ratón prácticamente silenciosa.", 2, 
                "auriculares", "perifericos", "forgeon", imgP39, null, null, true, false, false);


        //rating creation
        Rating rating1 = new Rating(1, 4, "Calidad precio lo mejor va bastante bien y no tiene ningun problema lo recomiendo y el envio muy rápido.", prod1, User6);
        Rating rating2 = new Rating(2, 5, "Buscaba un producto sencillo y económico del estilo y me ha sorprendido mucho, no puedo pedir mas, lo recomiendo.", prod1, User7);
        Rating rating3 = new Rating(3, 4, "Calidad precio lo mejor va bastante bien y no tiene ningun problema lo recomiendo y el envio muy rápido.", prod2, User8);
        Rating rating4 = new Rating(4, 5, "Buscaba un producto sencillo y económico del estilo y me ha sorprendido mucho, no puedo pedir mas, lo recomiendo.", prod2, User9);
        Rating rating5 = new Rating(5, 4, "Calidad precio lo mejor va bastante bien y no tiene ningun problema lo recomiendo y el envio muy rápido.", prod3, User10);
        Rating rating6 = new Rating(6, 5, "Buscaba un producto sencillo y económico del estilo y me ha sorprendido mucho, no puedo pedir mas, lo recomiendo.", prod3, User11);
        Rating rating7 = new Rating(7, 4, "Calidad precio lo mejor va bastante bien y no tiene ningun problema lo recomiendo y el envio muy rápido.", prod4, User12);
        Rating rating8 = new Rating(8, 5, "Buscaba un producto sencillo y económico del estilo y me ha sorprendido mucho, no puedo pedir mas, lo recomiendo.", prod4, User13);
        Rating rating9 = new Rating(9, 4, "Calidad precio lo mejor va bastante bien y no tiene ningun problema lo recomiendo y el envio muy rápido.", prod5, User14);
        Rating rating10 = new Rating(10, 5, "Buscaba un producto sencillo y económico del estilo y me ha sorprendido mucho, no puedo pedir mas, lo recomiendo.", prod5, User15);
        Rating rating11 = new Rating(11, 4, "Calidad precio lo mejor va bastante bien y no tiene ningun problema lo recomiendo y el envio muy rápido.", prod6, User16);
        Rating rating12 = new Rating(12, 5, "Buscaba un producto sencillo y económico del estilo y me ha sorprendido mucho, no puedo pedir mas, lo recomiendo.", prod6, User17);
        Rating rating13 = new Rating(13, 4, "Calidad precio lo mejor va bastante bien y no tiene ningun problema lo recomiendo y el envio muy rápido.", prod7, User18);
        Rating rating14 = new Rating(14, 5, "Buscaba un producto sencillo y económico del estilo y me ha sorprendido mucho, no puedo pedir mas, lo recomiendo.", prod7, User19);
        Rating rating15 = new Rating(15, 4, "Calidad precio lo mejor va bastante bien y no tiene ningun problema lo recomiendo y el envio muy rápido.", prod8, User20);
        Rating rating16 = new Rating(16, 5, "Buscaba un producto sencillo y económico del estilo y me ha sorprendido mucho, no puedo pedir mas, lo recomiendo.", prod8, User6);
        Rating rating17 = new Rating(17, 4, "Calidad precio lo mejor va bastante bien y no tiene ningun problema lo recomiendo y el envio muy rápido.", prod9, User7);
        Rating rating18 = new Rating(18, 5, "Buscaba un producto sencillo y económico del estilo y me ha sorprendido mucho, no puedo pedir mas, lo recomiendo.", prod9, User8);
        Rating rating19 = new Rating(19, 4, "Calidad precio lo mejor va bastante bien y no tiene ningun problema lo recomiendo y el envio muy rápido.", prod10, User9);
        Rating rating20 = new Rating(20, 5, "Buscaba un producto sencillo y económico del estilo y me ha sorprendido mucho, no puedo pedir mas, lo recomiendo.", prod10, User10);
        Rating rating21 = new Rating(21, 4, "Calidad precio lo mejor va bastante bien y no tiene ningun problema lo recomiendo y el envio muy rápido.", prod11, User11);
        Rating rating22 = new Rating(22, 5, "Buscaba un producto sencillo y económico del estilo y me ha sorprendido mucho, no puedo pedir mas, lo recomiendo.", prod11, User12);
        Rating rating23 = new Rating(23, 4, "Calidad precio lo mejor va bastante bien y no tiene ningun problema lo recomiendo y el envio muy rápido.", prod12, User13);
        Rating rating24 = new Rating(24, 5, "Buscaba un producto sencillo y económico del estilo y me ha sorprendido mucho, no puedo pedir mas, lo recomiendo.", prod12, User14);
        Rating rating25 = new Rating(25, 4, "Calidad precio lo mejor va bastante bien y no tiene ningun problema lo recomiendo y el envio muy rápido.", prod13, User15);
        Rating rating26 = new Rating(26, 5, "Buscaba un producto sencillo y económico del estilo y me ha sorprendido mucho, no puedo pedir mas, lo recomiendo.", prod13, User16);
        Rating rating27 = new Rating(27, 4, "Calidad precio lo mejor va bastante bien y no tiene ningun problema lo recomiendo y el envio muy rápido.", prod14, User17);
        Rating rating28 = new Rating(28, 5, "Buscaba un producto sencillo y económico del estilo y me ha sorprendido mucho, no puedo pedir mas, lo recomiendo.", prod14, User18);
        Rating rating29 = new Rating(29, 4, "Calidad precio lo mejor va bastante bien y no tiene ningun problema lo recomiendo y el envio muy rápido.", prod15, User19);
        Rating rating30 = new Rating(30, 5, "Buscaba un producto sencillo y económico del estilo y me ha sorprendido mucho, no puedo pedir mas, lo recomiendo.", prod15, User20);
        Rating rating31 = new Rating(31, 4, "Calidad precio lo mejor va bastante bien y no tiene ningun problema lo recomiendo y el envio muy rápido.", prod16, User6);
        Rating rating32 = new Rating(32, 5, "Buscaba un producto sencillo y económico del estilo y me ha sorprendido mucho, no puedo pedir mas, lo recomiendo.", prod16, User7);
        Rating rating33 = new Rating(33, 4, "Calidad precio lo mejor va bastante bien y no tiene ningun problema lo recomiendo y el envio muy rápido.", prod17, User8);
        Rating rating34 = new Rating(34, 5, "Buscaba un producto sencillo y económico del estilo y me ha sorprendido mucho, no puedo pedir mas, lo recomiendo.", prod17, User9);
        Rating rating35 = new Rating(35, 4, "Calidad precio lo mejor va bastante bien y no tiene ningun problema lo recomiendo y el envio muy rápido.", prod18, User10);
        Rating rating36 = new Rating(36, 5, "Buscaba un producto sencillo y económico del estilo y me ha sorprendido mucho, no puedo pedir mas, lo recomiendo.", prod18, User11);


        //DataSave
        //user
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
        //rol
        if(rolRepo.findAll().isEmpty()) {
            rolRepo.save(Rol1);
            rolRepo.save(Rol2);
            rolRepo.save(Rol3);
            rolRepo.save(Rol4);
            rolRepo.save(Rol5);
            rolRepo.save(Rol6);
            rolRepo.save(Rol7);
            rolRepo.save(Rol8);
            rolRepo.save(Rol9);
            rolRepo.save(Rol10);
            rolRepo.save(Rol11);
            rolRepo.save(Rol12);
            rolRepo.save(Rol13);
            rolRepo.save(Rol14);
            rolRepo.save(Rol15);
            rolRepo.save(Rol16);
            rolRepo.save(Rol17);
            rolRepo.save(Rol18);
            rolRepo.save(Rol18);
            rolRepo.save(Rol19);
            rolRepo.save(Rol20);
            rolRepo.save(Rol21);
            rolRepo.save(Rol22);
            rolRepo.save(Rol23);
            rolRepo.save(Rol24);
            rolRepo.save(Rol25);
        }
        //Order
        if(orderRepo.findAll().isEmpty()){
            orderRepo.save(order1);
            orderRepo.save(order2);
            orderRepo.save(order3);
            orderRepo.save(order4);
            orderRepo.save(order5);
            orderRepo.save(order6);
            orderRepo.save(order7);
            orderRepo.save(order8);
            orderRepo.save(order9);
            orderRepo.save(order10);
            orderRepo.save(order11);
            orderRepo.save(order12);
            orderRepo.save(order13);
            orderRepo.save(order14);
            orderRepo.save(order15);
            orderRepo.save(order16);
            orderRepo.save(order17);
            orderRepo.save(order18);
            orderRepo.save(order19);
            orderRepo.save(order20);
        }
        //ProducType
        if(productTypeRepo.findAll().isEmpty()){
            productTypeRepo.save(prodType1);
            productTypeRepo.save(prodType2);
            productTypeRepo.save(prodType3);
            productTypeRepo.save(prodType4);
            productTypeRepo.save(prodType5);
            productTypeRepo.save(prodType6);
            productTypeRepo.save(prodType7);
            productTypeRepo.save(prodType8);
            productTypeRepo.save(prodType9);
        }
        //brand
        if(brandRepo.findAll().isEmpty()){
            brandRepo.save(brand1);
            brandRepo.save(brand2);
            brandRepo.save(brand3);
            brandRepo.save(brand4);
            brandRepo.save(brand5);
            brandRepo.save(brand6);
            brandRepo.save(brand7);
            brandRepo.save(brand8);
            brandRepo.save(brand9);
            brandRepo.save(brand10);
            brandRepo.save(brand11);
        }
        //product
        if(productRepo.findAll().isEmpty()) {
			productRepo.save(prod1);
            productRepo.save(prod2);
            productRepo.save(prod3);
            productRepo.save(prod4);
            productRepo.save(prod5);
            productRepo.save(prod6);
            productRepo.save(prod7);
            productRepo.save(prod8);
            productRepo.save(prod9);
            productRepo.save(prod10);
            productRepo.save(prod11);
            productRepo.save(prod12);
            productRepo.save(prod13);
            productRepo.save(prod14);
            productRepo.save(prod15);
            productRepo.save(prod16);
            productRepo.save(prod17);
            productRepo.save(prod18);
        }
        //Rating
        if(ratingRepo.findAll().isEmpty()){
            ratingRepo.save(rating1);
            ratingRepo.save(rating2);
            ratingRepo.save(rating3);
            ratingRepo.save(rating4);
            ratingRepo.save(rating5);
            ratingRepo.save(rating6);
            ratingRepo.save(rating7);
            ratingRepo.save(rating8);
            ratingRepo.save(rating9);
            ratingRepo.save(rating10);
            ratingRepo.save(rating11);
            ratingRepo.save(rating12);
            ratingRepo.save(rating13);
            ratingRepo.save(rating14);
            ratingRepo.save(rating15);
            ratingRepo.save(rating16);
            ratingRepo.save(rating17);
            ratingRepo.save(rating18);
            ratingRepo.save(rating19);
            ratingRepo.save(rating20);
            ratingRepo.save(rating21);
            ratingRepo.save(rating22);
            ratingRepo.save(rating23);
            ratingRepo.save(rating24);
            ratingRepo.save(rating25);
            ratingRepo.save(rating26);
            ratingRepo.save(rating27);
            ratingRepo.save(rating28);
            ratingRepo.save(rating29);
            ratingRepo.save(rating30);
            ratingRepo.save(rating31);
            ratingRepo.save(rating32);
            ratingRepo.save(rating33);
            ratingRepo.save(rating34);
            ratingRepo.save(rating35);
            ratingRepo.save(rating36);
        }
    }
    
}
