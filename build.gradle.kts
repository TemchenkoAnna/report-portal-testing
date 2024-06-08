plugins {
    id("java")
    id("io.qameta.allure") version "2.11.2"
    id("io.qameta.allure-report") version "2.11.2"
    id("io.freefair.lombok") version "8.6"
    id("org.sonarqube") version "5.0.0.4638"

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
    testCompileOnly ("org.projectlombok:lombok:1.18.30")
    testAnnotationProcessor ("org.projectlombok:lombok:1.18.30")
    compileOnly ("org.projectlombok:lombok:1.18.30")
    annotationProcessor ("org.projectlombok:lombok:1.18.30")
    testImplementation ("org.testng:testng:7.9.0")
    agent("org.aspectj:aspectjweaver:$aspectJVersion")
    implementation("io.qameta.allure:allure-java-commons:2.26.0")
    testImplementation(platform("io.qameta.allure:allure-bom:$allureVersion"))
    testImplementation("io.qameta.allure:allure-testng")
    testImplementation("org.slf4j:slf4j-simple:2.0.11")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.2")
}

tasks.test {
    testLogging.showStandardStreams = true
    if (project.hasProperty("useJUnit5")) {
        useJUnitPlatform()
        maxParallelForks = 2
        forkEvery = 1
    } else {
        useTestNG()
        reports {
            junitXml.required.set(true)
        }
        jvmArgs = listOf(
                "-javaagent:${agent.singleFile}"
        )
    }
    testLogging{
        showStandardStreams = true
        events ("PASSED", "FAILED", "SKIPPED")
    }
}
subprojects {
    apply(plugin = "io.freefair.lombok")
    apply(plugin = "java")
}
sonarqube {
    properties {

                property("sonar.modules", "ui-test,api-test")

                property("ui-tests.sonar.host.url", "http://localhost:9000")
                property("ui-tests.sonar.login", "admin")
                property("ui-tests.sonar.password", "admin40")
                property("ui-tests.sonar.sources", "ui-tests/src/main/java")
                property("ui-tests.sonar.tests", "ui-tests/src/test/java")
                property("ui-tests.sonar.java.binaries", "ui-tests/build/classes/java/main")

                property("api-test.sonar.host.url", "http://localhost:9000")
                property("api-test.sonar.login", "admin")
                property("api-test.sonar.password", "admin40")
                property("api-test.sonar.sources", "api-test/src/main/java")
                property("api-test.sonar.tests", "api-test/src/test/java")
                property("api-test.sonar.java.binaries", "api-test/build/classes/java/main")
            }
        }
