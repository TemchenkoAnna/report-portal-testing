package com.report.test;

import com.github.javafaker.Faker;
import com.report.ProjectConfig;
import com.report.conditions.Conditions;
import com.report.payloads.DashboardPayload;
import com.report.services.DashboardApiService;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.report.conditions.Conditions.bodyField;
import static com.report.conditions.Conditions.statusCode;
import static org.hamcrest.core.IsNot.not;

//@Listeners({com.epam.reportportal.testng.ReportPortalTestNGListener.class})
public class DashboardsTest {
    private final DashboardApiService dashboardApiService  = new DashboardApiService();
    private final Faker faker = new Faker();
    ProjectConfig config =  ConfigFactory.create(ProjectConfig.class, System.getProperties());

    @BeforeClass
    public void setUp() {
       RestAssured.baseURI = config.baseUrl()+config.projectName();
    }

    @Test
    public void userCanGetAllDashboards() throws IOException {
        String dashboardsJsonPath = "D:/Studying/report-portal/api-test/src/main/resources/dashboard-schema.json";
        dashboardApiService.getAllDashboards()
                .shouldHave(statusCode(200))
                .shouldHave(Conditions.jsonSchema(dashboardsJsonPath));
    }

    @Test
    public void userCanCreateDashboard() {
        DashboardPayload dashboardPayload = new DashboardPayload()
                .description(faker.commerce().department())
                .name(faker.commerce().productName());

        dashboardApiService.createDashboard(dashboardPayload)
                .shouldHave(statusCode(201))
                .shouldHave(bodyField("id", not(Matchers.emptyOrNullString())));
    }
}
