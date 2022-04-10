package webapp8.webandtech.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import webapp8.webandtech.model.Order;
import webapp8.webandtech.model.User;
import webapp8.webandtech.repository.OrderRepository;

@Service
public class OrderService {
    @Autowired
	private OrderRepository orderRepository;

    public Order getOrder(int idorder) {
		return (orderRepository.findByidorder(idorder));
	}
    public List<Order> getUserOrders(User user){
        System.out.println(user);
        Page<Order> orders = orderRepository.findByIduser(user,PageRequest.of(0, 10, Sort.by("idorder").descending()));
        System.out.println(orders);
        return orders.getContent();
	}
    public Page<Order> getMoreUserOrders(User user,Pageable page){
        Page<Order> orders = orderRepository.findByIduser(user,page);
        return orders;
	}
    public List<Order> getAllOrders(){
        Page<Order> orders = orderRepository.findAll(PageRequest.of(0, 10, Sort.by("idorder").descending()));
        return orders.getContent();
	}
    public Page<Order> getMoreAllOrders(Pageable page){
        Page<Order> orders = orderRepository.findAll(page);
        return orders;
	}
    public List<Order> getAll(){
        return orderRepository.findAll();
	}
    public void saveOrder(Order order) throws IOException {
		orderRepository.save(order);
	}
}
