package webapp8.webandtech.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import webapp8.webandtech.model.User;



public interface UserRepository extends JpaRepository<User, Integer>{
	public Optional<User> findByIduser(int iduser);
	public Page<User> findAll(Pageable page);
	public List<User> findAll();
	public Optional<User> findByusername(String username);
	public Optional<User> findByemail(String email);
	public List<User> findByuserprofile(boolean userprofile);
	public Page<User> findByuserprofile(boolean userprofile, Pageable page);
	boolean existsIdusersByUsername(String username);
	boolean existsIdusersByEmail(String email);

}
