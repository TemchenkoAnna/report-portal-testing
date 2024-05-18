package reportportal.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import reportportal.components.Button;
import reportportal.components.CommonPageElement;
import reportportal.components.Link;
import reportportal.components.TextField;
import reportportal.dashboard.Dashboard;
import reportportal.dashboard.DashboardFactory;

import java.time.Duration;
import java.util.List;

public class AllDashboardsPage extends BasePage {
    private final String nameLink = "//div[contains(concat((@class), ''), 'pageLayout__page-content')]//a";
    private static final long WAIT_TIME_SECONDS = 10;
    private final By nameOfCreatedDashboardLocator =
            By.xpath(String.format(nameLink));
    private final By descriptionOfCreatedDashboardLocator =
            By.xpath(String.format("%s/..//div[1]", nameLink));

    public AllDashboardsPage open() {
        By pageLocator = By.xpath("//*[contains(text(), 'Dashboards')]/ancestor::a");
        Link link = new Link(pageLocator);
        link.click();
        return this;
    }

    private void clickEditIcon(String dashboardName){
        String editIconLocator = String.format("%s[text()='%s']/..//i[contains(concat((@class), ''), 'icon-pencil')]", nameLink, dashboardName);
        Button edit = new Button(By.xpath(editIconLocator));
        edit.click();
    }

    public AllDashboardsPage deleteDashboard(String dashboardName){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIME_SECONDS));

        waitUntilPopupsExtinct();
        String deleteIconLocator = String.format("%s[text()= '%s' ]/..//i[contains(concat((@class), ''), 'icon-delete')]", nameLink, dashboardName);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(deleteIconLocator)));
        new Button(By.xpath(deleteIconLocator)).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Delete']")));
        new Button(By.xpath("//button[text()='Delete']")).click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//a[contains(text(), '" + dashboardName + "')]")));

        return this;
    }

    public boolean isDashboardDeleted(String dashboardName) {
        return !isNameOfCreatedDashboardCorrect(dashboardName);
    }

    public AllDashboardsPage editDashboard(String dashboardName, String newDashboardName){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIME_SECONDS));
        waitUntilPopupsExtinct();
        clickEditIcon(dashboardName);
        By nameOfChangedDashboard = By.xpath("//input[@type='text' and @placeholder='Enter dashboard name']");
        By descriptionOfChangedDashboard = By.xpath("//textarea[@placeholder='Enter dashboard description']");
        By updateButton = By.xpath("//button[text()='Update']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameOfChangedDashboard));
        TextField nameTextField = new TextField(nameOfChangedDashboard);
        TextField descriptionTextField = new TextField(descriptionOfChangedDashboard);
        Button update = new Button(updateButton);
        nameTextField.clear();
        nameTextField.type(newDashboardName);
        descriptionTextField.clear();
        descriptionTextField.type(newDashboardName);
        update.click();

        return this;
    }

    public DashboardPage addNewDashboard(String nameOfCreatedDashboard, String descriptionOfCreatedDashboard) {
        By addNewDashboardButton = By.xpath("//span[text()='Add New Dashboard']");
        By nameOfNewDashboardLocator = By.xpath("//div[@id='modal-root']//input[@type='text']");
        By descriptionOfNewDashboardLocator = By.xpath("//div[@id='modal-root']//textarea");
        By addButtonLocator = By.xpath("//button[text()='Add']");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIME_SECONDS));

        Button addNewDashboard = new Button(addNewDashboardButton);
        wait.until(ExpectedConditions.elementToBeClickable(addNewDashboardButton));
        addNewDashboard.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameOfNewDashboardLocator));
        TextField name = new TextField(nameOfNewDashboardLocator);
        name.type(nameOfCreatedDashboard);
        wait.until(ExpectedConditions.visibilityOfElementLocated(descriptionOfNewDashboardLocator));
        TextField description = new TextField(descriptionOfNewDashboardLocator);
        description.type(descriptionOfCreatedDashboard);

        Button add = new Button(addButtonLocator);
        wait.until(ExpectedConditions.elementToBeClickable(addButtonLocator));
        add.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@title = '"+nameOfCreatedDashboard+"']")));

        return new DashboardPage();
    }

    private boolean isInsideOfList(List<WebElement> listOfDashboardNames, String nameOfDashboard) {
        for (int i = 0; i < listOfDashboardNames.toArray().length; ++i) {
            if (listOfDashboardNames.get(i).getText().equals(nameOfDashboard)) {
                return true;
            }
        }
        return false;
    }

    public boolean isNameOfCreatedDashboardCorrect(String nameOfDashboard) {
        waitUntilPopupsExtinct();
        CommonPageElement.waitForAllElementsPresenceLocated(nameOfCreatedDashboardLocator);
        List<WebElement> listOfDashboardNames = driver.findElements(nameOfCreatedDashboardLocator);
        return isInsideOfList(listOfDashboardNames, nameOfDashboard);
    }

    public boolean isDescriptionOfCreatedDashboardCorrect(String descriptionOfDashboard) {
        CommonPageElement.waitForAllElementsPresenceLocated(descriptionOfCreatedDashboardLocator);
        List<WebElement> listOfDashboardDescriptions = driver.findElements(descriptionOfCreatedDashboardLocator);
        return isInsideOfList(listOfDashboardDescriptions, descriptionOfDashboard);
    }

    public AllDashboardsPage addSeveralDashboards(int numberOfDashboards){
        for (int i = 0; i < numberOfDashboards; i++) {
            Dashboard dashboard = DashboardFactory.withRandomTestData();
            addNewDashboard(dashboard.getName(), dashboard.getDescription())
                    .returnToAllDashboardsPage();
        }
        return this;
    }

    public AllDashboardsPage typeDashboardNameIntoSearchByNameInput(String dashboardName) {
        By searchByNameInputLocator = By.xpath("//input[@type='text']");
        TextField searchByNameInput = new TextField(searchByNameInputLocator);
        searchByNameInput.type(dashboardName + "\n");
        return this;
    }

    public boolean isDashboardFoundWithSearchByNameInput(String dashboardName) {
        new WebDriverWait(driver, Duration.ofSeconds(8)).until(ExpectedConditions.numberOfElementsToBe(nameOfCreatedDashboardLocator, 1));
        Link foundDashboard = new Link(nameOfCreatedDashboardLocator);
        return foundDashboard.getText().equals(dashboardName);
    }
}
