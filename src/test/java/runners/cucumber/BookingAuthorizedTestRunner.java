package runners.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import testobjects.bookingobjects.RegisteredTestUserJsonObject;
import tests.bookingsteps.preconditions.Registration;
import utilities.GenerateFakeAddress;
import utilities.MyJsonParser;
import webdriver.Driver;

import java.io.IOException;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        glue = {"tests.bookingsteps"},
        features = {"src/test/resources/features/BookingAuthorizedTest.feature"
        },
        tags = {"@qa or @prod"},
        monochrome = false,
        strict = false
)
public class BookingAuthorizedTestRunner {

        private static final String BOOKING_URL = "https://www.booking.com/";
        private static final String password = "123456QW";
        private static final String filePath = "src/test/resources/testdata/testuser";

        @BeforeClass
        public static void setUp() throws IOException, InterruptedException {
                Driver.initDriver();
                Driver.setTimeout();
                String email = GenerateFakeAddress.generateEmail();
                RegisteredTestUserJsonObject testuser = new RegisteredTestUserJsonObject(email, password);
                MyJsonParser.saveJsonToFile(filePath, testuser);
                Registration.performRegistration(Driver.getWebDriver(), email, password, BOOKING_URL);
                Driver.clearCache();
        }

        @AfterClass
        public static void tearDown() {
                Driver.quitDriver();
        }
}
