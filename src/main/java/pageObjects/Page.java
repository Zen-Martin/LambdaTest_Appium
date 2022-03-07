package pageObjects;

import config.ConfigPropertiesReader;
import config.Properties;
import config.SystemPropertiesReader;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.function.Function;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class Page {

    private static final Logger LOG = LogManager.getLogger(Page.class);

    protected RemoteWebDriver driver;
    protected Actions actions;

    protected WebDriverWait shortWait;
    protected WebDriverWait wait;
    protected WebDriverWait longWait;
    protected WebDriverWait loadingWait;

    public static final long SHORT_WAIT = 5;
    public static final long WAIT = 10;
    public static final long LONG_WAIT = 15;
    public static final long LOADING_WAIT = 20;
    protected JavascriptExecutor js;

    protected SystemPropertiesReader systemPropertiesReader;
    protected ConfigPropertiesReader configPropertiesReader;

    public Page() {
        driver = Properties.APPIUM_DRIVER_MANAGER.getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        actions = new Actions(driver);
        js = (JavascriptExecutor) driver;
        wait        = new WebDriverWait(driver, WAIT);
        shortWait   = new WebDriverWait(driver, SHORT_WAIT);
        longWait    = new WebDriverWait(driver, LONG_WAIT);
        loadingWait = new WebDriverWait(driver, LOADING_WAIT);
        systemPropertiesReader = Properties.SYSTEM_PROPERTIES_READER;
        configPropertiesReader = Properties.CONFIG_PROPERTIES_READER;
    }

    /**
     * Wait until the condition in the function is satisfied
     * @param isTrue the condition
     * @param <V> the condition return type
     * @return true if the condition is satisfied, false if the condition hasn't been satisfied in the given time
     */
    protected <V> boolean shortWaitUntil(Function<? super WebDriver, V> isTrue) {
        try {
            shortWait.until(isTrue);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    protected void click(WebElement element) {
        if(!shortWaitUntil(visibilityOf(element)))
            LOG.warn("The element is not yet visible");
        if(!shortWaitUntil(elementToBeClickable(element)))
            LOG.warn("The element is not yet clickable");
        element.click();
    }


    public void get(String url){
        driver.get(url);
    }

    public int findElement(List<WebElement> list, String element){
        int occurence = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getText().contains(element)) {
                occurence++;
                break;
            }
        }
        return occurence;
    }

    @Attachment(value = "screenshot", type = "image/png")
    public static void saveScreenShotPNG(){
        Allure.addAttachment("screenshot", new ByteArrayInputStream(((TakesScreenshot) Properties
                .APPIUM_DRIVER_MANAGER.getDriver())
                .getScreenshotAs(OutputType.BYTES)));
    }

}
