package com.report.services;

import com.report.ProjectConfig;
import com.report.assertions.AssertableResponse;
import com.report.payloads.AddWidgetPayload;
import com.report.payloads.DashboardPayload;
import com.report.payloads.UpdateDashboardPayload;
import io.qameta.allure.Step;
import org.aeonbits.owner.ConfigFactory;

public class DashboardApiService extends ApiService {
    public static final String BASE_PATH = "dashboard";
    ProjectConfig config = ConfigFactory.create(ProjectConfig.class);

    @Step("User creating a dashboard")
    public AssertableResponse createDashboard(DashboardPayload dashboardPayload) {
        return new AssertableResponse(setUp()
                .basePath(BASE_PATH)
                .auth().oauth2(config.token())
                .body(dashboardPayload)
                .when()
                .post());

    }

    @Step("User gets all dashboards")
    public AssertableResponse getAllDashboards() {
        return new AssertableResponse(setUp()
                .basePath(BASE_PATH)
                .auth().oauth2(config.token())
                .when()
                .get());
    }


    @Step("User can get dashboard by ID: {id}")
    public AssertableResponse getDashboardById(int id) {
        return new AssertableResponse(setUp()
                .basePath(BASE_PATH + "/{id}")
                .auth().oauth2(config.token())
                .pathParam("id", id)
                .when()
                .get());
    }

    @Step("User can update dashboard with ID: {id}")
    public AssertableResponse updateDashboard(UpdateDashboardPayload updateDashboardPayload, int id) {
        return new AssertableResponse(setUp()
                .basePath(BASE_PATH + "/{id}")
                .auth().oauth2(config.token())
                .pathParam("id", id)
                .body(updateDashboardPayload)
                .when()
                .put());
    }


    @Step("User can delete dashboard with ID: {id}")
    public AssertableResponse deleteDashboard(int id) {
        return new AssertableResponse(setUp()
                .basePath(BASE_PATH + "/{id}")
                .auth().oauth2(config.token())
                .pathParam("id", id)
                .when()
                .delete());
    }

    @Step("User can add widget to dashboard with ID: {id}")
    public AssertableResponse createWidget(AddWidgetPayload addWidgetPayload, int id) {
        return new AssertableResponse(setUp()
                .basePath(BASE_PATH + "/{id}/add")
                .auth().oauth2(config.token())
                .pathParam("id", id)
                .body(addWidgetPayload)
                .when()
                .put());
    }

}
