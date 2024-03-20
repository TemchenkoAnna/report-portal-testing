package com.report.conditions;

import io.restassured.response.Response;
import org.hamcrest.Matcher;


public class BodyFieldCondition implements Condition{
    private final String jsonPath;
    private final Matcher matcher;

    public BodyFieldCondition(String jsonPath, Matcher matcher) {
        this.jsonPath = jsonPath;
        this.matcher = matcher;
    }

    @Override
    public void check(Response response) {
        response.then().assertThat().body(jsonPath, matcher);
    }

    @Override
    public String toString() {
        return "Body Field {" +
                "jsonPath='" + jsonPath + '\'' +
                ", matcher=" + matcher +
                '}';
    }
}
