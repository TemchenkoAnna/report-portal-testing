This is a Java-based project designed to learn and demonstrate API testing and Selenium UI testing. The project is built using Gradle for dependency management and build automation.

## Running Tests
To run tests you need to deploy application locally using Docker. Instructions are here: https://reportportal.io/installation/
Fill in the appropriate values for your environment in the `config.properties` and `test-data.properties` files.
This project contains multiple modules with separate scopes of testing. To run tests for a specific module, use the Gradle wrapper.

### API Tests
To run the `api-test` module tests, use:
```bash
./gradlew api-test:test
```

###  UI Tests
To run the `ui-tests` module tests, use:
```bash
./gradlew ui-tests:test
```
Features
API Testing: Automates RESTful API validations using testing framework RestAssured.
Selenium Testing: Automates browser-based UI testing using Selenium WebDriver.
Gradle Integration: Simplifies dependency management, testing, and builds.
Hamcrest Matchers for cleaner, readable assertions in tests.
Lombok Integration for reducing boilerplate code (e.g., getters, setters, constructors).
SonarQube Integration with Gradle for static code analysis and ensuring code quality.
Easy configuration with application.properties
Clean and modular code design adhering to best practices.

Technologies Used
Java (JDK 8+ / 11 / above)
Selenium WebDriver for UI testing
RestAssured
JUnit / TestNG/Cucumber testing frameworks
Gradle for build automation
Hamcrest Matchers for creating declarative assertions
Lombok for reducing boilerplate Java code
SonarQube for static code analysis and code quality checks
Logback for logging
Allure for test result visualization

