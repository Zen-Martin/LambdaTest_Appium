package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pageObjects.LambdaTestPage;

public class LambdaTestSteps {

    private LambdaTestPage lambdaTestPage;

    public LambdaTestSteps(LambdaTestPage lambdaTestPage) {
        this.lambdaTestPage = lambdaTestPage;
    }

    @Given("User is lambda test website")
    public void userIsLambdaTestWebsite() {
        lambdaTestPage.lambdaWebsite();
    }

    @When("User add text")
    public void userAddText() {
        lambdaTestPage.sendMessage();
    }

    @And("User click on add button")
    public void userClickOnAddButton() {
        lambdaTestPage.clickOnAdd();
    }

    @Then("User should see text added")
    public void userShouldSeeDifferenceInTitle() {
        Assert.assertEquals(lambdaTestPage.verifyAddingAction(),true);
    }
}
