package tests.silverscreensteps.silverscreentesttools;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class SilverScreenTestTools {

    public static boolean elementIsDisplayed(WebElement element){
        return element.isDisplayed();
    }

    public static boolean checkSearchResults(ArrayList<String> searchResults, String name){
        int flag = 0;
        for (String searchResult : searchResults) {
            if (!searchResult.contains(name)) {
                System.out.println("Following search result doesn't contain: " + name);
                System.out.println(searchResult + "\n");
                flag = 1;
            }
        }
        return flag == 0;
    }
}
