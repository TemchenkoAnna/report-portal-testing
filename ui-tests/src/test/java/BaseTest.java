import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import reportportal.browser.Browser;
import reportportal.configuration.CapabilitiesCreator;

import java.lang.reflect.Method;

public class BaseTest {
    protected Browser browser;

    @BeforeMethod(description = "Set Up")
    public void setUp(Method method) {
        CapabilitiesCreator.getMethodName(method.getName());
        browser = Browser.getInstance();
    }

    @AfterMethod(description = "Tear down")
    public void tearDown(ITestResult result) {
        Browser.stop();
    }
}
