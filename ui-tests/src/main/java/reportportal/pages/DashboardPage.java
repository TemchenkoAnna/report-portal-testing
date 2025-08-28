package reportportal.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import reportportal.browser.Browser;
import reportportal.components.Button;
import reportportal.components.CommonPageElement;

import java.time.Duration;

public class DashboardPage extends BasePage {
    private static final long WAIT_TIME_SECONDS = 5;
    By addNewWidgetButtonLocator = By.xpath("//span[text()='Add new widget']");

    public boolean isDashboardAdded() {
        CommonPageElement.waitForPageElementPresenceLocated(addNewWidgetButtonLocator);
        return CommonPageElement.isElementVisible(addNewWidgetButtonLocator, 5);
    }

    private SelectWidgetType clickAddNewWidget(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIME_SECONDS));
        Button addNewWidgetButton = new Button(addNewWidgetButtonLocator);
        wait.until(ExpectedConditions.elementToBeClickable(addNewWidgetButtonLocator));
        addNewWidgetButton.click();
        return new SelectWidgetType();
    }

    public DashboardPage addNewWidget(String filterName, String launchName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIME_SECONDS));
        clickAddNewWidget()
                .clickLaunchStatisticsChartItem()
                .addFilter(filterName, launchName)
                .clickNextStep()
                .clickAdd();
       wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'"+filterName+"')]")));
        return this;
    }

    public boolean isNewWidgetAdded(String name) {
        String nameOfAddedWidgetLocator = "//div[contains(text(),'s')]";
        WebDriver driver = Browser.getInstance().getDriver();
        try {
            driver.findElement(By.xpath(String.format(nameOfAddedWidgetLocator, name)));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public AllDashboardsPage returnToAllDashboardsPage(){
        new Button(By.xpath("//a[text()='All Dashboards']")).click();
        return new AllDashboardsPage();
    }
}
