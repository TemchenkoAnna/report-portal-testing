package reportportal.configuration;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import reportportal.browser.BrowserType;

import static reportportal.browser.BrowserType.CHROME;
import static reportportal.browser.BrowserType.FIREFOX;

public class CapabilitiesCreator {
    private static String methodName;

    public static DesiredCapabilities getChromeDesiredCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setPlatform(Platform.WIN11);
        return capabilities;
    }

    public static DesiredCapabilities getFirefoxDesiredCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("firefox");
        capabilities.setPlatform(Platform.WIN11);
        return capabilities;
    }

    public static MutableCapabilities getCapabilities(BrowserType type) {
        MutableCapabilities browserCapabilities = null;
        MutableCapabilities sauceOptions = new DesiredCapabilities();

        sauceOptions.setCapability("name", methodName);
        if (type == FIREFOX) {
            browserCapabilities = getFirefoxDesiredCapabilities();
        } else if (type == CHROME) {
            browserCapabilities = getChromeDesiredCapabilities();
        }
        return browserCapabilities;
    }

    public static void getMethodName(String name) {
        methodName = name;
    }
}
