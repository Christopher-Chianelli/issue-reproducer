package org.acme;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MainTest {
    @Test
    void testToXml() {
        MyAnnotatedClass instance = new MyAnnotatedClass();
        instance.setType(MyAnnotatedClass.class);
        Assertions.assertEquals("""
                <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
                <myAnnotatedClass>
                    <type>org.acme.MyAnnotatedClass</type>
                </myAnnotatedClass>
                """, Main.toXml(instance));
    }
}
