package org.acme;

import java.util.Collection;
import java.util.stream.Collectors;

public class MyClass {
    public static String joinCollection(Collection<Object> collection) {
        return collection.stream().map(Object::toString).collect(Collectors.joining(", "));
    }

    public static String getClassSimpleName(Class<?> clazz) {
        return clazz.getSimpleName();
    }
}
