package tests.silverscreensteps.silverscreentesttools;

import org.openqa.selenium.WebElement;
import testobjects.silverscreen.SilverScreenSearchResult;

import java.util.ArrayList;

public class SilverScreenTestTools {

    public static boolean elementIsDisplayed(WebElement element){
        return element.isDisplayed();
    }

    public static boolean checkSearchResults(ArrayList<SilverScreenSearchResult> searchResults, String name){
        int flag = 0;
        if(searchResults.size()==0){
            flag = 1;
        }
        for (SilverScreenSearchResult searchResult : searchResults) {
            if (!searchResult.getSearchName().contains(name.toLowerCase()) &&
                    !searchResult.getSearchDescription().contains(name.toLowerCase())) {
                System.out.println("Following search result doesn't contain: " + name);
                System.out.println("Name: " + searchResult.getSearchName() + "\n");
                System.out.println("Description: " + searchResult.getSearchDescription() + "\n");
                flag = 1;
            }
        }
        return flag == 0;
    }
}
