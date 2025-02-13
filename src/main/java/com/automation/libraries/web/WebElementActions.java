package com.automation.libraries.web;

import com.automation.libraries.common.ElementActions;
import com.automation.libraries.common.WaitUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class WebElementActions extends ElementActions {

    private WaitUtils waitUtils;

    // Constructor
    public WebElementActions(WebDriver driver, WaitUtils waitUtils) {
        super(driver,waitUtils );
        this.waitUtils = new WaitUtils(driver, 10);
    }

    // Overriding click to add explicit wait
    @Override
    public void click(WebElement element) {
        waitUtils.waitForClickability(element).click();
    }

    // Web-Specific Actions
    public void selectFromDropdown(WebElement dropdown, String value) {
        Select select = new Select(dropdown);
        select.selectByVisibleText(value);
    }

    public boolean isElementEnabled(WebElement element) {
        return waitUtils.waitForVisibility(element).isEnabled();
    }
}
