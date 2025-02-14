package com.automation.pages;

import com.automation.libraries.common.ElementActions;
import com.automation.libraries.web.WebElementActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {
    private WebDriver driver;

    @FindBy(name = "username")
    private WebElement usernameField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;

    @FindBy(xpath = "//img[@class='oxd-userdropdown-img']")
    private WebElement userDropdown;

    public LoginPage(WebDriver driver, WebElementActions actions) {
          super(driver, actions);
    }

    public void enterUsername(String username) {
        actions.typeText(usernameField, username);
    }

    public void enterPassword(String password) {
        actions.typeText(passwordField, password);
    }

    public void clickLogin() {
        actions.click(loginButton);
    }

    public boolean isUserDropdownDisplayed() {
        return actions.isDisplayed(userDropdown);
    }
}
