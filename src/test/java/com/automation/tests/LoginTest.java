package com.automation.tests;

import com.automation.core.factory.BrowserType;
import com.automation.core.factory.WebDriverFactory;
import com.automation.core.utils.LoggerUtil;
import com.automation.libraries.common.ElementActions;
import com.automation.libraries.common.WaitUtils;
import com.automation.libraries.web.WebElementActions;
import com.automation.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private WebElementActions elementActions;
    private WaitUtils waitUtils;

    @BeforeClass
    public void setup() {
        driver = WebDriverFactory.getDriver(BrowserType.CHROME); // Change as needed
        waitUtils = new WaitUtils(driver, 10);
        elementActions = new WebElementActions(driver, waitUtils); // Initializing ElementActions
        loginPage = new LoginPage(driver, elementActions);
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    @Test
    public void testLogin() {
        LoggerUtil.info("Test Started...");
        loginPage.enterUsername("Admin");
        loginPage.enterPassword("admin123");
        loginPage.clickLogin();

        // Validate login success
        Assert.assertTrue(loginPage.isUserDropdownDisplayed(), "Login Failed");

    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

