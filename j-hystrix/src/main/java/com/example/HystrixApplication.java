package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * feign
 * @author lsc
 * @since 2024.4.28
 * @version 1.0
 */
@SpringCloudApplication
@EnableEurekaClient //表明这是一个eureka客户端
@EnableFeignClients //开启feign
@EnableHystrix
public class HystrixApplication {
    public static void main(String[] args){
        SpringApplication.run(HystrixApplication.class,args);

    }
}
