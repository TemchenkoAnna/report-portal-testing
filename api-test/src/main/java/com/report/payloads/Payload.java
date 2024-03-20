package com.report.payloads;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Payload {
    @JsonProperty("description")
    private String description;
    @JsonProperty("name")
    private String name;

    public Payload(String description, String name) {
        this.description = description;
        this.name = name;
    }

    public static class Builder {
        private String description;
        private String name;

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Payload build() {
            return new Payload(description, name);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}