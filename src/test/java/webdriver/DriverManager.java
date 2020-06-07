package webdriver;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import utilities.GetResourcesFile;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverManager {

    static WebDriver driver;
    final static ChromeOptions chromeOptions = new ChromeOptions();

    public static WebDriver getDriver(Config config) throws MalformedURLException {
        switch (config) {
            case CHROME:
                return getChromeDriver();
            case FF:
                return getFireFoxDriver();
            case IE:
                return getInternetExploreDriver();
            case REMOTE:
                return getRemoteDriver();
            case MOBILE:
                return getMobileDriver();
            default:
                throw null;
        }
    }

    public static WebDriver getChromeDriver() {
        String pathToDriver = GetResourcesFile.getPath("webdriver/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", pathToDriver);
        System.setProperty("webdriver.chrome.silentOutput", "true");
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--disable-application-cache");
        chromeOptions.addArguments("--disable-gpu");
        driver = new ChromeDriver(chromeOptions);
        return driver;
    }

    public static WebDriver getRemoteDriver() throws MalformedURLException {
        return new RemoteWebDriver(new URL("http://localhost:8888/wd/hub"), chromeOptions);
    }

    public static WebDriver getFireFoxDriver() {
        String pathToDriver = GetResourcesFile.getPath("webdriver/geckodriver.exe");
        System.setProperty("webdriver.gecko.driver", pathToDriver);
        driver = new FirefoxDriver();
        return driver;
    }

    public static WebDriver getInternetExploreDriver() {
        String pathToDriver = GetResourcesFile.getPath("webdriver/internetexplorer.exe");
        System.setProperty("webdriver.ie.driver", pathToDriver);
        driver = new InternetExplorerDriver();
        return driver;
    }

    public static WebDriver getMobileDriver() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "Pixel 3");
        caps.setCapability("platformName", "android");
        caps.setCapability("browserName", "chrome");
        WebDriver driver =  new RemoteWebDriver(new URL("http://0.0.0.0:4723/wd/hub"), caps);
        return driver;
    }
}
