package com.brand.dao;

import com.brand.domain.Brand;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BrandDao {

    @Insert("insert into tb_brands (brand,name,stock,money) values(#{brand},#{name},#{stock},#{money})")
    @Results({
            @Result(column = "name", property = "name")
    })
    public int add(Brand brand);

    @Update("update tb_brands set brand = #{brand}, name = #{name}, stock = #{stock} ,money = #{money} where id = #{id}")
    @Results({
            @Result(column = "name", property = "name")
    })
    public int update(Brand brand);

    @Delete("delete from tb_brands where id = #{id}")
    @Results({
            @Result(column = "name", property = "name")
    })
    public int delete(Integer id);
    @Select("select * from tb_brands where name = #{name}")
    @Results({
            @Result(column = "name", property = "name")
    })
    public Brand getByName(String name);
    @Select("select * from tb_brands where id = #{id}")
    @Results({
            @Result(column = "name", property = "name")
    })
    public Brand getById(Integer id);

    @Select("select * from tb_brands")
    @Results({
            @Result(column = "name", property = "name")
    })
    public List<Brand> getAll();

    @Update("SET @auto_id = 0; UPDATE tb_brands SET id =( @auto_id := @auto_id + 1 );")
    public void sort();
}
