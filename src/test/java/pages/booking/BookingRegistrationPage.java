package pages.booking;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.WebAbstractPage;
import utilities.LogTool;

public class BookingRegistrationPage extends WebAbstractPage {

    @FindBy(xpath = "//*[@id='login_name_register']")
    private WebElement emailField;

    @FindBy(xpath = "//*[@id='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@id='confirmed_password']")
    private WebElement confirmedPassword;

    @FindBy(xpath = "//*[@id='wl252-modal-name']")
    private WebElement modalPop;

    public BookingRegistrationPage(WebDriver driver) {
        super(driver);
    }

    public void provideEmail(String email) {
        LogTool.debug("Submit login " + emailField);
        emailField.sendKeys(email);
        emailField.submit();
    }

    public WebElement getEmailPassword() {
        LogTool.debug("Return password " + passwordField);
        return passwordField;
    }

    public WebElement getEmailFiled() {
        LogTool.debug("Return email field " + emailField);
        return emailField;
    }


    public void providePassword(String password){
        LogTool.debug("Send keys to element " + passwordField);
        passwordField.sendKeys(password);
        LogTool.debug("Click element " + confirmedPassword);
        confirmedPassword.click();
        LogTool.debug("Send keys to element " + confirmedPassword);
        confirmedPassword.sendKeys(password);
    }

    public void submit(){
        LogTool.debug("Submit element " + confirmedPassword);
        confirmedPassword.submit();
    }

    public WebElement getModalPop() {
        LogTool.debug("Return element " + modalPop);
        return modalPop;
    }
}
