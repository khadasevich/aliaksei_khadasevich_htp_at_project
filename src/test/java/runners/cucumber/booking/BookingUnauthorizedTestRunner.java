package runners.cucumber.booking;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import webdriver.Driver;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        glue = {"tests.bookingsteps"},
        features = {"src/test/resources/features/BookingUnauthorizedTest.feature"
        },
        tags = {"@qa or @prod"},
        monochrome = false,
        strict = false
)
public class BookingUnauthorizedTestRunner {
    @AfterClass
    public static void tearDown() {
        Driver.quitDriver();
    }
}
