package reportportal.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import reportportal.browser.Browser;
import reportportal.components.CommonPageElement;

public class BasePage {
    protected WebDriver driver = Browser.getInstance().getDriver();
    protected static final String anyElementContainsSpecifiedTextLocator = "//*[contains(text(),'%s')]";
    protected static final String successfulLoginPopUp = "Signed in successfully";
    protected static final int TEN_SECONDS_TIMEOUT = 10;

    protected void waitUntilPopupsExtinct() {
        By popUpLoginLocator = By.xpath(String
                .format(anyElementContainsSpecifiedTextLocator, successfulLoginPopUp));
        By popUpSettingUpdateLocator = By.xpath(String
                .format(anyElementContainsSpecifiedTextLocator, "Pattern rule has been created"));
        By popUpSuccessfulCreatedLocator = By.xpath(String
                .format(anyElementContainsSpecifiedTextLocator, "Completed successfully!"));
        By popUpSuccessfulAddDashboardLocator = By.xpath(String
                .format(anyElementContainsSpecifiedTextLocator, "Dashboard has been added"));
        CommonPageElement.waitForPageElementInvisibilityLocated(popUpLoginLocator, TEN_SECONDS_TIMEOUT);
        CommonPageElement.waitForPageElementInvisibilityLocated(popUpSettingUpdateLocator, TEN_SECONDS_TIMEOUT);
        CommonPageElement.waitForPageElementInvisibilityLocated(popUpSuccessfulCreatedLocator, TEN_SECONDS_TIMEOUT);
        CommonPageElement.waitForPageElementInvisibilityLocated(popUpSuccessfulAddDashboardLocator, TEN_SECONDS_TIMEOUT);
    }
}
