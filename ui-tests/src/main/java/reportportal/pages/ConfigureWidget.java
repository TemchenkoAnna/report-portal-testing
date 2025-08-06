package reportportal.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import reportportal.components.Button;
import reportportal.components.TextField;

import java.time.Duration;

public class ConfigureWidget extends BasePage {

    public ConfigureWidget addFilter(String filterName, String launchName) {
        By addFilterButtonLocator = By.xpath("//span[text()='Add filter']");
        By inputFilterNameFieldLocator = By.xpath("//input[@placeholder='Input filter name']");
        By launchNameFieldLocator = By.xpath("//input[@placeholder='Enter name']");
        By submitButtonLocator = By.xpath("//button[text()='Submit']");
        new Button(addFilterButtonLocator).click();
        new TextField(inputFilterNameFieldLocator).type(filterName);
        new TextField(launchNameFieldLocator).type(launchName);
        new Button(submitButtonLocator).click();
        return this;
    }

    public Save clickNextStep() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Next step']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
        return new Save();
    }
}
