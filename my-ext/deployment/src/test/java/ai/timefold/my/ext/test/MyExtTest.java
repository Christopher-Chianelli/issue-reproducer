package ai.timefold.my.ext.test;

import jakarta.inject.Inject;

import ai.timefold.my.ext.runtime.MyBean;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import io.quarkus.test.QuarkusUnitTest;

public class MyExtTest {

    // Start unit test with your extension loaded
    @RegisterExtension
    static final QuarkusUnitTest unitTest = new QuarkusUnitTest()
            .overrideRuntimeConfigKey("my-ext.fail-on-startup", "true")
            .setArchiveProducer(() -> ShrinkWrap.create(JavaArchive.class))
            .assertException(throwable -> {
                Assertions.assertInstanceOf(IllegalArgumentException.class, throwable);
                Assertions.assertEquals("Fail on startup", throwable.getMessage());
            });

    @Inject
    MyBean myBean;

    @Test
    public void testStartupFails() {
        Assertions.fail();
    }
}
