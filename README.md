# E-commerce QA Automation Framework

## Overview

A Java-based test automation framework for validating an e-commerce practice application using Selenium WebDriver, TestNG, Maven, and REST Assured.

The project demonstrates UI automation, API testing, negative testing, the Page Object Model (POM), explicit waits, assertions, failure screenshot capture, test reporting, continuous integration, and version control.

## Technology Stack

- **Language:** Java 21
- **UI automation:** Selenium WebDriver 4
- **Test framework:** TestNG
- **API automation:** REST Assured
- **Build and dependency management:** Maven
- **Browser:** Google Chrome
- **Version control:** Git and GitHub
- **CI/CD:** GitHub Actions
- **Test reporting:** Maven Surefire and TestNG reports
- **Failure evidence:** Automatic UI failure screenshots

## Test Coverage

### UI Automation — SauceDemo

The framework covers the following UI scenarios:

1. Verify the application page title.
2. Verify successful login with valid credentials.
3. Verify invalid login error handling.
4. Add a product to the shopping cart.
5. Complete the checkout process.
6. Verify checkout validation when the first name is missing.

### API Automation — JSONPlaceholder

The API tests cover:

1. Verify a successful GET request returns HTTP 200.
2. Validate expected JSON response fields.
3. Verify a request for a nonexistent post returns HTTP 404.

**Note:** JSONPlaceholder is a sample API used for independent API testing. It is not the backend of SauceDemo.

## Project Structure

```text
ecommerce-qa-automation/
├── .github/
│   └── workflows/
│       └── qa-tests.yml
├── src/
│   └── test/
│       └── java/
│           ├── pages/
│           │   ├── LoginPage.java
│           │   ├── InventoryPage.java
│           │   ├── CartPage.java
│           │   └── CheckoutPage.java
│           ├── tests/
│           │   ├── FirstBrowserTest.java
│           │   ├── LoginTest.java
│           │   ├── InvalidLoginTest.java
│           │   ├── CartTest.java
│           │   ├── CheckoutTest.java
│           │   ├── CheckoutValidationTest.java
│           │   └── ApiTest.java
│           └── utils/
│               ├── DriverFactory.java
│               └── FailureScreenshot.java
├── .gitignore
├── pom.xml
└── README.md
```

## Prerequisites

Install the following before running the tests:

- Java JDK 21
- Apache Maven 3.9 or a compatible version
- Google Chrome or a compatible Chromium browser
- Git
- Internet access to the practice application and sample API

Verify Java and Maven installations:

```bash
java -version
mvn -version
git --version
```

## Run the Tests

Clone the repository:

```bash
git clone https://github.com/MemonRaiyan/ecommerce-qa-automation.git
cd ecommerce-qa-automation
```

Run the complete test suite:

```bash
mvn clean test
```

Run only the API tests:

```bash
mvn -Dtest=ApiTest test
```

Run an individual UI test class:

```bash
mvn -Dtest=LoginTest test
```

The UI tests use a shared driver factory for browser creation. In the GitHub Actions environment, Chrome runs in headless mode.

## Failure Screenshot Capture

The framework includes a `FailureScreenshot` utility that captures a screenshot when a UI test throws an assertion error or runtime exception within the test's protected execution block.

Screenshots are saved locally under:

```text
target/failure-screenshots/
```

Each screenshot filename includes the test name and a timestamp to help identify the failing test.

Example:

```text
target/failure-screenshots/
└── LoginTest-20261020-193651.png
```

The utility creates the screenshot directory when required and reports screenshot-capture errors without replacing the original test failure.

**Important:** Screenshots are generated when a failure occurs and the browser is still available. A successful test run does not necessarily produce any failure screenshots.

## Test Reports

After running the tests, Maven Surefire and TestNG generate reports in:

```text
target/surefire-reports/
```

Open the HTML report locally on Ubuntu:

```bash
xdg-open target/surefire-reports/index.html
```

You can also inspect `emailable-report.html` for a compact report.

The `target/` directory contains generated test outputs and should not be committed to Git.

## Continuous Integration — GitHub Actions

GitHub Actions automatically runs the UI and API tests when changes are pushed to `main` or when a pull request targets `main`. The workflow can also be triggered manually.

Workflow file:

```text
.github/workflows/qa-tests.yml
```

The workflow:

1. Checks out the repository.
2. Sets up Java 21 using Eclipse Temurin.
3. Caches Maven dependencies.
4. Runs the complete test suite using `mvn --batch-mode clean test`.
5. Uploads Surefire reports and any available failure screenshots as the `qa-test-reports` artifact.
6. Retains the artifact for 14 days.

The artifact upload step runs even if the tests fail, allowing available diagnostic evidence to be collected.

### Access CI Reports and Screenshots

1. Open the GitHub repository.
2. Select the **Actions** tab.
3. Open the relevant workflow run.
4. Download `qa-test-reports` under **Artifacts**.
5. Extract the archive.
6. Open the generated HTML report in a browser and inspect any available failure screenshots.

A successful local test run does not guarantee a successful CI run. Review the workflow status and logs independently.

## Design Approach

### Page Object Model

The UI automation uses the **Page Object Model (POM)** to separate page interactions from test logic and assertions.

Dedicated page classes represent the login, inventory, cart, and checkout pages. This structure improves maintainability and reduces duplication.

### Browser Management

`DriverFactory` centralizes browser creation and enables headless Chrome execution in the CI environment.

### Synchronization

Explicit waits are used where appropriate to synchronize browser interactions with page navigation and element availability.

### API Testing

REST Assured and TestNG assertions validate HTTP status codes and JSON response data independently of the UI tests.

### Failure Diagnostics

The screenshot utility captures UI failure evidence before the browser is closed, making debugging easier.

## Current Test Results

The latest verified local test run completed with:

- **Tests run:** 8
- **Failures:** 0
- **Errors:** 0
- **Skipped:** 0

The screenshot utility was also verified separately by successfully saving a valid PNG image. That temporary verification test was removed from the final suite.

Results may vary depending on browser configuration, network conditions, and external service availability.

## Limitations

- SauceDemo is a practice application, not a production e-commerce system.
- JSONPlaceholder is a sample API and is not connected to SauceDemo.
- The API tests validate basic HTTP responses and JSON fields; they do not cover a real e-commerce backend.
- Failure screenshots are diagnostic evidence, not a substitute for logs or reproducible test cases.
- Screenshots are not produced for successful tests.
- These tests do not establish production security, load handling, or performance.
- The tests require network access to their external targets.

## Planned Improvements

- Expand positive and negative API test coverage.
- Add data-driven tests and improve test data management.
- Add more checkout and cart edge cases.
- Introduce more robust reporting and diagnostic logging.
- Improve CI reporting and failure analysis.
- Explore cross-browser execution.
- Expand the framework with additional test scenarios as the project evolves.

## Author

**Raiyan Memon**

GitHub: https://github.com/MemonRaiyan

Repository: https://github.com/MemonRaiyan/ecommerce-qa-automation
