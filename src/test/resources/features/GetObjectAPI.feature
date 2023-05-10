#Keywords Summary :@api ,@getObjectAPI
@getObjectAPI @api
Feature: Retrieve Single Object GET API

  Scenario: Verify the Positive Scenarios
    Given user get Response for "7" Object Id
    Then validate the status code is 200
    Then validate if response header availability
    Then Verify the Response Object with "name" as "Apple MacBook Pro 16" in "String" data format
    Then Verify the Response Object with "id" as "7" in "String" data format
    And Verify the Response Data Object with "year" as "2019" in "Integer" data format
    And Verify the Response Data Object with "price" as "1849.99" in "Float" data format
    And verify the Response with "CPU model" as "Intel Core i9" in Data Object
    And verify the Response with "Hard disk size" as "1 TB" in Data Object

  Scenario Outline: Verify the Negative Scenarios
    Given user get Response for "<OBJECT_ID>" Object Id
    Then validate the status code is <STATUS_CODE>
    And Validate the Error Response with "<ERROR_MSG>" error message
    Examples:
      | OBJECT_ID         | STATUS_CODE | ERROR_MSG                         |
      | INVALID_OBJECT_ID | 404         | Oject with id=null was not found. |
      | EMPTY             | 400         | Bad Request                       |


  Scenario Outline: Verify that the response is not vulnerable to common web application attacks
    Given Check the "<ATTACK_TYPE>" for get Object API
    Examples:
      | ATTACK_TYPE   |
      | SQL_INJECTION |
      | XSS           |
      | CSRF          |