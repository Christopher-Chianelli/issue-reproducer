package org.acme;

import jakarta.xml.bind.JAXB;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.StringWriter;

@RestController
@RequestMapping("/test")
public class MyController {
    private final MyAnnotatedClass classInstance;

    public MyController(MyAnnotatedClass classInstance) {
        this.classInstance = classInstance;
    }

    @GetMapping(produces = MediaType.TEXT_PLAIN_VALUE)
    public String helloWorld() {
        // Do a pointless marshall to show JAXB cause a crash
        StringWriter stringWriter = new StringWriter();
        JAXB.marshal(classInstance, stringWriter);
        if (!stringWriter.toString().isEmpty()) {
            return classInstance.getType().getSimpleName();
        } else {
            throw new IllegalStateException();
        }
    }
}
