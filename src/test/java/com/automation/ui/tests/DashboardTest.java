package com.automation.ui.tests;

import com.automation.core.utils.LoggerUtil;
import com.automation.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DashboardTest extends BaseTest {
    private LoginPage loginPage;

    @BeforeMethod
    public void navigateToLoginPage() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        loginPage = new LoginPage(driver, elementActions);
    }

    @Test
    public void testDashboard() {
        LoggerUtil.info("Dashboard Test Started...");
        loginPage.enterUsername("Admin");
        loginPage.enterPassword("admin123");
        loginPage.clickLogin();
        Assert.assertTrue(loginPage.isUserDropdownDisplayed(), "Login Failed");

    }

}

