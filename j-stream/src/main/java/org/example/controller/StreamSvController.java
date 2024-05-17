package org.example.controller;

import org.example.dto.UserDto;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/stream")
@RestController
public class StreamSvController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getUser",method = RequestMethod.GET)
    public String getUser(long id){
        return userService.load(id).toString();
    }

    @RequestMapping(value = "/saveUser",method = RequestMethod.GET)
    public String saveUser(){
        UserDto userDto = new UserDto();
        userDto.setNickname("insertUser");
        userDto.setAvatar("first source");
        return userService.save(userDto).toString();
    }

    @RequestMapping(value = "/deleteUser",method = RequestMethod.GET)
    public String deleteUser(){
        userService.delete(1234L);
        return "删除用户  ID：" + 1234L;
    }
}
