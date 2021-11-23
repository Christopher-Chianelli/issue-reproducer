package org.acme;

import java.util.function.BiFunction;
import java.util.function.Function;

public class MyClass {
    public static String apply(Function<String, String> mapper) {
        return mapper.apply("1");
    }

    public static String apply(BiFunction<String, String, String> mapper) {
        return mapper.apply("1", "2");
    }

    public static String noOverloadApply(Function<String, String> mapper) {
        return mapper.apply("1");
    }
}
