package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.BookingUtils;
import utilities.LogTool;

import static utilities.BookingUtils.getPriceForNight;

public class BookingResultsPage extends BookingAbstractPage {

    JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;

    @FindBy(xpath = "//*[@id='filter_price']//descendant::a[@data-id='pri-5']//span[@class!='filter_count']")
    private WebElement maxPrice;

    @FindBy(xpath = "//*[@id='filter_price']//descendant::a[@data-id='pri-1']//span[@class!='filter_count']")
    private WebElement minPrice;

    @FindBy(xpath = "//*[@id='sort_by']//descendant::a[contains(., 'owest')]")
    private WebElement sortPrice;

    @FindBy(xpath = "(//*[@id='hotellist_inner']//descendant::" +
            "div[@class='bui-price-display__value prco-text-nowrap-helper prco-inline-block-maker-helper'])[1]")
    private WebElement topElement;

    @FindBy(xpath = "(//*[@id='hotellist_inner']//div[@class='sr_rooms_table_block clearfix '])" +
            "[10]//descendant::div[@class='bui-price-display__value " +
            "prco-text-nowrap-helper prco-inline-block-maker-helper'']")
    private WebElement tenElement;

    @FindBy(xpath = "//*[@class='sr-usp-overlay__container']")
    private WebElement overlay;

    @FindBy(xpath = "//*[@id='filter_class']//descendant::span[contains(., '3 stars')]")
    private WebElement threeStars;

    @FindBy(xpath = "//*[@id='filter_class']//descendant::span[contains(., '4 stars')]")
    private WebElement fourStars;

    @FindBy(xpath = "(//*[@id='hotellist_inner']//a[@class='hotel_name_link url'])[10]//span[@data-et-click]")
    private WebElement address;

    @FindBy(xpath = "//*[@id='profile-menu-trigger--content']")
    private WebElement profile;

    @FindBy(xpath = "//*[@id='profile-menu']//descendant::div[contains(., 'list')]")
    private WebElement myList;

    @FindBy(xpath = "//*[@class='wl-dropdown-saved-to-message' and @aria-hidden='true']")
    private WebElement savedPopUp;

    String savedItemXpath = "//*[@class='bk-icon -iconset-heart sr-wl-entry-heart-svg']";

    String saveXpath = "//*[@data-title='Save']";

    String hotelNamesXpath = "//*[@class='hotel_name_link url']//descendant::span[@data-et-click]";

    public BookingResultsPage(WebDriver driver) {
        super(driver);
    }

    public int getMaxFilterPrice() {
        LogTool.debug("Click element " + maxPrice);
        maxPrice.click();
        LogTool.debug("Return element " + maxPrice);
        return getPriceForNight(maxPrice, 1);
    }

    public int getMinFilterPrice() {
        LogTool.debug("Click element " + minPrice);
        minPrice.click();
        LogTool.debug("Return element " + minPrice);
        return getPriceForNight(minPrice, 1);
    }

    public void filterHotelsByStars() throws InterruptedException {
        LogTool.debug("Click element " + threeStars);
        threeStars.click();
        LogTool.debug("Click element " + fourStars);
        fourStars.click();
        java.util.concurrent.TimeUnit.SECONDS.sleep(1);
    }

    public void javascriptFunction() {
        scrollPageDown(address);
        hoverHotelName(address);
        changeHotelBackground();
        changeHotelNameColor();
    }

    public WebElement getOverlay() {
        LogTool.debug("Return element " + overlay);
        return overlay;
    }

    void scrollPageDown(WebElement element) {
        LogTool.debug("Scroll to element " + element);
        javascriptExecutor.executeScript("arguments[0].scrollIntoView();" +
                "window.scrollBy(0,-100);", element);
    }

    void scrollPageUp(WebElement element) {
        LogTool.debug("Scroll to element " + element);
        javascriptExecutor.executeScript("arguments[0].scrollIntoView();" +
                "window.scrollBy(0,100);", element);
    }

    void changeHotelBackground() {
        LogTool.debug("Change colour of element");
        javascriptExecutor.executeScript("" +
                "document.querySelector('#hotellist_inner > div:nth-child(11)').style.backgroundColor = 'green';");
    }

    void hoverHotelName(WebElement element) {
        LogTool.debug("Hover element " + element);
        String strJavaScript = "var element = arguments[0];"
                + "var mouseEventObj = document.createEvent('MouseEvents');"
                + "mouseEventObj.initEvent( 'mouseover', true, true );"
                + "element.dispatchEvent(mouseEventObj);";
        javascriptExecutor.executeScript(strJavaScript, element);
    }

    void changeHotelNameColor() {
        LogTool.debug("Change colour of element");
        javascriptExecutor.executeScript(
                "document.querySelector('#hotellist_inner > div:nth-child(11) > " +
                        "div.sr_item_content.sr_item_content_slider_wrapper > div.sr_property_block_main_row > " +
                        "div.sr_item_main_block > div.sr-hotel__title-wrap > h3 > a > " +
                        "span.sr-hotel__name').style.color  = 'red';");
    }

    public void sortPrice() {
        LogTool.debug("Click element " + sortPrice);
        sortPrice.click();
    }


    public int getActualMinSortingPrice(int interval) {
        LogTool.debug("Return value per night " + topElement);
        return getPriceForNight(topElement, interval);
    }

    public int getActualTopElementPrice(int interval) {
        LogTool.debug("Return value per night " + topElement);
        return getPriceForNight(topElement, interval);
    }

    public String getColorOfAddress() {
        LogTool.debug("Return colour of element " + address);
        return address.getCssValue("color");
    }

    public void saveFirstItem() {
        WebElement saveFirst = driver.findElement(By.xpath(BookingUtils.generateSaveXpath(1)));
        LogTool.debug("Click element " + saveFirst);
        saveFirst.click();
    }

    public void saveLastItem() {
        int quantityOfSaves = driver.findElements(By.xpath(saveXpath)).size();
        WebElement lastItem = driver.findElement(By.xpath(BookingUtils.generateSaveXpath(quantityOfSaves)));
        LogTool.debug("Click element " + lastItem);
        lastItem.click();
    }

    public String firstHotelName() {
        WebElement hotelFirst = driver.findElement(By.xpath(BookingUtils.generateHotelXpath(1)));
        LogTool.debug("Return text of element " + hotelFirst);
        return hotelFirst.getText();
    }

    public String lastHotelName() {
        int quantityOfHotels = driver.findElements(By.xpath(hotelNamesXpath)).size();
        WebElement hotelLast = driver.findElement(By.xpath(BookingUtils.generateHotelXpath(quantityOfHotels)));
        LogTool.debug("Return text of element " + hotelLast);
        return hotelLast.getText();
    }

    public String getColorOfSave() {
        return driver.findElement(By.xpath(savedItemXpath)).getCssValue("fill");
    }

    public void goToMyLists() {
        scrollPageUp(profile);
        LogTool.debug("Click element " + profile);
        profile.click();
        LogTool.debug("Click element " + myList);
        myList.click();
    }

    public void goToMyListsByLink(String url){
        driver.get(url);
    }

    public WebElement getSavedPopUp(){
        LogTool.debug("Return element " + savedPopUp);
        return savedPopUp;
    }
}

