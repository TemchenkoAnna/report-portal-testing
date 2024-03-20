package com.report.test;

import com.github.javafaker.Faker;
import com.report.ProjectConfig;
import com.report.payloads.Payload;
import com.report.services.DashboardApiService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.aeonbits.owner.ConfigFactory;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.report.conditions.Conditions.bodyField;
import static com.report.conditions.Conditions.statusCode;
import static org.hamcrest.core.IsNot.not;

public class DashboardsTest {
    public final DashboardApiService dashboardApiService  = new DashboardApiService();
    private final Faker faker = new Faker();
    ProjectConfig config =  ConfigFactory.create(ProjectConfig.class, System.getProperties());
    @BeforeClass
    public void setUp() {
       RestAssured.baseURI = config.baseUrl()+config.projectName();
    }


    @Deprecated
    @Test
    public void userCanGetAllDashboards() {
        RestAssured.given().contentType(ContentType.JSON).log().all()
                .auth().oauth2(config.token())
                .when()
                .get("dashboard")
                .then().log().all()
                .assertThat().statusCode(200);
    }

    @Test
    public void userCanCreateDashboard() {
        Payload dashboardPayload = Payload.builder()
                .withDescription(faker.commerce().department())
                .withName(faker.commerce().productName())
                .build();

        dashboardApiService.createDashboard(dashboardPayload)
                .shouldHave(statusCode(201))
                .shouldHave(bodyField("id", not(Matchers.emptyOrNullString())));

    }

}
