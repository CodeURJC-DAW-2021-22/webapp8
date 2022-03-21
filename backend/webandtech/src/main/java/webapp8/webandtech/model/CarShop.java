package webapp8.webandtech.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class CarShop {
    private List<Product> carShop = new ArrayList<Product>();

    public List<Product> getCarShop() {
        return carShop;
    }

    public void setCarShop(List<Product> carShop) {
        this.carShop = carShop;
    }
    
}
