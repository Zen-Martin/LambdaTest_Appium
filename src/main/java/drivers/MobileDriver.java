package drivers;

import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class MobileDriver implements Driver {

    private final RemoteWebDriver driver;

    String username = "merlinmagicien1";
    String accessKey = "TQ165SmqwKDzvcIpbqPRq51FSZ1V9F4Kdg98ZOnv4S3O3B9SMu";

    String cloudURL = "https://" + username + ":" + accessKey + "@hub.lambdatest.com/wd/hub";

    public MobileDriver(String platform) {
        driver = cloudMobileDriver(platform);
    }

    private RemoteWebDriver cloudMobileDriver(String platform) {

        try {
            return new RemoteWebDriver(new URL(cloudURL), getAndroidCapabilities());
        } catch (MalformedURLException e) {
            System.out.println("Invalid grid URL");
            return null;
        }
}

    @Override
    public RemoteWebDriver getDriver() {
        return driver;
    }

    @Override
    public void closeDriver() {
        driver.close();
    }



}
