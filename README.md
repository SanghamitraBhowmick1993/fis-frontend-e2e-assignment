# fis-frontend-e2e-assignment

[![Java CI with Maven](https://github.com/SanghamitraBhowmick1993/fis-frontend-e2e-assignment/actions/workflows/e2eflow.yml/badge.svg)](https://github.com/SanghamitraBhowmick1993/fis-frontend-e2e-assignment/actions/workflows/e2eflow.yml)

This is a Selenium Cucumber TestNG framework designed for automating web applications. The framework uses Cucumber for behavior-driven development (BDD) and TestNG for parallel execution.

Project Structure

- src/main/java: Contains main page classes
- src/test/java/parallel: Contains test definition files
- src/test/resources/parallel: Contains feature files
- src/test/java/testrunner: Contains test runner class
- test-output: Contains test reports (Extent Report with base64 image)

Running the Project

To run the project, follow these steps:

1. Clone the repository
2. Run the command: mvn test -Dcucumber.features="src/test/resources/parallel" -Dcucumber.glue=parallel -Denv.url=https://www.ebay.com

Parallel Execution

The framework uses TestNG's parallel execution feature to run tests in parallel. The ParallelRun class extends AbstractTestNGCucumberTests and overrides the scenarios() method to enable parallel execution.

CI/CD

The framework uses GitHub Actions for continuous integration and continuous deployment (CI/CD). The .yml file is located in the .github/workflows directory.

Test Reports

Test reports are generated using Extent Report and are stored in the test-output directory. The reports include base64 images for each step.

Sample Test Report

A sample test report is available in the test-output/SparkReport directory.

Framework Features

- Selenium WebDriver for web automation
- Cucumber for BDD
- TestNG for parallel execution
- Extent Report for test reporting
- GitHub Actions for CI/CD
