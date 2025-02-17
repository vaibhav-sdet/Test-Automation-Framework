package com.automation.libraries.api;


import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

/**
 * Acts as a base class for all API clients.
 * Initializes RestAssured and common request configurations
 */




public class BaseAPIClient {

    public static Response get(RequestSpecification requestSpec, String endpoint) {
        return given().spec(requestSpec).when().get(endpoint).then().extract().response();
    }

    public static Response post(RequestSpecification requestSpec, String endpoint, String body) {
        return given().spec(requestSpec).body(body).when().post(endpoint).then().extract().response();
    }

    public static Response put(RequestSpecification requestSpec, String endpoint, String body) {
        return given().spec(requestSpec).body(body).when().put(endpoint).then().extract().response();
    }

    public static Response delete(RequestSpecification requestSpec, String endpoint) {
        return given().spec(requestSpec).when().delete(endpoint).then().extract().response();
    }
}


