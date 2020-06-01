package tests.silverscreensteps;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.AfterClass;
import org.junit.Assert;
import pages.booking.BookingMainPage;
import pages.booking.BookingMyListPage;
import pages.booking.BookingResultsPage;
import pages.silverscreen.SilverScreenMainPage;
import tests.silverscreensteps.silverscreentesttools.SilverScreenTestTools;
import webdriver.Driver;

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

    @Then("I see the list of movie items")
    public void iSeeTheListOfMovieItems() {
        Driver.waitUntilItemWillBeShown(silverScreenMainPage.getResultHeader());
        boolean results = SilverScreenTestTools.elementIsDisplayed(silverScreenMainPage.getResultHeader());
        Assert.assertTrue("Results of search aren't displayed", results);
    }

    @Then("each item name or description contains \"(.*)\"")
    public void eachItemNameOrDescriptionContainsThing(String name) {
        boolean actual = SilverScreenTestTools.checkSearchResults(silverScreenMainPage.getArrayOfSearchResults(), name);
        Assert.assertTrue("Check results of serach", actual);
    }

    @AfterClass
    public static void tearDown() {
        Driver.quitDriver();
    }

}
