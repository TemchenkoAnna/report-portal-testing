package com.report;

import org.aeonbits.owner.*;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "classpath:config.properties",
        "classpath:secret.properties"
})
public interface ProjectConfig extends Config {
    String baseUrl();

    String projectName();

    boolean logging();
    @Key("token")
    @DefaultValue("${env:TOKEN}")
    String token();
}
