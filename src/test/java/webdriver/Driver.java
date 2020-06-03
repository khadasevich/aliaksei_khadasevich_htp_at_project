package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import utilities.LogTool;

import java.time.Duration;

import java.util.concurrent.TimeUnit;

public class Driver {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static FluentWait<WebDriver> fluentWait;

    private Driver() throws IllegalAccessError {
        throw new IllegalAccessError("Cannot create Instance of utility class");
    }

    public static void initDriver() {
        if (null == driver.get()) {
            try {
                driver.set(DriverManager.getDriver(Config.CHROME));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static WebDriver getWebDriver() {
        if (null == driver.get()) {
            try {
                driver.set(DriverManager.getDriver(Config.CHROME));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return driver.get();
    }

    public static void scrollPageDown(WebElement element) {
        LogTool.debug("Scroll to element " + element);
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver.get();
        javascriptExecutor.executeScript("arguments[0].scrollIntoView();" +
                "window.scrollBy(0,-100);", element);
    }

    public static void scrollPageUp(WebElement element) {
        LogTool.debug("Scroll to element " + element);
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver.get();
        javascriptExecutor.executeScript("arguments[0].scrollIntoView();" +
                "window.scrollBy(0,100);", element);
    }

    public static void hoverElement(WebElement element) {
        LogTool.debug("Hover element " + element);
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver.get();
        String strJavaScript = "var element = arguments[0];"
                + "var mouseEventObj = document.createEvent('MouseEvents');"
                + "mouseEventObj.initEvent( 'mouseover', true, true );"
                + "element.dispatchEvent(mouseEventObj);";
        javascriptExecutor.executeScript(strJavaScript, element);
    }

    public static void clearCache() throws InterruptedException {
        driver.get().manage().deleteAllCookies();
        driver.get().get("chrome://settings/clearBrowserData");
        WebElement root1 = driver.get().findElement(By.cssSelector("settings-ui"));
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

    public static WebElement expandRootElement(WebElement element) {
        return (WebElement) ((JavascriptExecutor) driver.get())
                .executeScript("return arguments[0].shadowRoot", element);
    }

    public static void quitDriver() {
        driver.get().close();
        driver.get().quit();
    }

    public static void clearCookies() {
        driver.get().manage().deleteAllCookies();
    }

    public static void setTimeout() {
        driver.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get().manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.get().manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
    }

    public static void removeTimeout() {
        driver.get().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        driver.get().manage().timeouts().pageLoadTimeout(0, TimeUnit.SECONDS);
        driver.get().manage().timeouts().setScriptTimeout(0, TimeUnit.SECONDS);
    }

    public static void waitUntilElementIsVisible(WebElement element) {
        fluentWait = new FluentWait<>(driver.get())
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
        fluentWait = new FluentWait<>(driver.get())
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofMillis(500)).ignoring(TimeoutException.class);
        fluentWait.until(ExpectedConditions.visibilityOf(element));
    }
}

