package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * feign
 * @author lsc
 * @since 2024.4.28
 * @version 1.0
 */
@SpringBootApplication
@EnableEurekaClient //表明这是一个eureka客户端
@EnableFeignClients(basePackages = "com.example.*") //开启feign
public class FeignApplication {
    public static void main(String[] args){
        SpringApplication.run(FeignApplication.class,args);

    }
}
