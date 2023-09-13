package com.order.service.imp;

import com.order.mapper.OrderDao;
import com.order.req.Order;
import com.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderServiceImp implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public void insert(Order order) {
        orderDao.insert(order);
    }

    @Override
    public Order selectById(Integer id) {
        return orderDao.selectById(id);
    }

    @Override
    public int update(Order order) {
        return orderDao.update(order);
    }

    @Override
    public int delete(Integer id) {
        return orderDao.delete(id);
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }

    @Override
    public void sort() {
        orderDao.sort();
    }
}
