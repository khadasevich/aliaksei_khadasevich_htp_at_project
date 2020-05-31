package tests.wssearchsteps.junit;

import httpservice.PostSearchRequest;
import org.junit.*;
import testobjects.wssearrchobjects.ResponseBodyJsonObject;
import tests.wssearchsteps.searchtesttools.SearchTestTools;
import utilities.MyJsonParser;

import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class SearchSteps {

    private static final String postUrl = "http://178.124.206.46:8001/app/ws/";

    @Test
    public void fullListTest() throws IOException {
        Map<String, ResponseBodyJsonObject> actualResponse = PostSearchRequest.searchUsers(postUrl, "", true);
        ResponseBodyJsonObject actualResponseBody = actualResponse.entrySet().iterator().next().getValue();
        ResponseBodyJsonObject expected = MyJsonParser.parseExpectedJson("expectedresults/allUsersSearch.json");
        assertEquals("Response code isn't expected", "200",
                actualResponse.entrySet().iterator().next().getKey());
        assertEquals("Quantity of users isn't expected", expected.getData().size(),
                actualResponseBody.getData().size());
        Assert.assertTrue(SearchTestTools.responseChecker(actualResponseBody, expected));
    }

    @Test
    public void strictLongNameSearchTest() throws IOException {
        Map<String, ResponseBodyJsonObject> actualResponse = PostSearchRequest.searchUsers(postUrl, "rangaradjangoo", true);
        ResponseBodyJsonObject actualResponseBody = actualResponse.entrySet().iterator().next().getValue();
        ResponseBodyJsonObject expected = MyJsonParser.parseExpectedJson("expectedresults/strictLongNameSearch.json");
        assertEquals("Response code isn't expected", "200",
                actualResponse.entrySet().iterator().next().getKey());
        assertEquals("Quantity of users isn't expected", expected.getData().size(),
                actualResponseBody.getData().size());
        Assert.assertTrue(SearchTestTools.responseChecker(actualResponseBody, expected));
    }

    @Test
    public void strictShortNameSearchTest() throws IOException {
        Map<String, ResponseBodyJsonObject> actualResponse = PostSearchRequest.searchUsers(postUrl, "a", true);
        ResponseBodyJsonObject actualResponseBody = actualResponse.entrySet().iterator().next().getValue();
        ResponseBodyJsonObject expected = MyJsonParser.parseExpectedJson("expectedresults/strictShortNameSearch.json");
        assertEquals("Response code isn't expected", "200",
                actualResponse.entrySet().iterator().next().getKey());
        assertEquals("Quantity of users isn't expected", expected.getData().size(),
                actualResponseBody.getData().size());
        Assert.assertTrue(SearchTestTools.responseChecker(actualResponseBody, expected));
    }

    @Test
    public void partialLongNameSearchTest() throws IOException {
        Map<String, ResponseBodyJsonObject> actualResponse = PostSearchRequest.searchUsers(postUrl, "rangara", false);
        ResponseBodyJsonObject actualResponseBody = actualResponse.entrySet().iterator().next().getValue();
        ResponseBodyJsonObject expected = MyJsonParser.parseExpectedJson("expectedresults/partialLongNameSearch.json");
        assertEquals("Response code isn't expected", "200",
                actualResponse.entrySet().iterator().next().getKey());
        assertEquals("Quantity of users isn't expected", expected.getData().size(),
                actualResponseBody.getData().size());
        Assert.assertTrue(SearchTestTools.responseChecker(actualResponseBody, expected));
    }

    @Test
    public void partialShortNameSearchTest() throws IOException {
        Map<String, ResponseBodyJsonObject> actualResponse = PostSearchRequest.searchUsers(postUrl, "s", false);
        ResponseBodyJsonObject actualResponseBody = actualResponse.entrySet().iterator().next().getValue();
        ResponseBodyJsonObject expected = MyJsonParser.parseExpectedJson("expectedresults/partialShortNameSearch.json");
        assertEquals("Response code isn't expected", "200",
                actualResponse.entrySet().iterator().next().getKey());
        assertEquals("Quantity of users isn't expected", expected.getData().size(),
                actualResponseBody.getData().size());
        Assert.assertTrue(SearchTestTools.responseChecker(actualResponseBody, expected));
    }
}
