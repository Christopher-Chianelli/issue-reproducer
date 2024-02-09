package org.acme;

import jakarta.xml.bind.JAXB;
import java.io.StringWriter;

public class Main {
    public static void main(String[] args) {
        MyAnnotatedClass instance = new MyAnnotatedClass();
        instance.setType(MyAnnotatedClass.class);
        System.out.println(toXml(instance));
    }

    public static String toXml(MyAnnotatedClass instance) {
        StringWriter out = new StringWriter();
        JAXB.marshal(instance, out);
        return out.toString();
    }
}