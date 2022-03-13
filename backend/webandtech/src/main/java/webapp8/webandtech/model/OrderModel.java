package webapp8.webandtech.model;

import java.util.List;

public class OrderModel {
	private List<Product> Products;
	private Order order;
    
    public List<Product> getProducts() {
        return Products;
    }
    public void setProducts(List<Product> products) {
        Products = products;
    }
    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }
	
}
