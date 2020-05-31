package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import utilities.LogTool;

import java.util.ArrayList;
import java.util.List;

import static utilities.BookingUtils.*;


public class BookingMainPage extends BookingAbstractPage {

    protected Actions builder;

    @FindBy(xpath = "//*[@id='ss']")
    private WebElement searchField;

    @FindBy(xpath = "//*[@id='frm']//descendant::div[@class='xp__dates xp__group']")
    private WebElement checkInList;

    @FindBy(xpath = "//*[@id='xp__guests__toggle']")
    private WebElement adultsList;

    @FindBy(xpath = "//*[@class='sb-group__field sb-group__field-adults']" +
            "//descendant::span[@class='bui-stepper__display']")
    private WebElement adultsQuantity;

    @FindBy(xpath = "//*[@class='sb-group__field sb-group__field-adults']/" +
            "/descendant::button[@class='bui-button bui-button--secondary bui-stepper__add-button ']")
    private WebElement plusButtonAdults;

    @FindBy(xpath = "//*[@class='sb-group__field sb-group-children ']//descendant::span[@class='bui-stepper__display']")
    private WebElement childrenQuantity;

    @FindBy(xpath = "//*[@class='sb-group__field sb-group-children ']" +
            "//descendant::button[@class='bui-button bui-button--secondary bui-stepper__add-button ']")
    private WebElement plusButtonChildren;

    @FindBy(xpath = "//*[@class='sb-group__field sb-group__field-rooms']//" +
            "descendant::span[@class='bui-stepper__display']")
    private WebElement roomsQuantity;

    @FindBy(xpath = "//*[@class='sb-group__field sb-group__field-rooms']//" +
            "descendant::button[@class='bui-button bui-button--secondary bui-stepper__add-button ']")
    private WebElement plusButtonRooms;

    @FindBy(xpath = "//*[@id='frm']//descendant::button[@data-sb-id='main']")
    private WebElement searchButton;

    @FindBy(xpath = "//*[@id='current_account_create']")
    private WebElement registration;

    @FindBy(xpath = "//*[@id='current_account']/a/div")
    private WebElement signIn;

    @FindBy(xpath = "//*[@data-title='View your notifications']")
    private WebElement notifications;

    @FindBy(xpath = "//*[@class='uc-notification js-uc-notification  uc-notification-unseen ']")
    private WebElement notConfirmed;

    @FindBy(xpath = "//*[@id='profile-menu-trigger--content']")
    private WebElement profile;

    String notificationsXpath = "//*[@class='uc-notification js-uc-notification  uc-notification-unseen ']";

    String headerItemsXpath = "//*[@id='user_form']/ul/li|//*[@id='logo_no_globe_new_logo']|" +
            "//*[@class='xpb__link']|//*[@class='xpb__link selected']";

    public BookingMainPage(WebDriver driver) {
        super(driver);
        this.builder = new Actions(driver);
    }

    public void openMainPage(String baseUrl) {
        driver.get(baseUrl);
        LogTool.debug("Opened URL " + baseUrl);
    }

    public void goToSearchResultsWebDriver(String city, int rooms, int children, int adults,
                                           int startFrom, int endingDate) {
        LogTool.debug("Click element " + searchField);
        searchField.click();
        LogTool.debug("Clear element " + searchField);
        searchField.clear();
        LogTool.debug("Send city " + city + " element " + searchField);
        searchField.sendKeys(city);
        LogTool.debug("Click element " + checkInList);
        checkInList.click();
        WebElement startDate = driver.findElement(By.xpath(generateDateXpath(startFrom)));
        LogTool.debug("Click element " + startDate);
        startDate.click();
        WebElement endDate = driver.findElement(By.xpath(generateDateXpath(endingDate)));
        LogTool.debug("Click element " + endDate);
        endDate.click();
        LogTool.debug("Click element " + adultsList);
        adultsList.click();
        String quan;
        for (quan = adultsQuantity.getText(); !quan.equals(String.valueOf(adults)); quan = adultsQuantity.getText()) {
            LogTool.debug("Click element " + plusButtonAdults);
            plusButtonAdults.click();
        }
        for (quan = childrenQuantity.getText(); !quan.equals(String.valueOf(children)); quan = childrenQuantity.getText()) {
            plusButtonChildren.click();
        }
        for (quan = roomsQuantity.getText(); !quan.equals(String.valueOf(rooms)); quan = roomsQuantity.getText()) {
            LogTool.debug("Click element " + plusButtonRooms);
            plusButtonRooms.click();
        }
        LogTool.debug("Click element " + searchButton);
        searchButton.click();
    }

    public void goToElementBuilder(String city, int rooms, int children, int adults,
                                   int startFrom, int endingDate) {
        WebElement startDate = driver.findElement(By.xpath(generateDateXpath(startFrom)));
        WebElement endDate = driver.findElement(By.xpath(generateDateXpath(endingDate)));
        LogTool.debug("Clear element " + searchField);
        searchField.clear();
        LogTool.debug("Building of the search querie starts");
        builder.moveToElement(searchField)
                .click()
                .sendKeys(city)
                .moveToElement(checkInList)
                .click()
                .moveToElement(startDate)
                .click()
                .moveToElement(endDate)
                .click()
                .moveToElement(adultsList)
                .click()
                .build()
                .perform();
        String quan;
        for (quan = adultsQuantity.getText(); !quan.equals(String.valueOf(adults)); quan = adultsQuantity.getText()) {
            LogTool.debug("Setting quantity of adults");
            builder.moveToElement(plusButtonAdults).click().build().perform();
        }
        for (quan = childrenQuantity.getText(); !quan.equals(String.valueOf(children)); quan = childrenQuantity.getText()) {
            LogTool.debug("Setting quantity of children");
            builder.moveToElement(plusButtonChildren).click().build().perform();
        }
        for (quan = roomsQuantity.getText(); !quan.equals(String.valueOf(rooms)); quan = roomsQuantity.getText()) {
            LogTool.debug("Setting quantity of rooms");
            builder.moveToElement(plusButtonRooms).click().build().perform();
        }
        builder.moveToElement(searchButton).click().build().perform();
        LogTool.debug("Search finished");
    }

    public void goToRegistration() {
        LogTool.debug("Click element " + registration);
        registration.click();
    }

    public void openNotification() {
        LogTool.debug("Click element " + notifications);
        notifications.click();
    }

    public void goToSignIn() {
        LogTool.debug("Click element " + signIn);
        signIn.click();
    }

    public ArrayList<String> listOfNotifications() {
        ArrayList<String> texts = new ArrayList<>();
        List<WebElement> elementName = driver.findElements(By.xpath(notificationsXpath));
        for (WebElement element : elementName) {
            LogTool.debug("Get text of element " + element);
            texts.add(element.getText());
        }
        return texts;
    }

    public WebElement getProfileElement() {
        LogTool.debug("Return element " + profile);
        return profile;
    }

    public int getQuantityOfItemsInHeader() {
        LogTool.debug("Getting size of elements " + driver.findElements(By.xpath(headerItemsXpath)));
        return driver.findElements(By.xpath(headerItemsXpath)).size();
    }
}

