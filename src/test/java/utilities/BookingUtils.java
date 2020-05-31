package utilities;

import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Formatter;

public class BookingUtils {

    public static String generateDateXpath(int quantityOfDays) {
        Formatter form = new Formatter();
        LocalDate today = LocalDate.now();
        String dateXpath = "//*[@id='frm']//descendant::td[@data-date='%s']";
        return form.format(dateXpath, today.plus(quantityOfDays, ChronoUnit.DAYS)).toString();
    }

    public static int getPriceForNight(WebElement element, int interval) {
        String cost = element.getText();
        return Integer.parseInt(cost.replaceAll("[^\\d.]", "")) / interval;
    }

    public static String generateSaveXpath(int quantity) {
        Formatter form = new Formatter();
        String savePreparedXpath = "(//*[@data-title='Save'])[%s]";
        return form.format(savePreparedXpath, quantity).toString();
    }

    public static String generateHotelXpath(int quantity) {
        Formatter form = new Formatter();
        String hotelPreparedXpath = "(//*[@class='hotel_name_link url']//descendant::span[@data-et-click])[%s]";
        return form.format(hotelPreparedXpath, quantity).toString();
    }
}
