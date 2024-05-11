package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.retry.annotation.EnableRetry;

// @EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
@EnableRetry
public class ProviderApplication {


    public static void main(String[] args){
        SpringApplication.run(ProviderApplication.class,args);
        System.out.println(" This is Provider1 port " );
    }

}
