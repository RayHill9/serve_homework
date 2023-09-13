package com.order.service;

import com.order.req.Order;

import java.util.List;

public interface OrderService {
    public void insert(Order order);
    public Order selectById(Integer id);
    public int update(Order order);
    public int delete(Integer id);
    public List<Order> getAll();

    public void sort();
}
