package runners.cucumber.wssearch;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        glue = {"tests.wssearchsteps"}, //packages with annotations (steps) that will be executed
        features = {"src/test/resources/features/UserApiTest.feature"
        },
        tags = {"@qa or @prod"}, //annotation @qa or @prod should be added to .feature file
        monochrome = false, // pretty print
        strict = false //
)
public class SearchApiTestRunner {
}
