package com.user.mapper;

import com.user.req.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDao {

    @Insert("insert into table_user (username,password,email,phone,position) values(#{username},#{password},#{email},#{phone},'店员')")
    public void register(User user);

    @Select("select * from table_user where username = #{username}")
    public User selectByName(String username);

    @Select("select * from table_user where id = #{id}")
    public User selectById(Integer id);

    @Update("update table_user set username = #{username}, email = #{email}, phone = #{phone}, position = #{position} where id = #{id}")
    public int update(User user);

    @Delete("delete from table_user where id = #{id}")
    public int delete(Integer id);

    @Select("select * from table_user")
    public List<User> getAll();

    @Update("SET @auto_id = 0; UPDATE table_user SET id =( @auto_id := @auto_id + 1 );")
    public void sort();
}
