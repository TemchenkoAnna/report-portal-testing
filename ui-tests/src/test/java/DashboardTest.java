import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import reportportal.credentials.CredentialsFactory;
import reportportal.dashboard.Dashboard;
import reportportal.dashboard.DashboardFactory;
import reportportal.filter.Filter;
import reportportal.filter.FilterFactory;
import reportportal.pages.AllDashboardsPage;
import reportportal.services.AuthenticationService;
import reportportal.services.NavigationService;

import static org.testng.Assert.assertTrue;

public class DashboardTest extends BaseTest{
    @BeforeMethod(description = "logs in report portal with default credentials")
    public void loginToReportPortal() {
        new NavigationService().navigateToLoginPage();
        new AuthenticationService().login(
                CredentialsFactory.withDataFromProperty().getLogin(),
                CredentialsFactory.withDataFromProperty().getPassword());
    }

    @Test
    public void addNewDashboard() {
        Dashboard dashboard = DashboardFactory.withRandomTestData();
        boolean isDashboardAdded = new AllDashboardsPage().open()
                .addNewDashboard(dashboard.getName(), dashboard.getDescription())
                .isDashboardAdded();
        assertTrue(isDashboardAdded);
    }

    @Test
    public void testNameOfCreatedDashboard(){
        Dashboard dashboard = DashboardFactory.withRandomTestData();
        boolean isNameMatchesToCreatedOne = new AllDashboardsPage().open()
                .addNewDashboard(dashboard.getName(), dashboard.getDescription())
                .returnToAllDashboardsPage()
                .isNameOfCreatedDashboardCorrect(dashboard.getName());
        assertTrue(isNameMatchesToCreatedOne);
    }

    @Test
    public void testDescriptionOfCreatedDashboard(){
        Dashboard dashboard = DashboardFactory.withRandomTestData();
        boolean isDescriptionMatchesToCreatedOne = new AllDashboardsPage().open()
                .addNewDashboard(dashboard.getName(), dashboard.getDescription())
                .returnToAllDashboardsPage()
                .isDescriptionOfCreatedDashboardCorrect(dashboard.getDescription());
        assertTrue(isDescriptionMatchesToCreatedOne);
    }
    @Test
    public void testSearchByNameInput(){
        Dashboard searchedDashboard = DashboardFactory.withRandomTestData();
        boolean isDashboardFound = new AllDashboardsPage().open()
                .addNewDashboard(searchedDashboard.getName(), searchedDashboard.getDescription())
                .returnToAllDashboardsPage()
                .addSeveralDashboards(3)
                .typeDashboardNameIntoSearchByNameInput(searchedDashboard.getName())
                .isDashboardFoundWithSearchByNameInput(searchedDashboard.getName());
        assertTrue(isDashboardFound);
    }
    @Test
    public void editDashboard(){
        Dashboard dashboard = DashboardFactory.withRandomTestData();
        Dashboard newDashboard = DashboardFactory.withRandomTestData();
        boolean isDashboardChanged = new AllDashboardsPage().open()
                .addNewDashboard(dashboard.getName(), dashboard.getDescription())
                .returnToAllDashboardsPage()
                .editDashboard(dashboard.getName(),newDashboard.getName())
                .isNameOfCreatedDashboardCorrect(newDashboard.getName());
        assertTrue(isDashboardChanged);
    }

    @Test
    public void deleteDashboard(){
        Dashboard dashboard = DashboardFactory.withRandomTestData();
        boolean isDashboardDeleted = new AllDashboardsPage().open()
                .addNewDashboard(dashboard.getName(), dashboard.getDescription())
                .returnToAllDashboardsPage()
                .deleteDashboard(dashboard.getName())
                .isDashboardDeleted(dashboard.getName());
        assertTrue(isDashboardDeleted);
    }
    @Test
    public void addWidget() {
        Dashboard dashboard = DashboardFactory.withRandomTestData();
        Filter filter = FilterFactory.withRandomTestData();
        boolean isNewWidgetAdded = new AllDashboardsPage().open()
                .addNewDashboard(dashboard.getName(), dashboard.getDescription())
                .addNewWidget(filter.getFilterName(), filter.getLaunchName())
                .isNewWidgetAdded(filter.getFilterName());
        assertTrue(isNewWidgetAdded);
    }
}

