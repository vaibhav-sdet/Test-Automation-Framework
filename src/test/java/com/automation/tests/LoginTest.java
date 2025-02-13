package com.automation.tests;

import com.automation.core.factory.BrowserType;
import com.automation.core.factory.WebDriverFactory;
import com.automation.core.utils.LoggerUtil;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest {
    private WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = WebDriverFactory.getDriver(BrowserType.CHROME); // Change as needed
        driver.get("https://www.google.com/");
    }

    @Test
    public void testLogin() {
        LoggerUtil.info("Executing login test...");
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

