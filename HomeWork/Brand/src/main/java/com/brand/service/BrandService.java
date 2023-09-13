package com.brand.service;

import com.brand.domain.Brand;

import java.util.List;

public interface BrandService {
    /**
     * 保存
     *
     * @param brand
     * @return
     */
    boolean add(Brand brand);

    /**
     * 修改
     *
     * @param brand
     * @return
     */
    public boolean update(Brand brand);

    /**
     * 按id删除
     *
     * @param id
     * @return
     */
    public boolean delete(Integer id);

    /**
     * 按id查询
     *
     * @param id
     * @return
     */
    public Brand getById(Integer id);
    public Brand getByName(String name);
    /**
     * 查询全部
     *
     * @return
     */
    public List<Brand> getAll();

    public void sort();
}
