package com.report.assertions;

import com.report.conditions.Condition;
import io.qameta.allure.Step;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class AssertableResponse {
 private final Response response;

    public AssertableResponse(Response response) {
        this.response = response;
    }
@Step
    public AssertableResponse shouldHave(Condition condition){
        log.info("About to check condition {}", condition);
         condition.check(response);
        return this;
    }
    public <T> T asPojo(Class<T> tClass){
        return response.as(tClass);
    }
    public Headers headers(){
        return response.getHeaders();
    }
}
