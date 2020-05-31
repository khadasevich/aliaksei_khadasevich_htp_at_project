package runners.junit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Suite;
import tests.bookingsteps.junit.AuthorizedSteps;
import tests.bookingsteps.junit.UnauthorizedSteps;
import utilities.LogTool;
import webdriver.Driver;

@RunWith(Suite.class)
@Suite.SuiteClasses({AuthorizedSteps.class, UnauthorizedSteps.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestRunner {

    @BeforeClass
    public static void setUp(){
        Driver.initDriver();
        Driver.setTimeout();
        LogTool.info("Driver for testing initialized");
    }

    @AfterClass
    public static void tearDown(){
        LogTool.info("Driver closed");
        Driver.quitDriver();
    }
}
