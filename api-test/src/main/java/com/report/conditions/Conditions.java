package com.report.conditions;

import lombok.experimental.UtilityClass;
import org.hamcrest.Matcher;

import java.io.IOException;

@UtilityClass
public class Conditions {
    public StatusCodeCondition statusCode(int statusCode) {
        return new StatusCodeCondition(statusCode);
    }
    public BodyFieldCondition bodyField(String jsonPath, Matcher matcher){
        return new BodyFieldCondition(jsonPath, matcher);
    }
    public JsonSchemaValidation jsonSchema(String jsonPath) throws IOException {
        return new JsonSchemaValidation(jsonPath);
    }
}

