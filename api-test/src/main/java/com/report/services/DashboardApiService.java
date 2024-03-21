package com.report.services;

import com.report.ProjectConfig;
import com.report.assertions.AssertableResponse;
import com.report.payloads.Payload;
import io.qameta.allure.Step;
import org.aeonbits.owner.ConfigFactory;

public class DashboardApiService extends ApiService{
    ProjectConfig config =  ConfigFactory.create(ProjectConfig.class);
    @Step("User creating a dashboard")
    public AssertableResponse createDashboard(Payload dashboardPayload) {
        return new AssertableResponse(setUp()
                .auth().oauth2(config.token())
                .body(dashboardPayload)
                .when()
                .post("dashboard"));
    }
}
