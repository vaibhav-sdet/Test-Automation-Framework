package com.automation.libraries.web;

import com.automation.libraries.common.ElementActions;
import com.automation.libraries.common.WaitUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class WebElementActions extends ElementActions {

    private WaitUtils waitUtils;

    // Constructor
    public WebElementActions(WebDriver driver, WaitUtils waitUtils) {
        super(driver );
        this.waitUtils = waitUtils;
    }

    // Overriding click to add explicit wait
    @Override
    public void click(WebElement element) {
        super.click(element);  // Use base class click method with JS fallback
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
