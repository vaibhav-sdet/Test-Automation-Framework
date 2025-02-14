package com.automation.pages;

import com.automation.libraries.common.ElementActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    protected WebDriver driver;
    protected ElementActions actions;

    public BasePage(WebDriver driver, ElementActions actions) {
        this.driver = driver;
        this.actions = actions;
        PageFactory.initElements(driver, this);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }
}

