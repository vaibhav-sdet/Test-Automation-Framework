package com.automation.api.tests;

import com.automation.libraries.api.CustomRequestSpec;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

public class BaseApiTest {
    protected RequestSpecification requestSpec;

    @BeforeClass
    public void setup() {
        requestSpec = CustomRequestSpec.getRequestSpecification();
    }
}

