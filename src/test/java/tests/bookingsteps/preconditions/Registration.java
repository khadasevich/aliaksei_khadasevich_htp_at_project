package tests.bookingsteps.preconditions;

import org.openqa.selenium.WebDriver;
import pages.yopmail.ConfirmRegistration;
import pages.booking.BookingMainPage;
import pages.booking.BookingRegistrationPage;
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
        Thread.sleep(2000);
        confirmRegistration.checkMail();
        Driver.waitUntilItemWillBeShown(confirmRegistration.getEmailFrame());
        Thread.sleep(5000);
        confirmRegistration.confirmRegistration();
    }
}
