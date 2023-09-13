package com.user.controller;


import com.user.req.User;
import com.user.resp.CommonResp;
import com.user.resp.UserLoginResp;
import com.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Component
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("register")
    public CommonResp<Object> register(@RequestBody User user) {
        CommonResp<Object> resp = new CommonResp<>();
        userService.register(user);
        userService.sort();
        return resp;
    }

    @PostMapping("login")
    public CommonResp<Object> login(@RequestBody User user) {
        CommonResp<Object> resp = new CommonResp<>();
        UserLoginResp userLogin = userService.login(user);
        resp.setContent(userLogin);
        if (userLogin == null)
            resp.setSuccess(false);
        return resp;
    }

    @DeleteMapping("/{id}")
    public CommonResp<Object> delete(@PathVariable Integer id) {
        CommonResp<Object> resp = new CommonResp<>();
        boolean flag = userService.delete(id) != 0;
        userService.sort();
        resp.setSuccess(flag);
        return resp;
    }

    @GetMapping
    public CommonResp<Object> getAll() {
        List<User> userList = userService.getAll();
        CommonResp<Object> resp = new CommonResp<>();
        resp.setSuccess(true);
        resp.setContent(userList);
        return resp;
    }

    @GetMapping("/{id}")
    public CommonResp<Object> getById(@PathVariable Integer id) {
        CommonResp<Object> resp = new CommonResp<>();
        User user = userService.selectById(id);
        resp.setSuccess(true);
        resp.setContent(user);
        return resp;
    }

    @PutMapping("/{id}")
    public CommonResp<Object> Edit(@PathVariable Integer id, @RequestBody User user) {
        CommonResp<Object> resp = new CommonResp<>();
        user.setId(id);
        boolean flag = userService.update(user) != 0;
        resp.setSuccess(flag);
        return resp;
    }

    @GetMapping("/in/{name}")
    public User getByName(@PathVariable String name) {
        return userService.selectByName(name);
    }


}
