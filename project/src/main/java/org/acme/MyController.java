package org.acme;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class MyController {
    private final MyAnnotatedClass classInstance;

    public MyController(MyAnnotatedClass classInstance) {
        this.classInstance = classInstance;
    }

    @GetMapping(produces = MediaType.TEXT_PLAIN_VALUE)
    public String helloWorld() {
        return classInstance.getClass().getSimpleName();
    }
}
