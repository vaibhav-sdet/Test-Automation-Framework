package com.automation.core.factory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
//import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import com.automation.core.MobilePlatform;

public class MobileDriverManager {

    private static final ThreadLocal<AppiumDriver> driverThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<MobilePlatform> platformThreadLocal = new ThreadLocal<>();

    private MobileDriverManager() { } // Prevent instantiation

    public static void setDriver(MobilePlatform platform) {
        if (driverThreadLocal.get() == null) {
            platformThreadLocal.set(platform);
            driverThreadLocal.set(createDriver(platform));
        }
    }

    public static AppiumDriver getDriver() {
        return driverThreadLocal.get();
    }

    private static AppiumDriver createDriver(MobilePlatform platform) {
        DesiredCapabilities caps = new DesiredCapabilities();
        AppiumDriver driver = null;

        switch (platform) {
            case ANDROID:
                caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
                caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
                caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
                caps.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "/apps/android-app.apk");

                try {
                    driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
                } catch (MalformedURLException e) {
                    throw new RuntimeException("Invalid Appium server URL", e);
                }
                break;

            case IOS:
                caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
                caps.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone Simulator");
                caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
                caps.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir") + "/apps/ios-app.app");

                try {
                    driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
                } catch (MalformedURLException e) {
                    throw new RuntimeException("Invalid Appium server URL", e);
                }
                break;

            default:
                throw new IllegalArgumentException("Unsupported platform: " + platform);
        }

        return driver;
    }

    public static void quitDriver() {
        if (driverThreadLocal.get() != null) {
            driverThreadLocal.get().quit();
            driverThreadLocal.remove();
            platformThreadLocal.remove();
        }
    }
}
