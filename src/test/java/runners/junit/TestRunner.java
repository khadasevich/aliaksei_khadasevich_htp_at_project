package runners.junit;

import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Suite;
import tests.bookingsteps.junit.AuthorizedSteps;
import tests.bookingsteps.junit.UnauthorizedSteps;

@RunWith(Suite.class)
@Suite.SuiteClasses({UnauthorizedSteps.class, AuthorizedSteps.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestRunner {
}
