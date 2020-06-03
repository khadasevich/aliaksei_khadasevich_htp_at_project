package runners.cucumber.silverscreen;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import webdriver.Driver;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        glue = {"tests.silverscreensteps"}, //packages with annotations (steps) that will be executed
        features = {"src/test/resources/features/SilverScreenTest.feature"
        },
        monochrome = false
)
public class SilverScreenTestRunner {

    @AfterClass
    public static void tearDown() {
        Driver.quitDriver();
    }
}
