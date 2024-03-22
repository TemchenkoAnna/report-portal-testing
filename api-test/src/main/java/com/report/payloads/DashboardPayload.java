package com.report.payloads;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = true)
public class DashboardPayload {
    @JsonProperty("description")
    private String description;
    @JsonProperty("name")
    private String name;

}