package org.acme.my.ext.it;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class MyExtResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
                .when().get("/my-ext")
                .then()
                .statusCode(200)
                .body(is("Hello my-ext"));
    }
}
