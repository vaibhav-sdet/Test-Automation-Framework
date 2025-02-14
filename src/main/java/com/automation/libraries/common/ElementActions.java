package com.automation.libraries.common;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class ElementActions implements BaseElementActions {

    protected WebDriver driver;
    protected WaitUtils waitUtils;


    // Constructor for Dependency Injection
    public ElementActions(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver, 10); // Default timeout 10s
    }

    @Override
    public void click(WebElement element) {
        try {
            waitUtils.waitForClickability(element).click();
        } catch (Exception e) {
            // Fallback to JavaScript click if normal click fails
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
        }
    }

    @Override
    public void typeText(WebElement element, String text) {
        waitUtils.waitForVisibility(element).sendKeys(text);
    }

    @Override
    public String getText(WebElement element) {
        return waitUtils.waitForVisibility(element).getText();
    }

    @Override
    public boolean isDisplayed(WebElement element) {
        waitUtils.waitForVisibility(element);
        return element.isDisplayed();
    }

    // Additional Common Actions
    public void hoverOverElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    public void scrollIntoView(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
