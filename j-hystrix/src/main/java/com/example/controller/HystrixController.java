package com.example.controller;

import com.example.service.HystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HystrixController {
    @Autowired
    private HystrixService hystrixService;

    @RequestMapping("/helloHystrix")
    @HystrixCommand(fallbackMethod = "helloFallback")
    public String helloHystrix(){

        System.out.println("HystrixController helloHystrix ");
        return hystrixService.sayHello();
    }

    public String helloFallback(){
        System.out.println("HystrixController helloFallback ");
        return "... helloFallback ...";
    }


    @RequestMapping("/helloError")
    @HystrixCommand(fallbackMethod = "helloErrorback")
    public String hystrixSayError(){

        System.out.println("HystrixController hystrixSayError ");
        return hystrixService.hystrixSayError();
    }

    public String helloErrorback(){
        System.out.println("HystrixController helloErrorback ");
        return "... helloErrorback ...";
    }
}
