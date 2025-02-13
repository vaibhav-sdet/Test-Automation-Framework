package com.automation.core.factory;

import com.automation.core.utils.LoggerUtil;
import org.openqa.selenium.WebDriver;

public class WebDriverFactory {

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    private WebDriverFactory() { } // Prevent instantiation

    public static WebDriver getDriver(BrowserType browser) {
        if (driverThreadLocal.get() == null) {
            LoggerUtil.info("Initializing WebDriver...");
            driverThreadLocal.set(createDriver(browser));
            LoggerUtil.info("WebDriver initialized successfully.");
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
