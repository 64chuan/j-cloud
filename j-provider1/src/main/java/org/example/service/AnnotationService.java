package org.example.service;

import org.example.dto.MyAnnottation;
import org.springframework.stereotype.Service;

@Service
public class AnnotationService {

    /**
     * 面向切面编程
     *
     */
    @MyAnnottation(role = "admin")
    public void add(){
        System.out.println("这是我的ADD方法，面向切面编程。");
    }

    @MyAnnottation(role = "error")
    public void error() {
        System.out.println("这是我的ERROR方法，面向切面编程。");

        throw new RuntimeException("这是我的ERROR方法，ERROR！");
    }
}
