package com.automation.core.factory;

import com.automation.core.utils.ConfigReader;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class SauceLabsDriverManager implements DriverManager {

    private static final String SAUCE_LABS_URL;
    private static final String SAUCE_USERNAME;
    private static final String SAUCE_ACCESS_KEY;

    static {
        Properties config = ConfigReader.getProperties("saucelabs.properties");
        SAUCE_USERNAME = config.getProperty("sauce.username");
        SAUCE_ACCESS_KEY = config.getProperty("sauce.accessKey");
        SAUCE_LABS_URL = config.getProperty("sauce.url");
    }

    @Override
    public WebDriver createDriver() {
        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName("Windows 11");
        browserOptions.setBrowserVersion("latest");
        Map<String, Object> sauceOptions = new HashMap<>();
        sauceOptions.put("username", SAUCE_USERNAME);
        sauceOptions.put("accessKey", SAUCE_ACCESS_KEY);
        sauceOptions.put("build", "selenium-build-MCIAI");
        sauceOptions.put("name", "GoogleTest");
        browserOptions.setCapability("sauce:options", sauceOptions);


        try {
            return new RemoteWebDriver(new URL(SAUCE_LABS_URL), browserOptions);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid SauceLabs URL", e);
        }
    }
}
