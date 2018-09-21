package tree;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tree.dao.MyDao;
import tree.domain.Customer;
import tree.domain.Order;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Main {
    private SqlSession session;

    @BeforeMethod
    public void init() throws IOException {
        InputStream in= Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(in);
        session=factory.openSession();
    }

    @Test
    public void findOrdersByCustomer(){
        //  指令格式为 namespace.id
        Customer customer=session.selectOne("customer.findByName","triski");
        List<Order>orders=customer.getOrders();
        for(Order order:orders){
            order.setCustomer(customer);
            System.out.println(order);
        }
    }

    @Test
    public void testGetMapper(){
        //  mybatis 推荐使用 getMapper 来操作数据库,而不是直接使用映射文件
        MyDao mapper=session.getMapper(MyDao.class);
        List<Order>orders=mapper.findAllOrders();
        for(Order order:orders)
            System.out.println(order);
    }
}
