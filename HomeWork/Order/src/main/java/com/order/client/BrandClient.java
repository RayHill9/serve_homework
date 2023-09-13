package com.order.client;


import com.order.req.Brand;

import com.order.resp.BrandResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("BrandService")
public interface BrandClient {
    @GetMapping("/brand/in/{name}")
    Brand getByName(@PathVariable String name);

    @PutMapping("/brand/{id}")
    public BrandResp<Object> update(@PathVariable Integer id, @RequestBody Brand brand);

}
