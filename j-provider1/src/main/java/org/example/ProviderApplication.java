package org.example;

import org.example.dto.UserMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.retry.annotation.EnableRetry;

@EnableDiscoveryClient
@SpringBootApplication
@EnableRetry
public class ProviderApplication {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args){
        SpringApplication.run(ProviderApplication.class,args);
        System.out.println(" This is Provider1 port " );
    }
}
