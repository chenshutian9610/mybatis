package tree.dao;

import org.apache.ibatis.annotations.*;
import tree.domain.Customer;
import tree.domain.Order;

import java.util.List;

public interface MyDao {
    /**
     *  这里引用 CustomerMapper.xml 定义的 resultMap
     *  因为注解定义的 @Results 只能用于嵌套查询，不能用于嵌套结果
     */
    @Select("select c.*, o.id oid, o.goods, o.price from tb_customer c,tb_order o where c.id=o.c_id")
    @ResultMap("customer.customerMap")
    public List<Customer>findAllCustomers();

    @Select("select*from tb_order")
    @Results(id="orderMap",value={
            @Result(property = "id",column = "id",id = true),
            @Result(property = "goods",column = "goods"),
            @Result(property = "price",column = "price"),
            @Result(property = "customer",column="c_id",one=@One(select = "customer.findById"))
    })
    public List<Order>findAllOrders();

    @Select("select c.* from tb_customer c,tb_order o where c.id=o.c_id")
    public Customer getCustomer(Order order);
}