package webapp8.webandtech.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import webapp8.webandtech.model.Order;
import webapp8.webandtech.model.User;

public interface OrderRepository extends JpaRepository<Order, Integer>{
    public Page<Order> findAll(Pageable page);
    public List<Order> findAll();
    public Order findByidorder (int idorder);
    public Page<Order> findByIduser(User iduser, Pageable page);
    public Long deleteByIduser(User iduser);
    //public boolean existsidorderByiduser(Integer iduser);

}
