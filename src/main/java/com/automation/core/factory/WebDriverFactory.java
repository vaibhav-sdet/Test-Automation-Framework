package com.automation.core.factory;

import com.automation.core.BrowserType;
import com.automation.core.utils.LoggerUtil;
import org.openqa.selenium.WebDriver;

public class WebDriverFactory {

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<BrowserType> browserThreadLocal = new ThreadLocal<>();

    private WebDriverFactory() { } // Prevent instantiation

    public static void setDriver(BrowserType browser) {
        if (driverThreadLocal.get() == null) {
            LoggerUtil.info("Initializing WebDriver for: " + browser);
            browserThreadLocal.set(browser);
            driverThreadLocal.set(createDriver(browser));
            LoggerUtil.info("WebDriver initialized successfully for: " + browser);
        }
    }

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    private static WebDriver createDriver(BrowserType browserType) {
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

    public static void quitDriver() {
        if (driverThreadLocal.get() != null) {
            LoggerUtil.info("Quitting WebDriver for: " + browserThreadLocal.get());
            driverThreadLocal.get().quit();
            driverThreadLocal.remove();
            browserThreadLocal.remove();
            LoggerUtil.info("WebDriver session cleaned up successfully.");
        }
    }
}
