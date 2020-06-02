package runners.cucumber.silverscreen;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
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
        monochrome = false,
        tags = {"@qa"},
        snippets = SnippetType.CAMELCASE
)
public class SilverScreenTestRunner {
}
