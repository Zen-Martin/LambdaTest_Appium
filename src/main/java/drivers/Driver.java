package drivers;

import config.Properties;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public interface Driver {

    Logger LOG = LogManager.getLogger(Driver.class);

    RemoteWebDriver getDriver();

    void closeDriver();

    default DesiredCapabilities getAndroidCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Properties.SYSTEM_PROPERTIES_READER.platformName);
        //capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, Properties.SYSTEM_PROPERTIES_READER.platformVersion);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, Properties.SYSTEM_PROPERTIES_READER.deviceName);
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 5);
        capabilities.setCapability("network", true); // To enable network logs
        capabilities.setCapability("visual", true); // To enable step by step screenshot
        capabilities.setCapability("video", true); // To enable video recording
        capabilities.setCapability("console", true);
        if(Properties.SYSTEM_PROPERTIES_READER.onBrowser) {
            capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
        } else {
            if(Properties.CONFIG_PROPERTIES_READER.app == null || Properties.CONFIG_PROPERTIES_READER.app.isEmpty()) {
                capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, Properties.CONFIG_PROPERTIES_READER.appPackage);
                capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, Properties.CONFIG_PROPERTIES_READER.appActivity);
            } else {
                capabilities.setCapability(MobileCapabilityType.APP, Properties.CONFIG_PROPERTIES_READER.app);
            }
        }
        return capabilities;
    }

    default DesiredCapabilities getIOSDesiredCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Properties.SYSTEM_PROPERTIES_READER.platformName);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, Properties.SYSTEM_PROPERTIES_READER.platformVersion);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, Properties.SYSTEM_PROPERTIES_READER.deviceName);
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 5);
        capabilities.setCapability("network", true); // To enable network logs
        capabilities.setCapability("visual", true); // To enable step by step screenshot
        capabilities.setCapability("video", true); // To enable video recording
        capabilities.setCapability("console", true);
        if(Properties.SYSTEM_PROPERTIES_READER.onBrowser) {
            capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Safari");
        } else {
            if(Properties.CONFIG_PROPERTIES_READER.app == null || Properties.CONFIG_PROPERTIES_READER.app.isEmpty()) {
                //capabilities.setCapability(IOSMobileCapabilityType.BUNDLE_ID, Properties.CONFIG_PROPERTIES_READER.bundleID);
            } else {
                capabilities.setCapability(MobileCapabilityType.APP, Properties.CONFIG_PROPERTIES_READER.app);
            }
        }
        return capabilities;
    }

    default URL getAppiumServer(String url) {
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            LOG.error("The URL of Appium Server is invalid: {}", url);
            throw new RuntimeException(e);
        }
    }

}
