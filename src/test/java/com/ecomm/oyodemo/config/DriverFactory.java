package com.ecomm.oyodemo.config;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;

import static com.ecomm.oyodemo.config.DriverType.valueOf;


public class DriverFactory {

    private RemoteWebDriver driver;
    private DriverType selectedDriverType;

    public DriverFactory() {
        DriverType driverType = DriverType.FIREFOX;
        String browser = System.getProperty("browser", driverType.toString()).toUpperCase();
        try {
            driverType = DriverType.valueOf(browser);
        } catch (IllegalArgumentException ignored) {
            System.err.println("Unknown driver specified, defaulting to '" + driverType + "'...");
        } catch (NullPointerException ignored) {
            System.err.println("No driver specified, defaulting to '" + driverType + "'...");
        }
        selectedDriverType = driverType;
    }

    public RemoteWebDriver getDriver() throws Exception {
        if (null == driver) {
            instantiateWebDriver(selectedDriverType);
        }

        return driver;
    }

    public RemoteWebDriver getStoredDriver() {
        return driver;
    }

    public void quitDriver() {
        if (null != driver) {
            driver.quit();
            driver = null;
        }
    }

    private void instantiateWebDriver(DriverType driverType) throws MalformedURLException {
        //TODO add in a real logger instead of System.out
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        driver = driverType.getWebDriverObject(desiredCapabilities);

    }
}

