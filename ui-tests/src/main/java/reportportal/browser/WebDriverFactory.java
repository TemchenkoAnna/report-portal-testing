package reportportal.browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import reportportal.configuration.DriverListener;

import java.net.MalformedURLException;

public class WebDriverFactory {
    private static WebDriver driver;

    public WebDriverFactory() {
        throw new AssertionError("Creating entity of " + WebDriverFactory.class + " is forbidden");
    }

    public static WebDriver getDriver(BrowserType type) {
        WebDriver driver = null;
        try {

                    switch (type) {
                        case CHROME -> driver = selectChromeDriverInstanceTypeBasedOnGridCondition();
                        case FIREFOX -> driver = selectFirefoxDriverInstanceTypeBasedOnGridCondition();
                        default -> throw new IllegalArgumentException("Incorrect browser type to initialize: " + type);
                    }


        } catch (MalformedURLException malformedURLException) {
            malformedURLException.printStackTrace();
        }
        EventFiringDecorator<WebDriver> decorator = new EventFiringDecorator<>(new DriverListener());
        assert driver != null;
        return decorator.decorate(driver);
    }

    private static WebDriver selectChromeDriverInstanceTypeBasedOnGridCondition()
            throws MalformedURLException {

            WebDriverManager.chromedriver().clearDriverCache().setup();
            driver = new ChromeDriver();
            return driver;
    }

    private static WebDriver selectFirefoxDriverInstanceTypeBasedOnGridCondition()
            throws MalformedURLException {

            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();

        return driver;
    }
}
