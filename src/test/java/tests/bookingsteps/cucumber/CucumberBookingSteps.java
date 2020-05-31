package tests.bookingsteps.cucumber;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import pages.BookingMainPage;
import pages.BookingMyListPage;
import pages.BookingResultsPage;
import testobjects.TestUser;
import tests.bookingsteps.preconditions.SignIn;
import utilities.MyJsonParser;
import webdriver.Driver;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class CucumberBookingSteps {

    static WebDriver driver;
    private static final String BOOKING_URL = "https://www.booking.com/";
    private static final String WISHLIST_URL = "https://www.booking.com/mywishlist.html";
    static BookingMainPage bookingMainPage;
    static BookingResultsPage bookingResultPage;
    static BookingMyListPage myListPage;
    int expected;
    int actual;
    String expectedFirstHotel;
    String expectedLastHotel;

    @Before
    public static void initializeDriver() {
        driver = Driver.getWebDriver();
        Driver.setTimeout();
        bookingMainPage = new BookingMainPage(driver);
        bookingResultPage = new BookingResultsPage(driver);
        myListPage = new BookingMyListPage(driver);
    }

    @Given("Open Main Page")
    public void openMainPage() {

        bookingMainPage.openMainPage(BOOKING_URL);
    }

    @When("I search trip with param: {string}, {int}, {int}, {int}, {int}, {int} and filter it by max price and sort by price")
    public void iSearchTripWithParamRoom(String city, Integer rooms, Integer children, Integer adults, Integer start, Integer end) {
        bookingMainPage.goToSearchResultsWebDriver(city, rooms, children, adults, start, end);
        expected = bookingResultPage.getMaxFilterPrice();
        bookingResultPage.sortPrice();
        Driver.waitUntilElementIsVisible(bookingResultPage.getOverlay());
        actual = bookingResultPage.getActualMinSortingPrice(end - start);
    }

    @Then("I expect price for a night of first in the list > than max price from filter")
    public void iExpectPriceForANight() throws InterruptedException {
        Assert.assertTrue("Price for a night isn't expected", expected < actual || expected == actual);
        Driver.clearCache();
    }

    @When("I search trip with param: {string}, {int}, {int}, {int}, {int}, {int} and filter result by min price")
    public void iSearchTripWithParamAndFilterResultByMinPrice(String city, Integer rooms, Integer children, Integer adults, Integer start, Integer end) {
        bookingMainPage.goToElementBuilder(city, rooms, children, adults, start, end);
        expected = bookingResultPage.getMinFilterPrice();
        Driver.waitUntilElementIsVisible(bookingResultPage.getOverlay());
        actual = bookingResultPage.getActualTopElementPrice(end - start);
    }

    @When("I login")
    public void iLogin() throws IOException {
        TestUser testUser = MyJsonParser.getTestUser();
        bookingMainPage.goToSignIn();
        SignIn.goThroughLogin(driver, testUser.getEmail(), testUser.getPassword());
    }

    @And("Search trip with param: {string}, {int}, {int}, {int}, {int}, {int} and select hotels")
    public void searchTripWithParamAndSelectHotels(String city, Integer rooms, Integer children, Integer adults, Integer start, Integer end) {
        bookingMainPage.goToSearchResultsWebDriver(city, rooms, children, adults, start, end);
        expectedFirstHotel = bookingResultPage.firstHotelName();
        bookingResultPage.saveFirstItem();
        Driver.waitUntilItemWillBeShown(bookingResultPage.getSavedPopUp());
        bookingResultPage.saveLastItem();
        Driver.waitUntilItemWillBeShown(bookingResultPage.getSavedPopUp());
        expectedLastHotel = bookingResultPage.lastHotelName();
        bookingResultPage.goToMyListsByLink(WISHLIST_URL);
    }

    @Then("I expect price of first item in the list < then value in the filter")
    public void iExpectPriceOfFirstItemInTheListThenValueInTheFilter() throws InterruptedException {
        Assert.assertTrue("Price for a night isn't expected", expected > actual);
        Driver.clearCache();
    }

    @When("I search trip with param: {string}, {int}, {int}, {int}, {int}, {int} and change background if the item")
    public void iSearchTripWithParamAndChangeBackgroundIfTheItem(String city, Integer rooms, Integer children, Integer adults, Integer start, Integer end) throws InterruptedException {
        bookingMainPage.goToSearchResultsWebDriver(city, rooms, children, adults, start, end);
        bookingResultPage.filterHotelsByStars();
        Driver.waitUntilElementIsVisible(bookingResultPage.getOverlay());
        bookingResultPage.javascriptFunction();
    }

    @Then("I expect that item has expected color")
    public void iExpectThatItemHasExpectedColor() throws InterruptedException {
        String expectedColor = "rgba(255, 0, 0, 1)";
        assertEquals("Color isn't red", expectedColor, bookingResultPage.getColorOfAddress());
        Driver.clearCache();
    }

    @Then("List of notifications doesn't contain reminder about registration")
    public void listOfNotificationsDoesnTContainReminderAboutRegistration() throws InterruptedException {
        bookingMainPage.openNotification();
        String expected = "Your account hasn't been activated. That means you're missing out on member perks. " +
                "Would you like us to resend the activation email?";
        ArrayList<String> actual = bookingMainPage.listOfNotifications();
        Assert.assertFalse("User wasn't registered", actual.contains(expected));
        Driver.clearCache();
    }

    @Then("These hotels appear in the wishlist")
    public void theseHotelsAppearInTheWishlist() throws InterruptedException {
        Driver.waitUntilItemWillBeShown(myListPage.getViewProperty());
        ArrayList<String> actual = myListPage.getListOfHotels();
        Assert.assertTrue("First Hotel is in the List", actual.contains(expectedFirstHotel));
        Assert.assertTrue("Second Hotel is in the List", actual.contains(expectedLastHotel));
        Driver.clearCache();
    }

    @Then("I expect to see right items in header")
    public void iExpectToSeeRightItemsInHeader() {
        Driver.waitUntilItemWillBeShown(bookingMainPage.getProfileElement());
        assertEquals("Quantity of items isn't expected", 12,
                bookingMainPage.getQuantityOfItemsInHeader());
    }
}
