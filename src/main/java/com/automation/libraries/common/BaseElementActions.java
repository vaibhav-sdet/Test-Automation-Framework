package com.automation.libraries.common;

import org.openqa.selenium.WebElement;

public interface BaseElementActions {
    void click(WebElement element);
    void typeText(WebElement element, String text);
    String getText(WebElement element);
    boolean isDisplayed(WebElement element);
}