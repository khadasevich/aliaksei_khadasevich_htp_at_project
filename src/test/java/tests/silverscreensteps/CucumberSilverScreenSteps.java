package tests.silverscreensteps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.AfterClass;
import org.junit.Assert;
import pages.silverscreen.SilverScreenMainPage;
import testobjects.bookingobjects.RegisteredTestUserJsonObject;
import tests.silverscreensteps.silverscreentesttools.SilverScreenTestTools;
import utilities.MyJsonParser;
import webdriver.Driver;

import java.io.IOException;

public class CucumberSilverScreenSteps {

    private static final String SILVER_SCREEN_URL = "https://silverscreen.by";
    static SilverScreenMainPage silverScreenMainPage;

    @Before
    public static void initializeDriver() {
        Driver.initDriver();
        Driver.setTimeout();
        silverScreenMainPage = new SilverScreenMainPage(Driver.getWebDriver());
    }

    @Given("I open an app")
    public void iOpenAnApp() {
        silverScreenMainPage.openMainPage(SILVER_SCREEN_URL);
    }

    @When("I search for \"(.*)\" word")
    public void iSearchForThingWord(String name) {
        silverScreenMainPage.hoverSearch();
        silverScreenMainPage.inputSearchRequest(name);

    }

    @When("I login")
    public void iLogin() throws IOException {
        RegisteredTestUserJsonObject registeredTestUserJsonObject = MyJsonParser.getTestUser();
        loginToSilverScreen(registeredTestUserJsonObject.getEmail(), registeredTestUserJsonObject.getPassword());
    }

    @Then("I see the list of movie items")
    public void iSeeTheListOfMovieItems() {
        Driver.waitUntilItemWillBeShown(silverScreenMainPage.getResultHeader());
        boolean results = SilverScreenTestTools.elementIsDisplayed(silverScreenMainPage.getResultHeader());
        Assert.assertTrue("Results of search aren't displayed", results);
    }

    @Then("each item name or description contains \"(.*)\"")
    public void eachItemNameOrDescriptionContainsThing(String name) {
        boolean actual = SilverScreenTestTools.checkSearchResults(silverScreenMainPage.getArrayOfSearchResults(), name);
        Assert.assertTrue("Check results of search", actual);
    }

    @Then("I can see Red Carpet Club \"(.*)\" in upper right corner")
    public void iCanSeeRedCarpetClubInUpperRightCorner(String level) {
        Assert.assertTrue("Level of user isn't expected", silverScreenMainPage.getTextOfHeader().contains(level));
        silverScreenMainPage.logoutFromSilverScreen();
    }

    @After
    public static void tearDown() {
        Driver.quitDriver();
    }

    public void loginToSilverScreen(String email, String password) {
        silverScreenMainPage.hoverSignIn();
        silverScreenMainPage.inputEmail(email);
        silverScreenMainPage.inputPassword(password);
        silverScreenMainPage.clickLogin();
    }
}
