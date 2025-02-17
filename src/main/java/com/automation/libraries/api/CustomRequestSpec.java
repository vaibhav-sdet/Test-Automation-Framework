package com.automation.libraries.api;


import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

/**
 * Defines reusable request specifications (headers, timeouts, base URI).
 */

public class CustomRequestSpec {

    public static RequestSpecification getDefaultSpec() {
        return given()
                .relaxedHTTPSValidation()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json");
    }

    public static RequestSpecification getAuthSpec(String token) {
        return getDefaultSpec()
                .header("Authorization", "Bearer " + token);
    }

    public static RequestSpecification getRequestSpecification() {
        return RestAssured.given()
                .baseUri("https://randomuser.me")
                .header("Content-Type", "application/json")
                .log().all();
    }
}

