package org.acme;

@MyAnnotation
public class MyAnnotatedClass {
    private Class<?> type;

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }
}
