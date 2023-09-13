package com.order.client;


import com.order.req.User;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("UserService")
public interface UserClient {

    @GetMapping("/user/in/{name}")
    User getByName(@PathVariable String name);

}
