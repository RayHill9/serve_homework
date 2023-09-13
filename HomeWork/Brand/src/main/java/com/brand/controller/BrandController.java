package com.brand.controller;

import com.brand.domain.Brand;
import com.brand.resp.BrandResp;
import com.brand.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @PostMapping
    public BrandResp<Object> add(@RequestBody Brand brand) {
        BrandResp<Object> brandResp = new BrandResp<>();
        boolean flag = brandService.add(brand);
        brandService.sort();
        brandResp.setSuccess(flag);
        return brandResp;
    }

    @PutMapping("/{id}")
    public BrandResp<Object> update(@PathVariable Integer id, @RequestBody Brand brand) {

        BrandResp<Object> brandResp = new BrandResp<>();
        brand.setId(id);
        boolean flag = brandService.update(brand);
        brandResp.setSuccess(flag);
        return brandResp;
    }

    @DeleteMapping("/{id}")
    public BrandResp<Object> delete(@PathVariable Integer id) {
        BrandResp<Object> brandResp = new BrandResp<>();
        boolean flag = brandService.delete(id);
        brandService.sort();
        brandResp.setSuccess(flag);
        return brandResp;
    }

    @GetMapping("/{id}")
    public BrandResp<Object> getById(@PathVariable Integer id) {
        BrandResp<Object> brandResp = new BrandResp<>();
        Brand brand = brandService.getById(id);
        brandResp.setSuccess(true);
        brandResp.setContent(brand);
        return brandResp;
    }

    @GetMapping
    public BrandResp<Object> getAll() {
        BrandResp<Object> brandResp = new BrandResp<>();
        List<Brand> brandList = brandService.getAll();
        brandResp.setSuccess(true);
        brandResp.setContent(brandList);
        return brandResp;
    }

    @GetMapping("/in/{name}")
    public Brand getByName(@PathVariable String name) {
        return brandService.getByName(name);
    }


}
