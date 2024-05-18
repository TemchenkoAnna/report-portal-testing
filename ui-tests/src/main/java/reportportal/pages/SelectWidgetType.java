package reportportal.pages;

import org.openqa.selenium.By;
import reportportal.components.Button;

public class SelectWidgetType extends BasePage {
    public ConfigureWidget clickLaunchStatisticsChartItem() {
        By launchStatisticsChartItemLocator = By.xpath("//div[text()='Launch statistics chart']");
        By activeLaunchStatisticsChartItemLocator = By.xpath("//div[text()='Launch statistics chart' and contains(concat((@class), ''), 'active')]");
        By nextStepButtonLocator = By.xpath("//span[text()='Next step']");
        Button launchStatisticsChart = new Button(launchStatisticsChartItemLocator);
        launchStatisticsChart.click();
        Button nextStep = new Button(nextStepButtonLocator);
        nextStep.click(activeLaunchStatisticsChartItemLocator);
        return new ConfigureWidget();
    }
}
