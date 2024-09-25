package com.mgxlin.flink.cdc.controller;

import com.mgxlin.flink.cdc.Brand;
import com.mgxlin.flink.cdc.ESRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Optional;

@RestController
public class FlinkController {

    @Resource
    private ESRepository esRepository;

    @GetMapping("/{id}")
    public String test(@PathVariable("id") Long id) {
        Optional<Brand> userOptional = esRepository.findById(id);

        return userOptional
                .map(Brand::toString)  // 数据存在时返回
                .orElse("Brand not found");  // 数据不存在时返回
    }
}
