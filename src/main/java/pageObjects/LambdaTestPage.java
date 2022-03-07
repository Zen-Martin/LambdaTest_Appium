package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class LambdaTestPage extends Page{

    @FindBy(id = "addbutton")
    private WebElement addButon;

    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout/android.webkit.WebView/android.view.View/android.widget.ListView/android.view.View[6]")
    private WebElement newElement;

    @FindBy(id = "sampletodotext")
    private WebElement textArea;

    @FindBy(className = "android.view.View")
    private List<WebElement> listOption;

    private String website = "https://lambdatest.github.io/sample-todo-app/";

    private String text = "Test Lead >> Martin";

    private int optionSize = 0;


    private void fillField(WebElement element, String value){
        shortWaitUntil(visibilityOf(element));
        click(element);
        element.clear();
        element.sendKeys(value);
    }

    public void lambdaWebsite(){
        get(website);
    }

    public void sendMessage(){
        fillField(textArea,text);
    }

    public void clickOnAdd(){
        click(addButon);
    }

    public boolean verifyAddingAction(){
        try{
            shortWaitUntil(visibilityOf(newElement));
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
