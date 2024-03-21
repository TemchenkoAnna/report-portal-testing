
plugins {
    id("java")
    id("io.freefair.lombok") version "8.6"
    id("io.qameta.allure-report") version "2.11.2"
}
val allureVersion = "2.25.0"
val aspectJVersion = "1.9.21"
val kotlinVersion = "1.9.22"
val agent: Configuration by configurations.creating {
    isCanBeConsumed = true
    isCanBeResolved = true
}
group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    implementation("org.projectlombok:lombok:1.18.28")
    implementation ("io.rest-assured:rest-assured:5.4.0")
    testImplementation ("org.testng:testng:7.9.0")
    implementation ("com.fasterxml.jackson.core:jackson-databind:2.16.2")
    implementation("org.hamcrest:hamcrest:2.2")
    implementation("com.github.javafaker:javafaker:1.0.2")
    implementation("com.guicedee.services:sl4j:1.0.13.5")
    testImplementation("ch.qos.logback:logback-classic:1.5.3")
    implementation("ch.qos.logback:logback-core:1.5.3")
    implementation("org.aeonbits.owner:owner-java8:1.0.12")
    agent("org.aspectj:aspectjweaver:$aspectJVersion")
    implementation("io.qameta.allure:allure-java-commons:2.26.0")
    implementation("io.qameta.allure:allure-rest-assured:2.26.0")
   // testImplementation("io.qameta.allure:allure-rest-assured")
    implementation("com.jayway.jsonpath:json-path:2.9.0")
    testImplementation(platform("io.qameta.allure:allure-bom:$allureVersion"))
    testImplementation("io.qameta.allure:allure-testng")
    testImplementation("org.slf4j:slf4j-simple:2.0.11")

}

tasks.test {
    useTestNG()
    testLogging{
        showStandardStreams = true
        events ("PASSED", "FAILED", "SKIPPED")
    }

}
plugins.withId("io.qameta.allure") {
    this.withGroovyBuilder {
        "allure" {
            "autoconfigure" to true
            "aspectjweawer" to true
            "configuration" to "compile"
            "resultsDirectory" to "build/allure-results"
            "reportDirectory" to "build/allure-report"
            "version" to "2.19.0"
        }
    }
}






