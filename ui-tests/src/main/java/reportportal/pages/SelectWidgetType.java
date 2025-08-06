package reportportal.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import reportportal.components.Button;

import java.time.Duration;

public class SelectWidgetType extends BasePage {
    public ConfigureWidget clickLaunchStatisticsChartItem() {
        By launchStatisticsChartItemLocator = By.xpath("//div[text()='Launch statistics chart']");
        By activeLaunchStatisticsChartItemLocator = By.xpath("//div[text()='Launch statistics chart' and contains(concat((@class), ''), 'active')]");
        By nextStepButtonLocator = By.xpath("//span[text()='Next step']");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Next step']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Button launchStatisticsChart = new Button(launchStatisticsChartItemLocator);
        launchStatisticsChart.click();
        Button nextStep = new Button(nextStepButtonLocator);
        try {
            nextStep.click(activeLaunchStatisticsChartItemLocator);
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
        return new ConfigureWidget();
    }
}
