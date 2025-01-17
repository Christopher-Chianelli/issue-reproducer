package ai.timefold.my.ext.runtime;

import java.util.function.Supplier;

import io.quarkus.runtime.annotations.Recorder;

@Recorder
public class MyExtRecorder {
    final MyExtRuntimeConfig config;

    public MyExtRecorder(final MyExtRuntimeConfig config) {
        this.config = config;
    }

    public Supplier<MyBean> myBeanSupplier() {
        return () -> {
            if (config.failOnStartup().orElse(false)) {
                throw new IllegalArgumentException("Fail on startup");
            }
            return new MyBean();
        };
    }
}
