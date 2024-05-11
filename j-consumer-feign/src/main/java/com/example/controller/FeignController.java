package com.example.controller;

import com.example.service.IFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignController {

    @Autowired
    private IFeignClient feignClient;

    @RequestMapping("/helloFeign")
    public String feignSayHello(){
        System.out.println("FeignController feignSayHello ");
        return feignClient.sayHello();
    }
}
