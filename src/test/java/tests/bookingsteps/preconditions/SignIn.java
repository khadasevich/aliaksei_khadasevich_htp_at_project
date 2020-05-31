package tests.bookingsteps.preconditions;

import org.openqa.selenium.WebDriver;
import pages.booking.WebMainPage;
import pages.booking.WebSignPage;
import webdriver.Driver;

public class SignIn {

    public static void goThroughLogin(WebDriver driver, String email, String password) {
        WebSignPage signPage = new WebSignPage(driver);
        WebMainPage mainPage = new WebMainPage(driver);
        signPage.provideEmail(email);
        Driver.waitUntilItemWillBeShown(signPage.getPasswordField());
        signPage.providePassword(password);
        Driver.waitUntilItemWillBeShown(mainPage.getProfileElement());
    }
}
