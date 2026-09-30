# Travel Booking BDD Automation Framework

## Stack
- Java 17+
- Selenium WebDriver
- PageFactory + Page Object Model
- Cucumber BDD
- TestNG
- Maven
- Extent Reports
- WebDriverManager is NOT required because Selenium Manager automatically resolves drivers in recent Selenium versions.

## Application
This project uses the BlazeDemo flight-booking demo application:
https://blazedemo.com/

It demonstrates a realistic airline search -> result selection -> passenger details -> reservation confirmation workflow.

> This is a public demo application, not a production airline website. The framework intentionally does not automate real payment or financial transactions.

## Project structure

src/test/java
  com.travel.config
    ConfigReader.java
  com.travel.driver
    DriverFactory.java
  com.travel.pages
    HomePage.java
    FlightResultsPage.java
    PurchasePage.java
  com.travel.hooks
    Hooks.java
  com.travel.steps
    FlightBookingSteps.java
  com.travel.runners
    TestRunner.java

src/test/resources
  config/config.properties
  features/FlightBooking.feature

## Run from IntelliJ / Eclipse
1. Import the folder as a Maven project.
2. Make sure Java 17+ is configured.
3. Run `TestRunner.java` as TestNG.

## Run from terminal

Default browser:
mvn clean test

Chrome:
mvn clean test -Dbrowser=chrome

Firefox:
mvn clean test -Dbrowser=firefox

Headless:
mvn clean test -Dbrowser=chrome -Dheadless=true

## Reports
After execution:
- Extent report: target/ExtentReports/ExtentReport.html
- Cucumber HTML: target/cucumber-report.html
- Screenshots: target/screenshots/

## Framework design

Feature
  -> Step Definitions
      -> Page Objects
          -> DriverFactory
              -> Selenium WebDriver

PageFactory is used to initialize WebElements in each page object.

## Real-world interview points covered
- Page Object Model
- PageFactory
- Explicit waits
- Reusable driver management
- Cucumber Hooks
- Scenario-based BDD
- TestNG runner
- Configuration management
- Screenshot on failure
- Extent reporting
- Maven execution
- Browser parameterization
