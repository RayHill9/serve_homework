package com.user.service.imp;

import com.user.mapper.UserDao;
import com.user.req.User;
import com.user.resp.UserLoginResp;
import com.user.service.UserService;
import com.user.utils.CopuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userdao;
    @Override
    public void register(User user) {
        if(ObjectUtils.isEmpty(selectByName(user.getUsername())))
            userdao.register(user);
    }

    @Override
    public User selectByName(String loginName){
        return userdao.selectByName(loginName);
    }
    @Override
    public UserLoginResp login(User user) {
        User user1 = selectByName(user.getUsername());
        if(ObjectUtils.isEmpty(user1)||!user.getPassword().equals(user1.getPassword())){
            return null;
        }
        else {
            return CopuUtil.copy(user1, UserLoginResp.class);
        }
    }

    @Override
    public User selectById(Integer id) {
        return userdao.selectById(id);
    }

    @Override
    public int update(User user) {
        return userdao.update(user);
    }


    @Override
    public int delete(Integer id) {
        return userdao.delete(id);
    }

    @Override
    public List<User> getAll() {
        return userdao.getAll();
    }

    @Override
    public void sort() {
        userdao.sort();
    }
}
