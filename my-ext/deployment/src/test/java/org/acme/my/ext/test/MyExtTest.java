package org.acme.my.ext.test;

import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Inject;

import org.acme.my.ext.MyGenericInterface;
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
        .setArchiveProducer(() -> ShrinkWrap.create(JavaArchive.class));

    @Inject
    MyGenericInterface<AtomicInteger, String> myGenericInterface;

    @Test
    public void writeYourOwnUnitTest() {
        // Write your unit tests here - see the testing extension guide https://quarkus.io/guides/writing-extensions#testing-extensions for more information
        Assertions.assertNull(myGenericInterface.extractNumber("0"), "Basic Test");
    }
}
