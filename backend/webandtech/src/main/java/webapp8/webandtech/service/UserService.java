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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import webapp8.webandtech.model.Rol;
import webapp8.webandtech.model.User;
import webapp8.webandtech.repository.RatingRepository;
import webapp8.webandtech.repository.RolRepository;
import webapp8.webandtech.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private RatingRepository ratingrepo;
	
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
	
	public Page<User> getAllUsersPage(){
        return userRepository.findAll(PageRequest.of(0, 10,Sort.by("username").ascending()));
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
	
	// @Modifying
	// public void modifyDataUser(Users user, String username, MultipartFile image, MultipartFile imageProfilePage) throws IOException {
	// 	Users prev = userRepository.findByusername(username).orElseThrow(() -> new NoSuchElementException("User not found"));
	// 	if(image != null){
	// 		if(!image.isEmpty()) {
	// 			prev.setUserimg(BlobProxy.generateProxy(image.getInputStream(), image.getSize()));
	// 		}
	// 	}
		
	// 	if(imageProfilePage != null){
	// 		if(!imageProfilePage.isEmpty()) {
	//     		prev.setImageprofile(BlobProxy.generateProxy(imageProfilePage.getInputStream(), imageProfilePage.getSize()));
	//     	}
	// 	}
	// 	if(user.getUsername() != null) {
	// 		if(!user.getUsername().isEmpty()) {
	// 			prev.setUsername(user.getUsername());
	// 		}
	// 	}
		
	// 	if(user.getEmail() != null) {
	// 		if(!user.getEmail().isEmpty()) {
	// 			prev.setEmail(user.getEmail());
	// 		}
	// 	}
		
	// 	if(user.getName() != null) {
	// 		if(!user.getName().isEmpty()) {
	// 			prev.setName(user.getName());
	// 		}
	// 	}	
			
	// 	if(user.getCity() != null) {
	// 		if(!user.getCity().isEmpty()) {
	// 			prev.setCity(user.getCity());
	// 		}
	// 	}
		
	// 	if(user.getLinkfacebook() != null) {
	// 		if(!user.getLinkfacebook().isEmpty()) {
	// 			prev.setLinkfacebook(user.getLinkfacebook());
	// 		}
	// 	}
		
	// 	if(user.getLinkinstagram() != null) {
	// 		if(!user.getLinkinstagram().isEmpty()) {
	// 			prev.setLinkinstagram(user.getLinkinstagram());
	// 		}
	// 	}
		
	// 	if(user.getLinktwitter() != null) {
	// 		if(!user.getLinktwitter().isEmpty()) {
	// 			prev.setLinktwitter(user.getLinktwitter());
	// 		}
	// 	}
		
	// 	userRepository.save(prev);
	// }

	// @Modifying
	// public void modifyPass(String username, String newpassword) {
	// 	Users prev = userRepository.findByusername(username).orElseThrow(() -> new NoSuchElementException("User not found"));
	// 	prev.setPass(encoder.encode(newpassword));
	// 	userRepository.save(prev);
	// }
	
	// @Transactional
	// public void deleteUser(String username) {
    // 	Users prev = userRepository.findByusername(username).orElseThrow(() -> new NoSuchElementException("User not found"));
	// 	listproductrepo.deleteByIduser(prev);
    // 	relationrepo.deleteByUserone(prev);
    // 	relationrepo.deleteByUsertwo(prev);
    // 	messagerepo.deleteByIduser(prev);
    // 	messagerepo.deleteByIduserto(prev);
    // 	likerepo.deleteByIduser(prev);
    // 	postsrepo.deleteByIduser(prev);
    // 	productrepo.deleteByIduser(prev);
    // 	relationrepo.deleteByUserone(prev);
    // 	rolesRepository.deleteByIduser(prev);
    // 	userRepository.deleteById(prev.getIdusers());
	// }
	
	// public void saveMessage(String to , MessageModel message) {
	// 	Users f =  userRepository.findByusername(message.getFromLogin()).orElseThrow(() -> new NoSuchElementException("User not found"));
    // 	Users t =  userRepository.findByusername(to).orElseThrow(() -> new NoSuchElementException("User not found"));
    // 	Message m = new Message();
    // 	m.setIduser(f);
    // 	m.setIduserto(t);
    // 	m.setMessage(message.getMessage());
    // 	m.setTime(message.getTime());
    // 	messagerepo.save(m);
	// }

	// public List<Message> getChat(String from, String to) {
    // 	Users f = userRepository.findByusername(from).orElseThrow(() -> new NoSuchElementException("User not found"));
	// 	Users t = userRepository.findByusername(to).orElseThrow(() -> new NoSuchElementException("User not found"));
    // 	List<Message> m = messagerepo.findByIduserAndIduserto(t, f);
    // 	List<Message> m2 = messagerepo.findByIduserAndIduserto(f, t);
    // 	m.addAll(m2);
    // 	Collections.sort(m);
    // 	return m;
	// }
	
	// public List<Users> getListMostFollowed(){
	// 	return relationrepo.findMostFollowers(PageRequest.of(0, 5));
	// }
	
	// public List<UsersRelations> getFollowing(String username){
	// 	Users s = userRepository.findByusername(username).orElseThrow(() -> new NoSuchElementException("User not found"));
	// 	return relationrepo.findByuserone(s);
	// }
	
	// public List<UsersRelations> getFollowers(String username){
	// 	Users s = userRepository.findByusername(username).orElseThrow(() -> new NoSuchElementException("User not found"));
	// 	return relationrepo.findByusertwo(s);	
	// }
	
	// public String existsRealtion(Users actual,Users follow) {
	// 	String color = "#53D690";
	// 	UsersRelations s =  relationrepo.findByuseroneAndUsertwo(actual, follow);
    // 	if(s != null) {
    // 		color = "#e44d3a";
    // 	}
    // 	return color;
	// }
	
	// public boolean existsRelationUsers(Users actual,Users follow) {
	// 	UsersRelations s =  relationrepo.findByuseroneAndUsertwo(actual, follow);
	// 	return (s != null);
	// }

	// public void saveRelation(UsersRelations relation) {
	// 	relationrepo.save(relation);
	// }
	
	// public List<UsersRelations> getAllRelations(){
    //     return relationrepo.findAll();
	// }
	
	// public Optional<UsersRelations> getRelations(int id){
    //     return relationrepo.findById(id);
	// }

	// public void deleteRelation(UsersRelations usersRelations) {
	// 	relationrepo.delete(usersRelations);
	// }

	// public void saveUser(Users user) {
	// 	user.setPass(encoder.encode(user.getPass()));
	// 	userRepository.save(user);
	// }

	// public int getRelationId(UsersRelations relation) {
	// 	UsersRelations rela = relationrepo.findByuseroneAndUsertwo(relation.getUserone(), relation.getUsertwo());
	// 	return rela.getIduserrelation();
	// }
	
	// @Transactional
	// public void deleteUserById(int id) {
	// 	Users prev = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found"));
	// 	listproductrepo.deleteByIduser(prev);
    // 	relationrepo.deleteByUserone(prev);
    // 	relationrepo.deleteByUsertwo(prev);
    // 	messagerepo.deleteByIduser(prev);
    // 	messagerepo.deleteByIduserto(prev);
    // 	likerepo.deleteByIduser(prev);
    // 	postsrepo.deleteByIduser(prev);
    // 	productrepo.deleteByIduser(prev);
    // 	relationrepo.deleteByUserone(prev);
    // 	rolesRepository.deleteByIduser(prev);
    // 	userRepository.deleteById(prev.getIdusers());
	// }

	// public boolean existsUser(String username) {
	// 	return userRepository.existsIdusersByUsername(username);
	// }

	// public boolean existEmail(String email) {
	// 	return userRepository.existsIdusersByEmail(email);
	// }

	// public boolean existsUserById(Users iduser) {
	// 	Optional<Users> user = userRepository.findById(iduser.getIdusers());
	// 	return user.isPresent();
	// }
}