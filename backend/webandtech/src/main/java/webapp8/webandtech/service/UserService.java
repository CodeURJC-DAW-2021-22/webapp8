package webapp8.webandtech.service;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import webapp8.webandtech.model.Rol;
import webapp8.webandtech.model.User;
import webapp8.webandtech.repository.OrderRepository;
import webapp8.webandtech.repository.RatingRepository;
import webapp8.webandtech.repository.RolRepository;
import webapp8.webandtech.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private RatingRepository ratingRepository;

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	 private UserRepository userRepository;
		
	@Autowired
	private RolRepository rolRepository;

	@Autowired
	private PasswordEncoder encoder;
    
    public Optional<User> getUserId(int id){
		return userRepository.findById(id);
	}
	
	public User getUser(String username) {
		return (userRepository.findByusername(username).orElseThrow(() -> new NoSuchElementException("User not found")));
	}
	
	public Page<User> getUsersPage(Pageable page){
		return userRepository.findAll(page);
	}
	
	public List<User> getAll(){
        return userRepository.findAll();
	}
	
	public List<User> getAllUsersPage(){
        Page<User> userList = userRepository.findAll(PageRequest.of(0, 10,Sort.by("username").ascending()));
        return userList.getContent();
	}
	public List<User> getMoreUsersPage(Pageable page){
        Page<User> userList = userRepository.findAll(page);
        return userList.getContent();
	}
	
	public Page<User> getUsersPage(){
        return userRepository.findByuserprofile(true, PageRequest.of(0, 10,Sort.by("username").ascending()));
	}
	
	public void registerUsers(User user, MultipartFile image) throws IOException {

		if(userRepository.existsIdusersByUsername(user.getUsername())) {
			throw new NoSuchElementException("USERNAME IS TOKEN");
		}else {
			if(image != null) {
				user.setUserimg(BlobProxy.generateProxy(image.getInputStream(), image.getSize()));
			}
			user.setUserprofile(true);
			user.setPass(encoder.encode(user.getPass()));
			userRepository.save(user);
			User use =  (userRepository.findByusername(user.getUsername()).orElseThrow(() -> new NoSuchElementException("User not found")));
			Rol r = new Rol();
			r.setIduser(use);
			r.setRol("USER");
			rolRepository.save(r);
		}

	}

	public Page<User> getCustomers(Pageable page){
		return userRepository.findByuserprofile(true,page);
	}

	public void usernameIsToken(String username) {
		if(userRepository.existsIdusersByUsername(username)) {
			throw new NoSuchElementException("USERNAME IS TOKEN");
		}
	}
	
	@Modifying
	public void modifyDataUser(User user, String username, MultipartFile image) throws IOException {
		User prev = userRepository.findByusername(username).orElseThrow(() -> new NoSuchElementException("User not found"));
		if(image != null){
			if(!image.isEmpty()) {
				prev.setUserimg(BlobProxy.generateProxy(image.getInputStream(), image.getSize()));
			}
		}
		
		if(user.getUsername() != null) {
			if(!user.getUsername().isEmpty()) {
				prev.setUsername(user.getUsername());
			}
		}
		
		if(user.getEmail() != null) {
			if(!user.getEmail().isEmpty()) {
				prev.setEmail(user.getEmail());
			}
		}
		
		if(user.getCompletname() != null) {
			if(!user.getCompletname().isEmpty()) {
				prev.setCompletname(user.getCompletname());
			}
		}	
			
		if(user.getAddress() != null) {
			if(!user.getAddress().isEmpty()) {
				prev.setAddress(user.getAddress());
			}
		}
	
		userRepository.save(prev);
	}

	@Modifying
	public void modifyPass(String username, String newpassword) {
		User prev = userRepository.findByusername(username).orElseThrow(() -> new NoSuchElementException("User not found"));
		prev.setPass(encoder.encode(newpassword));
		userRepository.save(prev);
	}
	
	@Transactional
	public void deleteUser(String username) {
    	User prev = userRepository.findByusername(username).orElseThrow(() -> new NoSuchElementException("User not found"));
    	ratingRepository.deleteByIduser(prev);
    	orderRepository.deleteByIduser(prev);
    	rolRepository.deleteByIduser(prev);
    	userRepository.deleteById(prev.getIduser());
	}
	
	public void saveUser(User user) {
		user.setPass(encoder.encode(user.getPass()));
		userRepository.save(user);
	}
	
	@Transactional
	public void deleteUserById(int id) {
		User prev = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found"));
		ratingRepository.deleteByIduser(prev);
    	orderRepository.deleteByIduser(prev);
    	rolRepository.deleteByIduser(prev);
    	userRepository.deleteById(prev.getIduser());
	}

	public boolean existsUser(String username) {
		return userRepository.existsIdusersByUsername(username);
	}

	public boolean existEmail(String email) {
		return userRepository.existsIdusersByEmail(email);
	}

	public boolean existsUserById(User iduser) {
		Optional<User> user = userRepository.findById(iduser.getIduser());
		return user.isPresent();
	}
}