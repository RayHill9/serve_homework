package com.brand.service.impl;

import com.brand.dao.BrandDao;
import com.brand.domain.Brand;
import com.brand.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandDao brandDao;

    @Override
    public boolean add(Brand brand) {
        return brandDao.add(brand) > 0;
    }

    @Override
    public boolean update(Brand brand) {
        return brandDao.update(brand) > 0;
    }

    @Override
    public boolean delete(Integer id) {
        return brandDao.delete(id) > 0;
    }

    @Override
    public Brand getById(Integer id) {
        return brandDao.getById(id);
    }

    @Override
    public List<Brand> getAll() {
        return brandDao.getAll();
    }

    @Override
    public Brand getByName(String name) {
        return brandDao.getByName(name);
    }

    @Override
    public void sort() {
        brandDao.sort();
    }
}
