package com.example.service;


import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("PROVIDER-USER")
public interface HystrixService {

    @RequestMapping(value="/user/sayHello",method= RequestMethod.GET)
    public String sayHello();

    @RequestMapping(value="/user/sayHello11",method= RequestMethod.GET)
    public String hystrixSayError();
}
