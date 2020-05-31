package webdriver;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverManager {

    static WebDriver driver;
    final static ChromeOptions chromeOptions = new ChromeOptions();

    public static WebDriver getChromeDriver(){
        String pathToDriver = DriverManager.class.getClassLoader().getResource("webdriver/chromedriver.exe").getPath();
        System.setProperty("webdriver.chrome.driver", pathToDriver);
        System.setProperty("webdriver.chrome.silentOutput", "true");
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
        chromeOptions.addArguments("start-maximized");
        chromeOptions.addArguments("--disable-application-cache");
        chromeOptions.addArguments("--disable-gpu");
        driver = new ChromeDriver(chromeOptions);
        return driver;
    }

    public static WebDriver getRemoteDriver() throws MalformedURLException {
        RemoteWebDriver driver = new RemoteWebDriver(new URL("http://localhost:8888/wd/hub"), chromeOptions);
        return driver;
    }
}
