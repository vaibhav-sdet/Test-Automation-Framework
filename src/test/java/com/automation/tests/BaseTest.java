package com.automation.tests;

import com.automation.core.factory.BrowserType;
import com.automation.core.factory.WebDriverFactory;
import com.automation.libraries.common.WaitUtils;
import com.automation.libraries.web.WebElementActions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseTest {
    protected WebDriver driver;
    protected WebElementActions elementActions;
    protected WaitUtils waitUtils;
    private BrowserType browserType;


    @BeforeClass
    @Parameters("browser")
    public void setup(String browser) {
        driver = WebDriverFactory.getDriver(BrowserType.valueOf(browser.toUpperCase())); // Change as needed
        waitUtils = new WaitUtils(driver, 10);
        elementActions = new WebElementActions(driver, waitUtils);
        driver.manage().window().maximize();
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
