package steps;

import drivers.AppiumDriverManager;
import io.cucumber.java.After;
import org.openqa.selenium.remote.RemoteWebDriver;
import pageObjects.LambdaTestPage;
import pageObjects.Page;

public class Hooks {

    private LambdaTestPage lambdaTestPage;
    private RemoteWebDriver driver = AppiumDriverManager.getDriver();

    public Hooks(LambdaTestPage lambdaTestPage) {
        this.lambdaTestPage = lambdaTestPage;
    }

    @After
    public void closeApp() {
        Page.saveScreenShotPNG();
    }

}
