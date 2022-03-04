package webapp8.webandtech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import webapp8.webandtech.model.Rol;
import webapp8.webandtech.model.User;



public interface RolRepository extends JpaRepository<Rol, Integer>{
	public List<Rol> findByiduser(User iduser);
	public List<Rol> findByrol(String rol);
	public Long deleteByIduser(User iduser);
}
