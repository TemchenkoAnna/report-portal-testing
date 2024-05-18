package reportportal.browser;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import reportportal.configuration.DirectoryCreator;
import reportportal.configuration.PropertyDataReader;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Browser {
    private static final ThreadLocal<Browser> browserInstance = new ThreadLocal<>();
    private final WebDriver driver;
    private final String screenshotDirectoryPath;
    public static final String BROWSER_KEY_VALUE = "browser";
    public static final String CAN_NOT_BE_NULL_MESSAGE = "%s can not be null";
    public static final String LOCATOR_NOT_NULL_MESSAGE = "Locator can not be null";

    public Browser() {
        BrowserType browserType = BrowserType.valueOf(System.getProperty(BROWSER_KEY_VALUE,
                PropertyDataReader.getDataByKey(BROWSER_KEY_VALUE)));
        screenshotDirectoryPath = DirectoryCreator.create("./target/screenshots");
        driver = WebDriverFactory.getDriver(browserType);
    }

    public static Browser getInstance() {
        if (browserInstance.get() == null) {
            browserInstance.set(new Browser());
        }
        return browserInstance.get();
    }

    public static void stop() {
        if (browserInstance.get() != null) {
            browserInstance.get().driver.quit();
        }
        browserInstance.remove();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void click(By locator) {
        Objects.requireNonNull(locator, LOCATOR_NOT_NULL_MESSAGE);
        driver.findElement(locator)
                .click();
    }

    public boolean isDisplayed(By locator) {
        Objects.requireNonNull(locator, LOCATOR_NOT_NULL_MESSAGE);
        return driver.findElement(locator)
                .isDisplayed();
    }

    public void sendKeys(By locator, CharSequence... keysToSend) {
        Objects.requireNonNull(locator, LOCATOR_NOT_NULL_MESSAGE);
        Objects.requireNonNull(keysToSend, String.format(CAN_NOT_BE_NULL_MESSAGE, "KEYS TO SEND"));
        WebElement inputArea = driver.findElement(locator);
        inputArea.sendKeys(keysToSend);
    }

    public void clear(By locator) {
        Objects.requireNonNull(locator, LOCATOR_NOT_NULL_MESSAGE);
        WebElement webElement = driver.findElement(locator);
        webElement.clear();
    }

    public void reloadPage() {
        driver.navigate().refresh();
    }

    public String getText(By locator) {
        Objects.requireNonNull(locator, LOCATOR_NOT_NULL_MESSAGE);
        WebElement webElement = driver.findElement(locator);
        return webElement.getText().trim();
    }

    public void openNewTab() {
        driver.switchTo().newWindow(WindowType.TAB);
    }

    public void switchTab(String windowHandle) {
        Objects.requireNonNull(windowHandle, String.format(CAN_NOT_BE_NULL_MESSAGE, "Window handle"));
        driver.switchTo().window(windowHandle);
    }

    public void closeTab(String windowHandle) {
        Objects.requireNonNull(windowHandle, String.format(CAN_NOT_BE_NULL_MESSAGE, "Window handle"));
        switchTab(windowHandle);
        driver.close();
    }

    public File takeScreenshot() {
        String screenshotPath = String.format("%s/%s.png", screenshotDirectoryPath, System.nanoTime());
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshotFile, new File(screenshotPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return screenshotFile;
    }

}
