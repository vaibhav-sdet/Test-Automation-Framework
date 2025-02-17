package com.automation.ui.tests;

import com.automation.core.factory.BrowserType;
import com.automation.core.factory.WebDriverFactory;
import com.automation.libraries.common.WaitUtils;
import com.automation.libraries.web.WebElementActions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class BaseTest {
    protected WebDriver driver;
    protected WebElementActions elementActions;
    protected WaitUtils waitUtils;
    private BrowserType browserType;


    @BeforeMethod
    @Parameters("browser")
    public void setup(String browser) {
        BrowserType browserType = BrowserType.valueOf(browser.toUpperCase());
        WebDriverFactory.setDriver(browserType);  // Set driver per test thread
        driver = WebDriverFactory.getDriver();    // Get driver from ThreadLocal storage

        waitUtils = new WaitUtils(driver, 10);
        elementActions = new WebElementActions(driver, waitUtils);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void teardown() {
        WebDriverFactory.quitDriver();  // Ensures driver is closed properly for each test method
    }
}
