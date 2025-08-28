package com.report.test;
import com.github.javafaker.Faker;
import com.report.ProjectConfig;
import com.report.conditions.responses.DashboardResponse;
import com.report.conditions.responses.IdResponse;
import com.report.payloads.*;
import com.report.services.DashboardApiService;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import java.io.IOException;
import static com.report.conditions.Conditions.bodyField;
import static com.report.conditions.Conditions.statusCode;
import static com.report.conditions.Conditions.jsonSchema;
import static org.hamcrest.core.IsNot.not;
import static org.testng.Assert.assertEquals;

@Listeners({com.epam.reportportal.testng.ReportPortalTestNGListener.class})
public class DashboardsTest {
    private final DashboardApiService dashboardApiService = new DashboardApiService();
    private final Faker faker = new Faker();
    ProjectConfig config = ConfigFactory.create(ProjectConfig.class, System.getProperties());

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = config.baseUrl() + config.projectName();
    }

    @Test(testName = "User can view all created dashboards")
    public void userCanGetAllDashboards() throws IOException {
        String dashboardsJsonPath = "/dashboard-schema.json";
        dashboardApiService.getAllDashboards()
                .shouldHave(statusCode(200))
                .shouldHave(jsonSchema(dashboardsJsonPath));
    }

    @Test(testName = "User can create dashboard")
    public void userCanCreateDashboard() {
        DashboardPayload dashboardPayload = new DashboardPayload()
                .description(faker.commerce().department())
                .name(faker.commerce().productName());

        dashboardApiService.createDashboard(dashboardPayload)
                .shouldHave(statusCode(201))
                .shouldHave(bodyField("id", not(Matchers.emptyOrNullString())));
    }
    @Test(testName = "User can not create dashboard with invalid data")
    public void userCantCreateDashboardWithInvalidData() {
        DashboardPayload dashboardPayload = new DashboardPayload()
                .description(faker.commerce().department())
                .name("");

        dashboardApiService.createDashboard(dashboardPayload)
                .shouldHave(statusCode(400))
                .shouldHave(bodyField("message", Matchers.is("Incorrect Request. [Field 'name' should not contain only white spaces and shouldn't be empty. Field 'name' should have size from '3' to '128'.] ")));
    }

    @Test(testName = "User can not create dashboard with duplicate name")
    public void userCantCreateDashboardWithDuplicateName() {
        String name = faker.commerce().productName();

        DashboardPayload dashboardPayload = new DashboardPayload()
                .description(faker.commerce().department())
                .name(name);

        dashboardApiService.createDashboard(dashboardPayload)
                .shouldHave(statusCode(201))
                .shouldHave(bodyField("id", not(Matchers.emptyOrNullString())));

        DashboardPayload dashboardPayloadDuplicate = new DashboardPayload()
                .description(faker.commerce().department())
                .name(name);

        dashboardApiService.createDashboard(dashboardPayloadDuplicate)
                .shouldHave(statusCode(409))
                .shouldHave(bodyField("message", Matchers.is("Resource '" + name + "' already exists. You couldn't create the duplicate.")));
    }

    @Test(testName = "User can get dashboard by ID")
    public void userCanGetDashboard() {
        String description = faker.commerce().department();
        String name = faker.commerce().productName();

        DashboardPayload dashboardPayload = new DashboardPayload()
                .description(description)
                .name(name);
        //create Dashboard and get ID
        int id = dashboardApiService.createDashboard(dashboardPayload)
                .asPojo(IdResponse.class)
                .id();

        //User can Get Dashboard by ID
        DashboardResponse response = dashboardApiService.getDashboardById(id).asPojo(DashboardResponse.class);
        assertEquals(response.description(), description);
        assertEquals(response.name(), name);
    }

    @Test(testName = "User can update dashboard")
    public void userCanUpdateDashboard() {
        String description = faker.commerce().department();
        String name = faker.commerce().productName();

        DashboardPayload dashboardPayload = new DashboardPayload()
                .description("Some Description")
                .name("Name to be updated");
        //create Dashboard and get ID
        int id = dashboardApiService.createDashboard(dashboardPayload)
                .asPojo(IdResponse.class)
                .id();

        UpdateDashboardPayload updateDashboardPayload = new UpdateDashboardPayload()
                .description(description)
                .name(name);

        dashboardApiService.updateDashboard(updateDashboardPayload, id)
                .shouldHave(statusCode(200))
                .shouldHave(bodyField("message", Matchers.is("Dashboard with ID = '" + id + "' successfully updated")));
    }
    @Test(testName = "User can not update non-existing dashboard")
    public void userCantUpdateNonExistingDashboard() {
        UpdateDashboardPayload updateDashboardPayload = new UpdateDashboardPayload()
                .description(faker.commerce().department())
                .name(faker.commerce().productName());

        dashboardApiService.updateDashboard(updateDashboardPayload, 9999)
                .shouldHave(statusCode(404))
                .shouldHave(bodyField("message", Matchers.is("Dashboard with ID '9999' not found on project 'default_personal'. Did you use correct Dashboard ID?")));
    }

    @Test(testName = "User can delete dashboard")
    public void userCanDeleteDashboard() {
        String description = faker.commerce().department();
        String name = faker.commerce().productName();

        DashboardPayload dashboardPayload = new DashboardPayload()
                .description(description)
                .name(name);
        //create Dashboard and get ID
        int id = dashboardApiService.createDashboard(dashboardPayload)
                .asPojo(IdResponse.class)
                .id();

        dashboardApiService.deleteDashboard(id)
                .shouldHave(statusCode(200))
                .shouldHave(bodyField("message", Matchers.is("Dashboard with ID = '" + id + "' successfully deleted.")));
    }

    @Test(testName = "User can not delete non-existing dashboard")
    public void userCantDeleteNonExistingDashboard() {
        int id = 9999;
        dashboardApiService.deleteDashboard(id)
                .shouldHave(statusCode(404))
                .shouldHave(bodyField("message", Matchers.is("Dashboard with ID '" + id + "' not found on project 'default_personal'. Did you use correct Dashboard ID?")));
    }

    @Test(testName = "User can not create duplicate dashboard")
    public void userCantCreateDuplicateDashboard() {
        String name = faker.commerce().productName();

        DashboardPayload dashboardPayload = new DashboardPayload()
                .description(faker.commerce().department())
                .name(name);
        dashboardApiService.createDashboard(dashboardPayload)
                .shouldHave(statusCode(201))
                .shouldHave(bodyField("id", not(Matchers.emptyOrNullString())));

        dashboardApiService.createDashboard(dashboardPayload)
                .shouldHave(statusCode(409))
                .shouldHave(bodyField("message", Matchers.is("Resource '" + name + "' already exists. You couldn't create the duplicate.")));
    }

    @Test(testName = "User can create a dashboard and add to widget to it")
    public void userCanAddWidgetToDashboard() {
        String widgetName = "LAUNCH STATISTICS AREA";
        String widgetType = "launchStatistics";
        int widgetId = 16;

        DashboardPayload dashboardPayload = new DashboardPayload()
                .description(faker.commerce().department())
                .name(faker.commerce().productName());

        int id = dashboardApiService.createDashboard(dashboardPayload)
                .asPojo(IdResponse.class)
                .id();

        WidgetSize widgetSize = new WidgetSize()
                .width(4)
                .height(3);
        WidgetPosition widgetPosition = new WidgetPosition()
                .positionY(5)
                .positionX(6);
        AddWidget addWidget = new AddWidget()
                .widgetId(widgetId)
                .widgetName(widgetName)
                .widgetType(widgetType)
                .widgetSize(widgetSize)
                .widgetPosition(widgetPosition);
        AddWidgetPayload addWidgetPayload = new AddWidgetPayload()
                .addWidget(addWidget);
        dashboardApiService.createWidget(addWidgetPayload, id)
                .shouldHave(statusCode(200))
                .shouldHave(bodyField("message", Matchers.is("Widget with ID = '" + widgetId + "' was successfully added to the dashboard with ID = '" + id + "'")));
    }
}
