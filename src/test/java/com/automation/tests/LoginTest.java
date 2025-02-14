package com.automation.tests;

import com.automation.core.factory.BrowserType;
import com.automation.core.factory.WebDriverFactory;
//import com.automation.core.utils.LoggerUtil;
import com.automation.core.utils.LoggerUtil;
import com.automation.libraries.common.ElementActions;
import com.automation.libraries.common.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest {
    private WebDriver driver;
    private ElementActions elementActions;
    private WaitUtils waitUtils;

    @BeforeClass
    public void setup() {
        driver = WebDriverFactory.getDriver(BrowserType.CHROME); // Change as needed
        waitUtils = new WaitUtils(driver, 10);
        elementActions = new ElementActions(driver); // Initializing ElementActions
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    @Test
    public void testLogin() {
        LoggerUtil.info("Test Started...");

        WebElement usernameField = waitUtils.waitForVisibility(By.name("username"));
        WebElement passwordField = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));


        // Perform login actions using ElementActions
        elementActions.typeText(usernameField, "Admin");
        elementActions.typeText(passwordField, "admin123");
        elementActions.click(loginButton);

        // Validate login success

        boolean loginSuccess = elementActions.isDisplayed(waitUtils.waitForVisibility((By.xpath("//img[@class='oxd-userdropdown-img']"))));
        Assert.assertTrue(loginSuccess, "Login Failed");
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

