package tests.bookingsteps.preconditions;

import org.openqa.selenium.WebDriver;
import pages.ConfirmRegistration;
import pages.BookingMainPage;
import pages.BookingRegistrationPage;
import webdriver.Driver;

public class Registration {

    public static void performRegistration(WebDriver driver,
                                           String email,
                                           String password,
                                           String url) throws InterruptedException {
        ConfirmRegistration confirmRegistration = new ConfirmRegistration(driver);
        BookingMainPage bookingMainPage = new BookingMainPage(driver);
        BookingRegistrationPage bookingRegistrationPage = new BookingRegistrationPage(driver);
        bookingMainPage.openMainPage(url);
        bookingMainPage.goToRegistration();
        Driver.waitUntilItemWillBeShown(bookingRegistrationPage.getEmailFiled());
        bookingRegistrationPage.provideEmail(email);
        Driver.waitUntilItemWillBeShown(bookingRegistrationPage.getEmailPassword());
        bookingRegistrationPage.providePassword(password);
        bookingRegistrationPage.submit();
        Driver.waitUntilItemWillBeShown(bookingRegistrationPage.getModalPop());
        confirmRegistration.loginToMail(email);
        Driver.waitUntilItemWillBeShown(confirmRegistration.getRefreshButton());
        confirmRegistration.checkMail();
        Driver.waitUntilItemWillBeShown(confirmRegistration.getEmailFrame());
        Thread.sleep(3000);
        confirmRegistration.confirmRegistration();
    }
}
