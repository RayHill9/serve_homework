package com.user.service;

import com.user.req.User;
import com.user.resp.UserLoginResp;

import java.util.List;

public interface UserService {

    User selectByName(String username);

    void register(User user);

    UserLoginResp login(User user);

    User selectById(Integer id);

    int update(User user);

    int delete(Integer id);

    List<User> getAll();

    public void sort();
}
