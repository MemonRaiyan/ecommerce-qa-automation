
# E-commerce QA Automation Framework

## Overview
A Java-based UI automation project for testing the SauceDemo practice
e-commerce application using Selenium WebDriver, TestNG and Maven.

## Technology Stack
- Java 21
- Selenium WebDriver
- TestNG
- Maven
- Git

## Current Test Coverage
- Application page-title verification
- Successful login
- Invalid-password error handling
- Adding a product to the shopping cart

## Prerequisites
- Java JDK 21
- Maven
- Google Chrome or a compatible Chromium browser
- Internet access to reach the practice application

## Run the Tests
Clone or download the project, navigate to its root directory, then run:

```bash
mvn test
```

## Test Reports
Maven Surefire generates reports in:

`target/surefire-reports/`

Open `index.html` in a browser for the available TestNG report.

## Design
The project uses Page Object Model (POM) to separate page interactions
from test assertions.

## Limitations
This project currently tests a public practice application.
It does not establish the reliability, security or performance of a
production e-commerce system.

## Future Improvements
- Expand product, cart and checkout coverage
- Add API testing
- Improve test data management
- Integrate automated execution with Jenkins
- Publish repeatable test reports
