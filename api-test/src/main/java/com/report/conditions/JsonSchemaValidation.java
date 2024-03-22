package com.report.conditions;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import java.io.IOException;
import java.nio.file.Paths;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

public class JsonSchemaValidation implements Condition {
    private final String json;

    public JsonSchemaValidation(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        json = mapper.writeValueAsString(mapper.readValue(Paths.get(filePath).toFile(), Object.class));
    }

    @Override
    public void check(Response response) {
        response.then().assertThat().body(matchesJsonSchema(json));
    }
}
