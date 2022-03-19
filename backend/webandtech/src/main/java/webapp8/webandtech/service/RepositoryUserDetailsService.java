package webapp8.webandtech.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import webapp8.webandtech.model.Rol;
import webapp8.webandtech.model.User;
import webapp8.webandtech.repository.RolRepository;
import webapp8.webandtech.repository.UserRepository;



@Service
public class RepositoryUserDetailsService implements UserDetailsService {
 @Autowired
 private UserRepository userRepository;
 @Autowired
 private RolRepository rolRepository;
 @Override
 public UserDetails loadUserByUsername(String username)
 throws UsernameNotFoundException {
	 User user =  (userRepository.findByusername(username).orElseThrow(() -> new UsernameNotFoundException("User not found")));
	 List<Rol> r = rolRepository.findByIduser(user);
	 List<GrantedAuthority> roles = new ArrayList<>();
	 for (Rol rol : r) {
		 roles.add(new SimpleGrantedAuthority("ROLE_"+rol.getRol()));
	}
	 return new org.springframework.security.core.userdetails.User(user.getUsername(), 
	 user.getPass(), roles);
 }
}
