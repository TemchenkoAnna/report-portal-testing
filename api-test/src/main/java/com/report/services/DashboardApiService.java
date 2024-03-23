package com.report.services;

import com.report.ProjectConfig;
import com.report.assertions.AssertableResponse;
import com.report.payloads.DashboardPayload;
import com.report.payloads.UpdateDashboardPayload;
import io.qameta.allure.Step;
import org.aeonbits.owner.ConfigFactory;

public class DashboardApiService extends ApiService{
    ProjectConfig config =  ConfigFactory.create(ProjectConfig.class);
    @Step("User creating a dashboard")
    public AssertableResponse createDashboard(DashboardPayload dashboardPayload) {
        return new AssertableResponse(setUp()
                .auth().oauth2(config.token())
                .body(dashboardPayload)
                .when()
                .post("dashboard"));
    }

    @Step("User gets all dashboards")
    public AssertableResponse getAllDashboards() {
        return new AssertableResponse(setUp()
                .auth().oauth2(config.token())
                .when()
                .get("dashboard"));
    }
    @Step("User can get dashboard by ID")
    public AssertableResponse getDashboardById(int id) {
        return new AssertableResponse(setUp()
                .auth().oauth2(config.token())
                .when()
                .get("dashboard/" + id));
    }
    @Step("User can update dashboard")
    public AssertableResponse updateDashboard(UpdateDashboardPayload updateDashboardPayload, int id) {
        return new AssertableResponse(setUp()
                .auth().oauth2(config.token())
                .body(updateDashboardPayload)
                .when()
                .put("dashboard/"+ id));
    }
    @Step("User can update dashboard")
    public AssertableResponse deleteDashboard(int id) {
        return new AssertableResponse(setUp()
                .auth().oauth2(config.token())
                .when()
                .delete("dashboard/" + id));
    }
}
