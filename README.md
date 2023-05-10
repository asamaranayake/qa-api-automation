
# BDD Cucumber + RestAssuerd + TestNG API Automation Framework


## Documentation

[API Documentation](https://restful-api.dev/)


## Tech Stack



**REST API Invoking:** *Rest-Assuered*

**Writing Test and execution :** *Cucumber , TestNG*

**Main Lanugage:** *JAVA*

**Json Mapping:** *Jackson*

**Reporting:** *Allure Report, Cucumber Report*

**POJO Classes:** *Lombok* 

**Logs:** *sl4j, ch.qos.logback*

## Pre Requisites

*apache-maven-3.8.6*

*JAVA 11*

*allure 2.20.1*




## Running Tests

To run tests, run the following Steps and command

You Can download this repo from Github
or 
You can simple clone this repo from GitHub


```git
  git clone https://github.com/asamaranayake/qa-api-automation.git
```

To Execute the all Tests 

```bash
mvn clean test
```
To Execute specific test

To Run API automation Test cases only 

```bash
mvn clean test -Dcucumber.filter.tags="@api"
```
## Test Reporting

Test Report file Locations


**Cucumber Report path:**  ```target/cucumber-report/cucumber-output.html```
[Cucumber Report](target/cucumber-report/cucumber-output.html)

***To Gerarate Allure Report***

*1. Run the test using above any command*

*2. Check whether allure-results folders is available inside the target Folder*

*3. Generate the report using below command :*

```bash
mvn allure:report 
```

**Allure Report path:**  ```target/site/allure-maven-plugin/index.html```
[Allure Report](target/site/allure-maven-plugin/index.html)



## Author

- [@asamaranayake](https://www.github.com/asamaranayake)
