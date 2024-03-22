
plugins {
    id("java")
    id("io.qameta.allure") version "2.11.2"
    id("io.qameta.allure-report") version "2.11.2"
    id("io.freefair.lombok") version "8.6"
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
}

tasks.test {
    useTestNG()
    jvmArgs = listOf(
            "-javaagent:${agent.singleFile}"
    )
}
subprojects {
    apply(plugin = "io.freefair.lombok")
    apply(plugin = "java")
}


