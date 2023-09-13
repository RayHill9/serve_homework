package com.order.mapper;

import com.order.req.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface OrderDao {

    @Insert("insert into table_order (brand,username,quantity,price) values(#{brand},#{username},#{quantity},#{price})")
    public void insert(Order order);

    @Select("select * from table_order where id = #{id}")
    public Order selectById(Integer id);
    @Update("update table_order set brand = #{brand}, username = #{username}, quantity = #{quantity}, price = #{price} where id = #{id}")
    public int update(Order order);
    @Delete("delete from table_order where id = #{id}")
    public int delete(Integer id);

    @Select("select * from table_order")
    public List<Order> getAll();

    @Update("SET @auto_id = 0; UPDATE table_order SET id =( @auto_id := @auto_id + 1 );")
    public void sort();
}
