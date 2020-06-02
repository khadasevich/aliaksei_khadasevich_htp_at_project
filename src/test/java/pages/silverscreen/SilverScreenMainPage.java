package pages.silverscreen;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

    @FindBy(xpath = "//*[@id='root']//descendant::span[contains(., 'Вход')]")
    private WebElement signInButton;

    @FindBy(xpath = "//*[@id='root']//descendant::input[@type='email']")
    private WebElement emailField;

    @FindBy(xpath = "//*[@id='root']//descendant::input[@type='password']")
    private WebElement passwordField;

    @FindBy(xpath = "(//*[@id='root']//descendant::span[contains(., 'Мой уровень: ')])[1]")
    private WebElement loggedHeader;

    @FindBy(xpath = "//*[@id='root']//descendant::span[contains(., 'Выйти')]")
    private WebElement exitButton;

    @FindBy(xpath = "//*[@id='root']//descendant::button[contains(., 'Войти')]")
    private WebElement enterButton;

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
        LogTool.debug("Hover element " + search);
    }

    public void inputSearchRequest(String name) {
        searchField.click();
        LogTool.debug("Click element " + searchField);
        searchField.clear();
        LogTool.debug("Clear element " + searchField);
        searchField.sendKeys(name);
        LogTool.debug("Send name of film to " + searchField);
        searchField.sendKeys(Keys.ENTER);
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
        LogTool.debug("Return element " + resultHeader);
        return resultHeader;
    }

    public void hoverSignIn() {
        LogTool.debug("Hover element " + signInButton);
        builder.moveToElement(signInButton).perform();
    }

    public void inputEmail(String email) {
        LogTool.debug("Input email to " + emailField);
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void inputPassword(String password) {
        LogTool.debug("Input password to " + passwordField);
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickLogin() {
        LogTool.debug("Click element " + enterButton);
        enterButton.click();
    }

    public void logoutFromSilverScreen() {
        LogTool.debug("Click element " + exitButton);
        exitButton.click();
    }

    public String getTextOfHeader() {
        LogTool.debug("Get text of element " + exitButton);
        return loggedHeader.getText();
    }
}
