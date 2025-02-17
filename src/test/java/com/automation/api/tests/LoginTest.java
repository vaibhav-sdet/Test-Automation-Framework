package com.automation.api.tests;

import com.automation.businesslogic.api.User;
import com.automation.core.utils.LoggerUtil;
import com.automation.libraries.api.APIResponseValidator;
import com.automation.libraries.api.BaseAPIClient;
import com.automation.libraries.api.RequestHelper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseApiTest {

    private final String basePath = "/api";

    @Test
    public void testGetUsers() {
        Response response = BaseAPIClient.get(requestSpec, basePath);
        APIResponseValidator.validateStatusCode(response, 200);
        LoggerUtil.info(response.asString());
       // Assert.assertFalse(response.jsonPath().getList("$").isEmpty(), "User list is empty!");
    }

    @Test
    public void testCreateUser() {
        User user = new User("John Doe", "johndoe", "john@example.com");

        Response response = BaseAPIClient.post(requestSpec, basePath, RequestHelper.toJson(user));
        APIResponseValidator.validateStatusCode(response, 201);
        Assert.assertEquals(response.jsonPath().getString("name"), "John Doe");
    }

    @Test
    public void testUpdateUser() {
        User updatedUser = new User("John Updated", "johndoe", "john@example.com");

        Response response = BaseAPIClient.put(requestSpec, basePath + "/1", RequestHelper.toJson(updatedUser));
        APIResponseValidator.validateStatusCode(response, 200);
        Assert.assertEquals(response.jsonPath().getString("name"), "John Updated");
    }

    @Test
    public void testDeleteUser() {
        Response response = BaseAPIClient.delete(requestSpec, basePath + "/1");
        APIResponseValidator.validateStatusCode(response, 200);
        Assert.assertTrue(response.asString().isEmpty());
    }
}

