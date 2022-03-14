package webapp8.webandtech.service;

import java.io.IOException;
import java.sql.Blob;
import java.util.List;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import webapp8.webandtech.model.Order;
import webapp8.webandtech.model.Product;
import webapp8.webandtech.model.Statistics;
import webapp8.webandtech.repository.OrderRepository;
import webapp8.webandtech.repository.ProductRepository;
import webapp8.webandtech.repository.RolRepository;
import webapp8.webandtech.repository.UserRepository;


@Service
public class AdminService {

    @Autowired
	private ProductRepository productrepo;
    @Autowired
	private UserRepository userRepository;
    @Autowired
	private RolRepository rolesRepository;
    @Autowired
	private OrderRepository orderRepository;
    
    public Model getData(Model model) {
		List<Product> p = productrepo.findAll();
		List<Order> orders = orderRepository.findAll();
         int components = 0;
         int peripherals = 0;
         int phone = 0;
         int moderBoard = 0;
         int processor = 0;
         int hardDisck = 0;
         int graphicCard = 0;
         int keyBoard = 0;
         int mouse = 0;
         int monitors = 0;
         int headPhones = 0;
         int tusers = userRepository.findAll().size();
         int users = rolesRepository.findByrol("USER").size();
         int admins = rolesRepository.findByrol("ADMIN").size();
         float earnings = 0;
         int products = p.size();
         int tproduct = p.size();
        for (Order order : orders){
            earnings = earnings + order.getPrice();
        }

         if(tproduct > 0) {
			components = ( productrepo.findByproductcategory("Componente").size() * 100)/tproduct;
			peripherals = ( productrepo.findByproductcategory("Periferico").size() * 100)/tproduct;
			phone = ( productrepo.findByproductcategory("telefono").size() * 100)/tproduct;
			moderBoard = ( productrepo.findByproductType("Placa base").size() * 100)/tproduct;
			processor = ( productrepo.findByproductType("Procesador").size() * 100)/tproduct;
			hardDisck = ( productrepo.findByproductType("Disco duro").size() * 100)/tproduct;
			graphicCard = ( productrepo.findByproductType("Tarjeta grafica").size() * 100)/tproduct;
			keyBoard = ( productrepo.findByproductType("Teclado").size() * 100)/tproduct;
			mouse = ( productrepo.findByproductType("Raton").size() * 100)/tproduct;
			monitors = ( productrepo.findByproductType("Monitor").size() * 100)/tproduct;
			headPhones = ( productrepo.findByproductType("Auriculares").size() * 100)/tproduct;
			
            	
		}
		
		
		model.addAttribute("numproduct", products);
		
		model.addAttribute("userPor", (((users-admins)*100)/tusers));
		model.addAttribute("adminPor", ((admins*100)/tusers));
		
		model.addAttribute("sumstore", earnings);
		model.addAttribute("sumuser", userRepository.findAll().size());
		
		model.addAttribute("components", components);
		model.addAttribute("peripherals", peripherals);
		model.addAttribute("phone", phone);
		model.addAttribute("moderBoard", moderBoard);
		model.addAttribute("processor", processor);
		model.addAttribute("hardDisck", hardDisck);
		model.addAttribute("graphicCard", graphicCard);
		model.addAttribute("keyBoard", keyBoard);
		model.addAttribute("mouse", mouse);
		model.addAttribute("monitors", monitors);
		model.addAttribute("headPhones", headPhones);
		return model;
	}
	
    
}
