plugins {
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
    implementation("io.github.bonigarcia:webdrivermanager:5.8.0")
    testImplementation(platform("io.qameta.allure:allure-bom:$allureVersion"))
    testImplementation("io.qameta.allure:allure-testng")
    testImplementation("org.slf4j:slf4j-simple:2.0.11")
    testImplementation ("org.testng:testng:7.9.0")
    testImplementation("ch.qos.logback:logback-classic:1.5.3")
    implementation("ch.qos.logback:logback-core:1.5.3")
    implementation("org.aeonbits.owner:owner-java8:1.0.12")
    agent("org.aspectj:aspectjweaver:$aspectJVersion")
    implementation("io.qameta.allure:allure-java-commons:2.26.0")
    implementation("io.qameta.allure:allure-rest-assured:2.26.0")
    implementation("org.seleniumhq.selenium:selenium-java:4.20.0")
}

tasks.test {
    useTestNG()
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