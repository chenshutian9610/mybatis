package tree.domain;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private int id;
    private String name;
    private List<Order> orders=new ArrayList<Order>();

    public List<Order> getOrders() {
        return orders;
    }

    @Override
    public String toString() {
        return id+" "+name;
    }
}
