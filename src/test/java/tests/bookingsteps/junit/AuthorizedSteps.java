package tests.bookingsteps.junit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import tests.wssearchsteps.cucumber.CucumberSearchSteps;
import utilities.GenerateFakeAddress;
import pages.*;
import tests.bookingsteps.preconditions.Registration;
import tests.bookingsteps.preconditions.SignIn;
import webdriver.Driver;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class AuthorizedSteps {

    static WebDriver driver;
    private static final String BOOKING_URL = "https://www.booking.com/";
    private static final String WISHLIST_URL = "https://www.booking.com/mywishlist.html";
    private static final String password = "123456QW";
    private static final Logger LOGGER = LogManager.getLogger(CucumberSearchSteps.class);
    BookingMainPage bookingMainPage;
    BookingResultsPage bookingResultPage;
    BookingRegistrationPage bookingRegistrationPage;
    BookingMyListPage myListPage;
    static String email;

    @BeforeClass
    public static void baseActions() throws InterruptedException {
        LOGGER.info("Booking authorized testing starts");
        driver = Driver.getWebDriver();
        Driver.setTimeout();
        email = GenerateFakeAddress.generateEmail();
        Registration.performRegistration(driver, email, password, BOOKING_URL);
        LOGGER.info("Registration finished");
        Driver.clearCache();
    }

    @Before
    public void initializePages() {
        bookingMainPage = new BookingMainPage(driver);
        bookingResultPage = new BookingResultsPage(driver);
        bookingRegistrationPage = new BookingRegistrationPage(driver);
        myListPage = new BookingMyListPage(driver);
        LOGGER.info("Pages for testing initialized");
    }

    @Test
    public void registrationTest() {
        bookingMainPage.openMainPage(BOOKING_URL);
        bookingMainPage.goToSignIn();
        SignIn.goThroughLogin(driver, email, password);
        bookingMainPage.openNotification();
        String expected = "Your account hasn't been activated. That means you're missing out on member perks. " +
                "Would you like us to resend the activation email?";
        ArrayList<String> actual = bookingMainPage.listOfNotifications();
        Assert.assertFalse("User wasn't registered", actual.contains(expected));
    }

    @Test
    public void nextTripTest() {
        String expectedColor = "rgb(204, 0, 0)";
        bookingMainPage.openMainPage(BOOKING_URL);
        bookingMainPage.goToSignIn();
        SignIn.goThroughLogin(driver, email, password);
        bookingMainPage.goToSearchResultsWebDriver("Madrid", 1, 0, 2, 30, 35);
        String expectedFirstHotel = bookingResultPage.firstHotelName();
        bookingResultPage.saveFirstItem();
        Driver.waitUntilItemWillBeShown(bookingResultPage.getSavedPopUp());
        bookingResultPage.saveLastItem();
        Driver.waitUntilItemWillBeShown(bookingResultPage.getSavedPopUp());
        String expectedLastHotel = bookingResultPage.lastHotelName();
        String actualColor = bookingResultPage.getColorOfSave();
        bookingResultPage.goToMyListsByLink(WISHLIST_URL);
        Driver.waitUntilItemWillBeShown(myListPage.getViewProperty());
        ArrayList<String> actual = myListPage.getListOfHotels();
        assertEquals("Color isn't red", expectedColor, actualColor);
        Assert.assertTrue("First Hotel is in the List", actual.contains(expectedFirstHotel));
        Assert.assertTrue("Second Hotel is in the List", actual.contains(expectedLastHotel));
    }

    @Test
    public void headerItemsTest() {
        bookingMainPage.openMainPage(BOOKING_URL);
        bookingMainPage.goToSignIn();
        SignIn.goThroughLogin(driver, email, password);
        Driver.waitUntilItemWillBeShown(bookingMainPage.getProfileElement());
        assertEquals("Quantity of items isn't expected", 12,
                bookingMainPage.getQuantityOfItemsInHeader());
    }

    @After
    public void closeDriver() throws InterruptedException {
        Driver.clearCache();
        LOGGER.info("Cache of browser cleared");
    }

    @AfterClass
    public static void stopBrowser() {
        Driver.quitDriver();
        LOGGER.info("Driver closed");
    }
}
