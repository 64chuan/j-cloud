package org.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

    private final RestTemplate resttemplate;

    public ConsumerController(RestTemplate resttemplate) {
        this.resttemplate = resttemplate;
    }

    @GetMapping("/hello")
    public String hello(){
        System.out.println(" ConsumerController ---hello  ");
        return resttemplate.getForObject("http://provider-user/user/sayHello",String.class);
    }
}
