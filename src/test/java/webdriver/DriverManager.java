package webdriver;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager {

    static WebDriver driver;

    public static WebDriver getChromeDriver(){
        String pathToDriver = DriverManager.class.getClassLoader().getResource("webdriver/chromedriver.exe").getPath();
        System.setProperty("webdriver.chrome.driver", pathToDriver);
        System.setProperty("webdriver.chrome.silentOutput", "true");
        final ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
        chromeOptions.addArguments("start-maximized");
        chromeOptions.addArguments("--disable-application-cache");
        chromeOptions.addArguments("--disable-gpu");
        driver = new ChromeDriver(chromeOptions);
        return driver;
    }
}
