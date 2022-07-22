package io.quarkiverse.issue.reproducer.it;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class IssueReproducerResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
                .when().get("/issue.reproducer")
                .then()
                .statusCode(200)
                .body(is("true"));
    }
}
