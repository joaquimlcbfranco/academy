package com.ctw.workstation.goodbye;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class GoodbyeResourceTest {

    @Test
    void testGoodbyeEndpoint() {

        given()
                .when().get("/goodbye")
                .then()
                .statusCode(200)
                .body(is("Goodbye from Quarkus REST"));

    }

}