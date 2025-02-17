package com.automation.libraries.api;

import io.restassured.response.Response;
import org.testng.Assert;

/**
 * Provides reusable methods for validating response codes, response time, and schema validation.
 */

public class APIResponseValidator {

    public static void validateStatusCode(Response response, int expectedStatus) {
        Assert.assertEquals(response.getStatusCode(), expectedStatus, "Unexpected status code.");
    }

    public static void validateResponseTime(Response response, long maxTimeMillis) {
        Assert.assertTrue(response.getTime() < maxTimeMillis, "Response time exceeded limit.");
    }

    public static void validateResponseContains(Response response, String expectedText) {
        Assert.assertTrue(response.asString().contains(expectedText), "Response does not contain expected text.");
    }
}

