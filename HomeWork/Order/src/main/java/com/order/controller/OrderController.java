package com.order.controller;


import com.order.client.BrandClient;
import com.order.client.UserClient;
import com.order.req.Brand;
import com.order.req.User;
import com.order.resp.CommonResp;
import com.order.req.Order;
import com.order.service.OrderService;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserClient userClient;

    @Autowired
    private BrandClient brandClient;


    @PostMapping
    public CommonResp<Object> insert(@RequestBody Order order) {
        try {
            CommonResp<Object> resp = new CommonResp<>();
            if (brandClient.getByName(order.getBrand()) == null) {
                resp.setSuccess(false);
                return resp;
            }
            Brand brand = brandClient.getByName(order.getBrand());
            int price = brand.getMoney();
            if (userClient.getByName(order.getUsername()) == null) {
                resp.setSuccess(false);
                return resp;
            }
            if (brand.getStock() < order.getQuantity()) {
                resp.setMessage("库存不足,");
                resp.setSuccess(false);
                return resp;
            } else {
                brand.setStock(brand.getStock() - order.getQuantity());
                brandClient.update(brand.getId(), brand);
            }

            order.setPrice(price * order.getQuantity());
            orderService.insert(order);
            orderService.sort();
            return resp;
        } catch (FeignException e) {
            CommonResp<Object> resp = new CommonResp<>();
            resp.setSuccess(false);
            return resp;
        }
    }

    @DeleteMapping("/{id}")
    public CommonResp<Object> delete(@PathVariable Integer id) {
        CommonResp<Object> resp = new CommonResp<>();
        boolean flag = orderService.delete(id) != 0;
        orderService.sort();
        resp.setSuccess(flag);
        return resp;
    }

    @GetMapping
    public CommonResp<Object> getAll() {
        List<Order> orderList = orderService.getAll();
        CommonResp<Object> resp = new CommonResp<>();
        resp.setSuccess(true);
        resp.setContent(orderList);
        return resp;
    }

    @GetMapping("/User/{username}")
    public CommonResp<Object> getUser(@PathVariable String username) {
        CommonResp<Object> resp = new CommonResp<>();
        User user = userClient.getByName(username);
        resp.setSuccess(true);
        resp.setContent(user);
        return resp;
    }

    @GetMapping("/{id}")
    public CommonResp<Object> getById(@PathVariable Integer id) {
        CommonResp<Object> resp = new CommonResp<>();
        Order order = orderService.selectById(id);
        resp.setSuccess(true);
        resp.setContent(order);
        return resp;
    }

    @PutMapping("/{id}")
    public CommonResp<Object> Edit(@PathVariable Integer id, @RequestBody Order order) {
        CommonResp<Object> resp = new CommonResp<>();
        order.setId(id);
        int price = brandClient.getByName(order.getBrand()).getMoney();
        order.setPrice(price * order.getQuantity());
        boolean flag = orderService.update(order) != 0;
        resp.setSuccess(flag);
        return resp;
    }
}
