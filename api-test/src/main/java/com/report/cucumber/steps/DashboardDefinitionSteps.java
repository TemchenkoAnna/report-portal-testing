package com.report.cucumber.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.report.ProjectConfig;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.text.IsEmptyString.emptyOrNullString;


public class DashboardDefinitionSteps {
    private RequestSpecification request;
    private Response response;
    ProjectConfig config = ConfigFactory.create(ProjectConfig.class, System.getProperties());
    private Faker faker = new Faker();
    private String id;
    private String description;
    private String name;

    @Given("I have a valid dashboard payload")
    public void i_have_a_valid_dashboard_payload() throws JsonProcessingException {
        description = faker.commerce().department();
        name = faker.commerce().productName();
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> dashboardData = new HashMap<>();
        dashboardData.put("description", description);
        dashboardData.put("name", name);
        String jsonBody = objectMapper.writeValueAsString(dashboardData);

        request = RestAssured.given()
                .contentType(ContentType.JSON)
                .auth().oauth2(config.token())
                .body(jsonBody);
    }

    @When("I create a dashboard")
    public void i_create_a_dashboard() {
        response = request.post("dashboard");
    }

    @Then("the dashboard should be created successfully")
    public void the_dashboard_should_be_created_successfully() {
        response.then()
                .statusCode(201)
                .body("id", not(emptyOrNullString()));
    }
    @Given("I have the ID from newly created dashboard")
    public void i_have_the_id_from_newly_created_dashboard() {
        id = response.jsonPath().getString("id");
    }
    @When("I request the dashboard by its ID")
    public void i_request_the_dashboard_by_its_id() {
        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .auth().oauth2(config.token())
                .get("dashboard/" + id);
    }
    @Then("I should get the dashboard details")
    public void i_should_get_the_dashboard_details() {
        response.then()
                .statusCode(200)
                .body("description", equalTo(description))
                .body("name", equalTo(name));
    }

    @When("I update the dashboard")
    public void i_update_the_dashboard() throws JsonProcessingException {
        description = faker.commerce().department();
        name = faker.commerce().productName();
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> dashboardData = new HashMap<>();
        dashboardData.put("description", description);
        dashboardData.put("name", name);
        String jsonBody = objectMapper.writeValueAsString(dashboardData);

        request = RestAssured.given()
                .contentType(ContentType.JSON)
                .auth().oauth2(config.token())
                .body(jsonBody);

        response = request.put("dashboard/" + id);
    }
    @Then("the dashboard should be updated successfully")
    public void the_dashboard_should_be_updated_successfully() {
        response.then()
                .statusCode(200)
                .body("message", is("Dashboard with ID = '" + id + "' successfully updated"));
    }

    @When("I delete the dashboard")
    public void i_delete_the_dashboard() {
        response = RestAssured.given()
                .auth().oauth2(config.token())
                .when()
                .delete("dashboard/" + id);
    }

    @Then("the dashboard should be deleted successfully")
    public void the_dashboard_should_be_deleted_successfully() {
        response.then()
                .statusCode(200)
                .body("message", is("Dashboard with ID = '" + id + "' successfully deleted."));
    }
    @When("I try to create a dashboard with the same details")
    public void i_try_to_create_a_dashboard_with_the_same_details() {
        response = request.post("dashboard");
    }

    @Then("I should get an error message")
    public void i_should_get_an_error_message() {
        response.then()
                .statusCode(409)
                .body("message", is("Resource '" + name + "' already exists. You couldn't create the duplicate."));
    }
}

