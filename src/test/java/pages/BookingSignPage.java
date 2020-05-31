package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.LogTool;

public class BookingSignPage extends BookingAbstractPage {

    @FindBy(xpath = "//*[@id='username']")
    private WebElement emailFiled;

    @FindBy(xpath = "//*[@id='password']")
    private WebElement passwordField;

    public BookingSignPage(WebDriver driver) {
        super(driver);
    }

    public void provideEmail(String email) {
        LogTool.debug("Submit email " + emailFiled);
        emailFiled.sendKeys(email);
        emailFiled.submit();
    }

    public void providePassword(String password) {
        LogTool.debug("Submit password " + passwordField);
        passwordField.sendKeys(password);
        passwordField.submit();
    }

    public WebElement getPasswordField(){
        LogTool.debug("Return password field " + passwordField);
        return passwordField;
    }
}
