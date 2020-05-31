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
        goToBookingAndSearch("Paris", 2, 0, 4, 3, 10);
        int expected = bookingResultPage.getMaxFilterPrice();
        bookingResultPage.sortPrice();
        Driver.waitUntilElementIsVisible(bookingResultPage.getOverlay());
        int actual = bookingResultPage.getActualMinSortingPrice(7);
        Assert.assertTrue("Price for a night isn't expected", expected < actual || expected == actual);
    }

    @Test
    public void moscowTest() {
        goToBookingAndSearch("Moscow", 2, 0, 4, 10, 15);
        int expected = bookingResultPage.getMinFilterPrice();
        Driver.waitUntilElementIsVisible(bookingResultPage.getOverlay());
        int actual = bookingResultPage.getActualTopElementPrice(5);
        Assert.assertTrue("Price for a night isn't expected", expected > actual);
    }

    @Test
    public void osloTest() throws InterruptedException {
        goToBookingAndSearch("Oslo", 1, 2, 2, 1, 2);
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

    public void goToBookingAndSearch(String city, int rooms, int children, int adults, int start, int end){
        bookingMainPage.openMainPage(BOOKING_URL);
        bookingMainPage.goToSearchResultsWebDriver(city, rooms, children, adults, start, end);
    }
}
