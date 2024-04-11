package com.report.cucumber.hooks;

import com.report.ProjectConfig;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;

public class DashboardHooks {
    ProjectConfig config = ConfigFactory.create(ProjectConfig.class, System.getProperties());

    @Before
    public void setUp() {
        RestAssured.baseURI = config.baseUrl() + config.projectName();
    }
}
