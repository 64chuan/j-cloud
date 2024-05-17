package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;

/**
 * @author liushichuan
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableBinding(Source.class)
@EnableFeignClients(basePackages = "org.example.feign") //开启feign
public class StreamApplication {

    public static void main(String[] args){
        SpringApplication.run(StreamApplication.class,args);
    }
}
