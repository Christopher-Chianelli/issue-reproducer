package com.example.demo;

import java.util.List;

public class MyBean {
    private final String name;
    private final int age;

    public MyBean(MyBeanRecord myBeanRecord) {
        this.name = myBeanRecord.name();
        this.age = myBeanRecord.age();
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
