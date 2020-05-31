package pages.booking;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.WebAbstractPage;
import utilities.LogTool;

import java.util.ArrayList;
import java.util.List;

public class WebMyListPage extends WebAbstractPage {

    String myHotelsXpath = "//*[@class='js-listview-book js-listview-hotel-title']";

    @FindBy(xpath = "(//*[@id='b2mywishlistPage']//descendant::" +
            "div[@class='bui-group bui-card__actions' and contains(., 'View property')])[1]")
    private WebElement viewProperty;

    public WebMyListPage(WebDriver driver) {
        super(driver);
    }

    public ArrayList<String> getListOfHotels() {
        ArrayList<String> texts = new ArrayList<>();
        List<WebElement> elementName = driver.findElements(By.xpath(myHotelsXpath));
        for (WebElement element : elementName) {
            LogTool.debug("Get text of element " + element);
            texts.add(element.getText());
        }
        return texts;
    }

    public WebElement getViewProperty() {
        LogTool.debug("Return element " + viewProperty);
        return viewProperty;
    }
}
