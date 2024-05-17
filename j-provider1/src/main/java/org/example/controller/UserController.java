package org.example.controller;

import org.example.dto.UserDto;
import org.example.service.AnnotationService;
import org.example.service.UserRetryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserRetryService userRetryService;

    @Autowired
    private AnnotationService annotationService;

    /**
     * 面向切面编程
     * @return 返回
     */
    @GetMapping("/sayAnnotation")
    public String sayAnnotation(){
        annotationService.add();
        return " Hello ! Annotation. ";
    }

    /**
     * 面向切面编程
     * @return 返回
     */
    @GetMapping("/sayAnnotationError")
    public String sayAnnotationError(){
        try{
            annotationService.error();
        } catch (Exception e){
            System.out.println(" catch Exception  " + e.getMessage());
        }
        return " Hello ! Annotation.error ";
    }


    @GetMapping("/sayHello")
    public String sayHello(){
        try {
            long t = System.currentTimeMillis();
            System.out.println(" start doing something ...   ");

            // 重试接口，内部已处理异常，外部将捕获不到此异常
            userRetryService.call(t);
        } catch (Exception e) {
            System.out.println(" sayHello Exception ...   ");
        }

        return "Hello! I am Provider1.";
    }

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping(value="/helloPort")
    public String helloPort(){
        return " Hello server port：" + serverPort;
    }

    /**
     * 获取用户详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/loadUser/{id}", method = RequestMethod.GET)
    public UserDto detail(@PathVariable Long id){
        UserDto userDto = new UserDto();
        userDto.setId(id);
        userDto.setAvatar("hhh");
        userDto.setNickname("Hello! Hello!");
        return userDto;
    }
}
