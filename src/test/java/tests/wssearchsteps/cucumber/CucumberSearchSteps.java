package tests.wssearchsteps.cucumber;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import httpservice.PostSearchRequest;
import org.apache.log4j.LogManager;
import org.junit.Assert;
import testobjects.ResponseBodyJsonObject;
import tests.wssearchsteps.searchtesttools.SearchTestTools;
import utilities.MyJsonParser;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;

import static org.junit.Assert.assertEquals;

public class CucumberSearchSteps {

    private static final String postUrl = "http://178.124.206.46:8001/app/ws/";
    ResponseBodyJsonObject expected;
    Map<String, ResponseBodyJsonObject> actualResponse;
    ResponseBodyJsonObject actualResponseBody;
    private static final Logger LOGGER = LogManager.getLogger(CucumberSearchSteps.class);


    @Given("I start execution")
    public void iStartExecution() {
        LOGGER.info("I start");
    }

    @When("I search user by \"(.*)\" name")
    public void iSearchUserByName(String string) throws IOException {
        LOGGER.info("I search");
        expected = MyJsonParser.parseExpectedJson("expectedresults/vovaSearch.json");
        actualResponse = PostSearchRequest.searchUsers(postUrl, string, true);
        actualResponseBody = actualResponse.entrySet().iterator().next().getValue();
    }

    @When("I search {string} with strict true")
    public void iSearchWithStrictOrder(String string) throws IOException {
        LOGGER.info("I search");
        expected = MyJsonParser.parseExpectedJson("expectedresults/allUsersSearch.json");
        actualResponse = PostSearchRequest.searchUsers(postUrl, string, true);
        actualResponseBody = actualResponse.entrySet().iterator().next().getValue();
    }

    @When("I search full name {string} with strict true")
    public void iSearchFullNameWithStrictOrder(String string) throws IOException {
        LOGGER.info("I search");
        expected = MyJsonParser.parseExpectedJson("expectedresults/strictLongNameSearch.json");
        actualResponse = PostSearchRequest.searchUsers(postUrl, string, true);
        actualResponseBody = actualResponse.entrySet().iterator().next().getValue();
    }

    @When("I search short name {string} with strict true")
    public void iSearchShortNameWithStrictTrue(String string) throws IOException {
        LOGGER.info("I search");
        expected = MyJsonParser.parseExpectedJson("expectedresults/strictShortNameSearch.json");
        actualResponse = PostSearchRequest.searchUsers(postUrl, string, true);
        actualResponseBody = actualResponse.entrySet().iterator().next().getValue();
    }

    @When("I search long name {string} with strict false")
    public void iSearchLongNameWithStrictFalse(String string) throws IOException {
        LOGGER.info("I search");
        expected = MyJsonParser.parseExpectedJson("expectedresults/partialLongNameSearch.json");
        actualResponse = PostSearchRequest.searchUsers(postUrl, string, false);
        actualResponseBody = actualResponse.entrySet().iterator().next().getValue();
    }

    @When("I search short name {string} with strict false")
    public void iSearchShortNameWithStrictFalse(String string) throws IOException {
        LOGGER.info("I search");
        expected = MyJsonParser.parseExpectedJson("expectedresults/partialShortNameSearch.json");
        actualResponse = PostSearchRequest.searchUsers(postUrl, string, false);
        actualResponseBody = actualResponse.entrySet().iterator().next().getValue();
    }

    @Then("List of users returned from WS")
    public void fullListOfUsersReturnedFromWS() {
        LOGGER.info("Check what I've got");
        assertEquals("Response code isn't expected", "200",
                actualResponse.entrySet().iterator().next().getKey());
        assertEquals("Quantity of users isn't expected", expected.getData().size(),
                actualResponseBody.getData().size());
        Assert.assertTrue(SearchTestTools.responseChecker(actualResponseBody, expected));
    }

    @Then("I verify that I got \"(.*)\"")
    public void iVerifyThatIGot(String string) {
        LOGGER.info("Check what I've got");
        assertEquals("Response code isn't expected", "200",
                actualResponse.entrySet().iterator().next().getKey());
        assertEquals("Quantity of users isn't expected", 1,
                actualResponseBody.getData().size());
        assertEquals("User isn't in the response", string,
                actualResponseBody.getData().get(0).getRealname());
    }
}
