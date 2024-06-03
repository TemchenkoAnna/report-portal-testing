package reportportal.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import reportportal.components.Button;

import java.time.Duration;

public class Save extends BasePage {
    private static final long WAIT_TIME_SECONDS = 5;
    public DashboardPage clickAdd() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIME_SECONDS));

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Add']")));
        new Button(By.xpath("//button[text()='Add']")).click();
        return new DashboardPage();
    }
}