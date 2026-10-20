# E-commerce QA Automation Framework

## Overview

A Java-based test automation framework for validating an e-commerce practice application using Selenium WebDriver, TestNG, Maven, and REST Assured.

The project demonstrates UI automation, API validation, negative testing, reusable page objects, assertions, test reporting, and version control.

## Technology Stack

- **Language:** Java 21
- **UI automation:** Selenium WebDriver
- **Test framework:** TestNG
- **API automation:** REST Assured
- **Build and dependency management:** Maven
- **Version control:** Git
- **Test reporting:** Maven Surefire / TestNG reports

## Test Coverage

### UI Automation — SauceDemo

- Verify application page title.
- Verify successful login.
- Verify invalid login error handling.
- Add a product to the shopping cart.
- Complete the checkout process.
- Verify checkout validation when the first name is missing.

### API Automation — JSONPlaceholder

- Verify a successful GET request returns HTTP 200.
- Validate expected JSON response fields.
- Verify a request for a nonexistent post returns HTTP 404.

## Project Structure

```text
ecommerce-qa-automation/
├── pom.xml
├── README.md
└── src/
    └── test/
        └── java/
            ├── pages/
            │   ├── LoginPage.java
            │   ├── InventoryPage.java
            │   ├── CartPage.java
            │   └── CheckoutPage.java
            └── tests/
                ├── FirstBrowserTest.java
                ├── LoginTest.java
                ├── InvalidLoginTest.java
                ├── CartTest.java
                ├── CheckoutTest.java
                ├── CheckoutValidationTest.java
                └── ApiTest.java
```

## Prerequisites

- Java JDK 21
- Apache Maven
- Google Chrome or a compatible Chromium browser
- Internet access to the practice application and sample API

## Run the Tests

Run the complete test suite from the project root:

```bash
mvn clean test
```

Run the API tests only:

```bash
mvn -Dtest=ApiTest test
```


## Test Reports

After running the tests, Maven Surefire and TestNG generate reports in:

`target/surefire-reports/`

Open the HTML report locally:

```bash
xdg-open target/surefire-reports/index.html
```

You can also inspect `emailable-report.html` for a compact report.

## Continuous Integration

GitHub Actions automatically runs the UI and API tests when changes are pushed to `main` or when a pull request targets `main`. The workflow can also be started manually.

Workflow file: `.github/workflows/qa-tests.yml`

The workflow:

1. Checks out the repository.
2. Sets up Java 21 and Maven dependency caching.
3. Runs `mvn --batch-mode clean test`.
4. Uploads the Surefire report directory as the `qa-test-reports` artifact, retaining it for 14 days.

To access a CI report:

1. Open the repository's **Actions** tab.
2. Select a completed workflow run.
3. Download `qa-test-reports` under **Artifacts**.
4. Extract the archive and open `index.html` in a browser.

A successful local run does not guarantee a successful CI run; check the workflow result and logs independently.

## Design Approach

The UI tests use the **Page Object Model (POM)** to separate page interactions from test assertions. Explicit waits are used where appropriate to improve synchronization with browser elements.

The API tests use REST Assured and TestNG assertions to validate HTTP responses and JSON data.

## Current Results

The latest recorded test run completed with:

- Tests run: 8
- Failures: 0
- Errors: 0
- Skipped: 0

Results may vary with browser, network, and external service availability.

## Limitations

- SauceDemo is a practice application, not a production e-commerce system.
- JSONPlaceholder is a sample API, not the backend of SauceDemo.
- These tests do not establish production security, load handling, or performance.
- The tests require network access to their external targets.

## Planned Improvements

- Add more API test scenarios and response validation.
- Improve test data management.
- Configure automated execution through a CI/CD pipeline.
- Publish the project repository with clear setup instructions and test evidence.
