package org.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/cc")
@RestController
public class ConfigController {


    @RequestMapping("/tt")
    public String getRepository(){
        return "111";
    }

}
