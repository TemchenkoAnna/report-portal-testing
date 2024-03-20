package com.report;

import org.aeonbits.owner.*;

@Config.Sources({"classpath:config.properties"})
public interface ProjectConfig extends Config {
    String baseUrl();

    String projectName();

    boolean logging();

    String token();
}
