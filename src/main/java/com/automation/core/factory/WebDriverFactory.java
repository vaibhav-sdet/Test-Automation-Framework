package com.automation.core.factory;

import org.openqa.selenium.WebDriver;

public class WebDriverFactory {

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    private WebDriverFactory() { } // Prevent instantiation

    public static WebDriver getDriver(BrowserType browser) {
        if (driverThreadLocal.get() == null) {
            driverThreadLocal.set(createDriver(browser));
        }
        return driverThreadLocal.get();
    }
    public static WebDriver createDriver(BrowserType browserType) {
        DriverManager driverManager;

        switch (browserType) {
            case CHROME:
                driverManager = new ChromeDriverManager();
                break;
            case FIREFOX:
                driverManager = new FirefoxDriverManager();
                break;
            case EDGE:
                driverManager = new EdgeDriverManager();
                break;
            case SAUCELABS:
                driverManager = new SauceLabsDriverManager();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserType);
        }
        return driverManager.createDriver();
    }
}
