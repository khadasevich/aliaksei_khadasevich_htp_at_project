package tests.bookingsteps.preconditions;

import org.openqa.selenium.WebDriver;
import pages.BookingMainPage;
import pages.BookingSignPage;
import webdriver.Driver;

public class SignIn {

    public static void goThroughLogin(WebDriver driver, String email, String password) {
        BookingSignPage signPage = new BookingSignPage(driver);
        BookingMainPage mainPage = new BookingMainPage(driver);
        signPage.provideEmail(email);
        Driver.waitUntilItemWillBeShown(signPage.getPasswordField());
        signPage.providePassword(password);
        Driver.waitUntilItemWillBeShown(mainPage.getProfileElement());
    }
}
