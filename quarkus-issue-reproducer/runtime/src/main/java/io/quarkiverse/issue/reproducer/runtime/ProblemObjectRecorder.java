package io.quarkiverse.issue.reproducer.runtime;

import java.util.function.Supplier;

import io.quarkus.runtime.annotations.Recorder;

@Recorder
public class ProblemObjectRecorder {
    public Supplier<ProblemObject> problemObjectSupplier(final ProblemObject base) {
        return () -> base;
    }
}
