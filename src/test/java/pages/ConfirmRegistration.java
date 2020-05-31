package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Set;

public class ConfirmRegistration extends BookingAbstractPage {

    protected WebDriver driver;

    private static final String YOPMAIL_URL = "http://www.yopmail.com/en/";

    @FindBy(xpath = "//*[@id='login']")
    private WebElement yopmailEmail;

    @FindBy(xpath = "//iframe[@name='ifmail']")
    private WebElement emailFrame;

    @FindBy(xpath = "//*[@id='lrefr']")
    private WebElement refreshButton;

    @FindBy(xpath = "//*[@id='nbmail']")
    private WebElement quantityOfMails;

    @FindBy(xpath = "//*[@id='mailmillieu']//descendant::a[contains(., 'Confirm')]")
    private WebElement confirmButton;

    public ConfirmRegistration(WebDriver driver) {
        super(driver);
    }

    public void loginToMail(String email) {
        driver.get(YOPMAIL_URL);
        yopmailEmail.click();
        yopmailEmail.sendKeys(email);
        yopmailEmail.submit();
    }

    public WebElement getEmailFrame() {
        return emailFrame;
    }

    public WebElement getRefreshButton() {
        return refreshButton;
    }

    public void clickRefresh() {
        refreshButton.click();
    }

    public void checkMail() {
        while (quantityOfMails.getText().equals("0 mail ")) {
            refreshButton.click();
        }
    }

    public void confirmRegistration() {
        driver.switchTo().frame(emailFrame);
        String currentHandle = driver.getWindowHandle();
        confirmButton.click();
        Set<String> handles = driver.getWindowHandles();
        for (String actual : handles) {
            if (actual.equalsIgnoreCase(currentHandle)) {
                driver.switchTo().window(currentHandle);
            }
        }
    }
}
