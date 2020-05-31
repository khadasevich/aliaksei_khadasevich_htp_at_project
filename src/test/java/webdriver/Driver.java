package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

import java.util.concurrent.TimeUnit;

public class Driver {

    private static WebDriver webDriver;
    private static FluentWait<WebDriver> fluentWait;

    private Driver() throws IllegalAccessError {
        throw new IllegalAccessError("Cannot create Instance of utility class");
    }

    public static void initDriver() {

    }

    public static WebDriver getWebDriver() {
        if (webDriver == null) {
            webDriver = DriverManager.getChromeDriver();
        }
        return webDriver;
    }


    public static void clearCache() throws InterruptedException {
        webDriver.manage().deleteAllCookies();
        webDriver.get("chrome://settings/clearBrowserData");
        WebElement root1 = webDriver.findElement(By.cssSelector("settings-ui"));
        WebElement shadowRoot1 = expandRootElement(root1);
        WebElement root2 = shadowRoot1.findElement(By.cssSelector("settings-main"));
        WebElement shadowRoot2 = expandRootElement(root2);
        WebElement root3 = shadowRoot2.findElement(By.cssSelector("settings-basic-page"));
        WebElement shadowRoot3 = expandRootElement(root3);
        WebElement root4 = shadowRoot3.findElement(By.cssSelector("settings-section > settings-privacy-page"));
        WebElement shadowRoot4 = expandRootElement(root4);
        WebElement root5 = shadowRoot4.findElement(By.cssSelector("settings-clear-browsing-data-dialog"));
        WebElement shadowRoot5 = expandRootElement(root5);
        WebElement root6 = shadowRoot5.findElement(By.cssSelector("#clearBrowsingDataDialog"));
        WebElement clearDataButton = root6.findElement(By.cssSelector("#clearBrowsingDataConfirm"));
        clearDataButton.click();
        Thread.sleep(5000);
    }

    private static WebElement expandRootElement(WebElement element) {
        return (WebElement) ((JavascriptExecutor) webDriver)
                .executeScript("return arguments[0].shadowRoot", element);
    }

    public static void quitDriver() {
        webDriver.close();
        webDriver.quit();
    }

    public static void setTimeout() {
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        webDriver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
    }

    public static void removeTimeout() {
        webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        webDriver.manage().timeouts().pageLoadTimeout(0, TimeUnit.SECONDS);
        webDriver.manage().timeouts().setScriptTimeout(0, TimeUnit.SECONDS);
    }

    public static void waitUntilElementIsVisible(WebElement element) {
        fluentWait = new FluentWait<>(webDriver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofMillis(500));
        removeTimeout();
        try {
            fluentWait.until(ExpectedConditions.invisibilityOf(element));
        } catch (org.openqa.selenium.TimeoutException ignored) {
        }
        setTimeout();
    }


    public static void waitUntilItemWillBeShown(WebElement element) {
        fluentWait = new FluentWait<>(webDriver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofMillis(500)).ignoring(TimeoutException.class);
        fluentWait.until(ExpectedConditions.visibilityOf(element));
    }
}

