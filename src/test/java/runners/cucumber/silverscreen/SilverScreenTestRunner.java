package runners.cucumber.silverscreen;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        glue = {"tests.silverscreensteps"}, //packages with annotations (steps) that will be executed
        features = {"src/test/resources/features/SilverScreenTest.feature"
        },
        monochrome = false,
        snippets = SnippetType.CAMELCASE
)
public class SilverScreenTestRunner {
}
