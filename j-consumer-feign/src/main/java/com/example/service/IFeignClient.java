package com.example.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * 远程连接，需要在启动类扫描到此类，并注入Bean： @EnableFeignClients(basePackages = "com.example.*") //开启feign
 */
@FeignClient("PROVIDER-USER")
public interface IFeignClient {

    @RequestMapping(value="/user/sayHello",method = RequestMethod.GET)
    String sayHello();
}
