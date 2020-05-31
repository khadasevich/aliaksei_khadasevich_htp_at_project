package tests.bookingsteps.junit;

import org.junit.*;
import pages.booking.WebMainPage;
import pages.booking.WebResultsPage;
import utilities.LogTool;
import webdriver.Driver;

import static org.junit.Assert.assertEquals;

public class UnauthorizedSteps {

    private static final String BOOKING_URL = "https://www.booking.com/";
    WebMainPage bookingMainPage;
    WebResultsPage bookingResultPage;

    @BeforeClass
    public static void startBrowser() {
        Driver.initDriver();
        Driver.setTimeout();
        LogTool.info("Driver for testing initialized");
    }

    @Before
    public void initializePages() {
        Driver.setTimeout();
        LogTool.info("Driver for testing initialized");
        bookingMainPage = new WebMainPage(Driver.getWebDriver());
        bookingResultPage = new WebResultsPage(Driver.getWebDriver());
        LogTool.info("Pages for testing initialized");
    }

    @Test
    public void parisTest() {
        bookingMainPage.openMainPage(BOOKING_URL);
        bookingMainPage.goToSearchResultsWebDriver("Paris", 2, 0, 4, 3, 10);
        int expected = bookingResultPage.getMaxFilterPrice();
        bookingResultPage.sortPrice();
        Driver.waitUntilElementIsVisible(bookingResultPage.getOverlay());
        int actual = bookingResultPage.getActualMinSortingPrice(7);
        Assert.assertTrue("Price for a night isn't expected", expected < actual || expected == actual);
    }

    @Test
    public void moscowTest() {
        bookingMainPage.openMainPage(BOOKING_URL);
        bookingMainPage.goToElementBuilder("Moscow", 2, 0, 4, 10, 15);
        int expected = bookingResultPage.getMinFilterPrice();
        Driver.waitUntilElementIsVisible(bookingResultPage.getOverlay());
        int actual = bookingResultPage.getActualTopElementPrice(5);
        Assert.assertTrue("Price for a night isn't expected", expected > actual);
    }

    @Test
    public void osloTest() throws InterruptedException {
        bookingMainPage.openMainPage(BOOKING_URL);
        bookingMainPage.goToSearchResultsWebDriver("Oslo", 1, 2, 2, 1, 2);
        bookingResultPage.filterHotelsByStars();
        Driver.waitUntilElementIsVisible(bookingResultPage.getOverlay());
        bookingResultPage.javascriptFunction();
        String expectedColor = "rgba(255, 0, 0, 1)";
        assertEquals("Color isn't red", expectedColor, bookingResultPage.getColorOfAddress());
    }

    @After
    public void closeDriver() throws InterruptedException {
        Driver.clearCache();
        LogTool.info("Cache of browser cleared");
    }

    @AfterClass
    public static void stopBrowser() {
        Driver.quitDriver();
        LogTool.info("Driver closed");
    }
}
