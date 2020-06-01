package pages.silverscreen;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import pages.WebAbstractPage;
import utilities.LogTool;

import java.util.ArrayList;
import java.util.List;

public class SilverScreenMainPage extends WebAbstractPage {

    protected Actions builder;

    @FindBy(xpath = "(//*[name()='svg' and @id='svg-icon-search'])[1]")
    private WebElement search;

    @FindBy(xpath = "//*[@id='root']//descendant::input[@placeholder='Поиск']")
    private WebElement searchField;

    private final String searchResultXpath = "//*[@id='root']//descendant::a[@poster]//following-sibling::div";

    @FindBy(xpath = "//*[@id='root']//descendant::a[@poster]//following-sibling::div")
    private WebElement searchResult;

    @FindBy(xpath = "//*[@id='root']//descendant::h2[contains(., 'Фильмы')]")
    private WebElement resultHeader;


    public SilverScreenMainPage(WebDriver driver) {
        super(driver);
        this.builder = new Actions(driver);
    }

    public void openMainPage(String baseUrl) {
        driver.get(baseUrl);
        LogTool.debug("Opened URL " + baseUrl);
    }

    public void hoverSearch() {
        builder.moveToElement(search).perform();
    }

    public void inputSearchRequest(String name) {
        searchField.click();
        searchField.clear();
        searchField.sendKeys(name);
    }

    public ArrayList<String> getArrayOfSearchResults() {
        ArrayList<String> texts = new ArrayList<>();
        List<WebElement> elementName = driver.findElements(By.xpath(searchResultXpath));
        for (WebElement element : elementName) {
            LogTool.debug("Get text of element " + element);
            texts.add(element.getText());
        }
        return texts;
    }

    public WebElement getResultHeader() {
        return resultHeader;
    }
}
