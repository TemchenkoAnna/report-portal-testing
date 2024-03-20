package com.report.conditions;

import lombok.experimental.UtilityClass;
import org.hamcrest.Matcher;

@UtilityClass
public class Conditions {
    public static StatusCodeCondition statusCode(int statusCode){
        return new StatusCodeCondition(statusCode);
    }
    public static BodyFieldCondition bodyField(String jsonPath, Matcher matcher){
        return new BodyFieldCondition(jsonPath, matcher);

    }
}
