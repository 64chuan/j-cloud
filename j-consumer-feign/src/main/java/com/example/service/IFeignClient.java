package com.example.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient("PROVIDER-USER")
public interface IFeignClient {

    @RequestMapping(value="/user/sayHello",method = RequestMethod.GET)
    String sayHello();
}
