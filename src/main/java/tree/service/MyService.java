package tree.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import tree.dao.MyDao;
import tree.domain.Customer;
import tree.domain.Order;

import java.util.List;

@Service
public class MyService {
    @Autowired private MyDao dao;

    @Cacheable(cacheNames = "orders")
    public List<Order> findAllOrders(){
        return dao.findAllOrders();
    }

    @Caching(cacheable = {
            @Cacheable(cacheNames = "byPrice",condition="#order.price>25"),
            @Cacheable(cacheNames = "byCID",condition="#order.c_id>5")
    })
    public Customer getCustomer(Order order){
        return dao.getCustomer(order);
    }

    @CacheEvict(cacheNames="orders",beforeInvocation=true,allEntries = true)
    public void removeOrdersFromCache(){}
}
