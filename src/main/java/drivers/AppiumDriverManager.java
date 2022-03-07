package drivers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AppiumDriverManager {

    private static final AppiumDriverManager INSTANCE = new AppiumDriverManager();

    private static ThreadLocal<MobileDriver> driver = new ThreadLocal<>();

    private AppiumDriverManager() {
    }

    public static AppiumDriverManager getInstance() {
        return INSTANCE;
    }

    public static RemoteWebDriver getDriver() {
        return driver.get().getDriver();
    }

    public static void setDriver(String platform) {
        driver.set(DriverFactory.getDriver(platform));
    }

}
