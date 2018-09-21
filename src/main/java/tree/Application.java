package tree;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;
import tree.dao.MyDao;
import tree.domain.Order;
import tree.service.MyService;
import java.util.List;

public class Application {

    @Test
    public void testMapper(){
        ApplicationContext context=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        SqlSessionTemplate session=context.getBean(SqlSessionTemplate.class);
        MyDao mapper=context.getBean(MyDao.class);
        List<Order>orders=mapper.findAllOrders();
        for(Order order:orders)
            System.out.println(order);
    }

    @Test
    public void testCache(){
        ApplicationContext context=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        MyService service=context.getBean(MyService.class);
        List<Order>orders=service.findAllOrders();
        System.out.println("----------------------------- first -----------------------------");
        for(Order order:orders)
            System.out.println(order);
        System.out.println("----------------------------- second -----------------------------");
        orders=service.findAllOrders();
        for(Order order:orders)
            System.out.println(order);
        service.removeOrdersFromCache();
        System.out.println("---------------------- clear orders' cache ----------------------");
        System.out.println("----------------------------- third -----------------------------");
        orders=service.findAllOrders();
        for(Order order:orders)
            System.out.println(order);
        System.out.println("----------------------------- forth -----------------------------");
        orders=service.findAllOrders();
        for(Order order:orders)
            System.out.println(order);
    }
}
