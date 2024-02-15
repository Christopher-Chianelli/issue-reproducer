package com.example.demo;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class MyController {
    private final MyBean bean;

    public MyController(MyBean bean) {
        this.bean = bean;
    }

    @GetMapping(produces = MediaType.TEXT_PLAIN_VALUE)
    public String helloWorld() {
        return "Bean name is " + bean.getName() + " with age " + bean.getAge();
    }
}
