#Keywords Summary :@api @ADDObjectAPI
@ADDObjectAPI @api
Feature: ADD Single Object POST API

  Scenario: Verify the Positive Scenarios
    Given User set "name" as the Apple Iphone 16 to Object
    And User set "Year" as the 2029 to Data Object
    And User set "CPU model" as the M12CHIP to Data Object
    And User set "Price" as the 2000.89 to Data Object
    Given user add the Object using api Request
    Then validate the status code is 200
    Then validate if response header availability
    Then validate "ABOVE" object ID is not Changed after the Update Object
    Then verify the Response with "name" as "Apple Iphone 16" in Object
    And verify the Response with "Year" as "2029" in Data Object
    And verify the Response with "Price" as "2000.89" in Data Object
    And verify the Response with "CPU model" as "M12CHIP" in Data Object
    Then Verify the Response Object with "name" as "Apple Iphone 16" in "String" data format
    Then Verify the Response Object with "id" as "ABOVE" in "String" data format
    And Verify the Response Data Object with "Year" as "2029" in "String" data format
    And Verify the Response Data Object with "Price" as "2000.89" in "String" data format

  Scenario Outline: Verifying the "<SCENARIO>" Negative Scenarios
    Given User set "name" as the "<NAME>" to Object with "<NAME_DATA_TYPE>" Data type
    And User set "Year" as the "<YEAR>" to Data Object with "<YEAR_DATA_TYPE>" Data type
    And User set "price" as the "<PRICE>" to Data Object with "<PRICE_DATA_TYPE>" Data type
    And User set "CPU model" as the "<CPU_MODEL>" to Data Object with "<CPU_MODEL_DATA_TYPE>" Data type
    Given user SEND ADD OBJECT POST request
    Then validate the status code is <CODE>
    Examples:
      | SCENARIO                          | NAME          | YEAR | PRICE   | CPU_MODEL     | NAME_DATA_TYPE | YEAR_DATA_TYPE | PRICE_DATA_TYPE | CPU_MODEL_DATA_TYPE | CODE |
      | invalid_DATA_TYPE_FOR_NAME_Field  | 12345         | 2021 | 2004.89 | Intel Core i9 | integer        | integer        | float           | string              | 400  |
      | Special_Characters_FOR_NAME_Field | !@#$&*()_+-;: | 2021 | 2004.89 | Intel Core i9 | string         | integer        | float           | string              | 400  |
      | NULL_FOR_NAME_Field               | null          | 2021 | 2004.89 | Intel Core i9 | string         | integer        | float           | string              | 400  |

  Scenario: Verify that the Getting 400 error When sending POST request with no Body json.
    Given Set JSON Body as EMPTY
    When user SEND ADD OBJECT POST request
    Then validate the status code is 400
    And Validate the Error Response with "400 Bad Request. If you are trying to create or update the data, potential issue is that you are sending incorrect body json or it is missing at all." error message

  Scenario: Verify GET request to the API endpoint instead of POST
    Given User set "name" as the Apple Iphone 14 to Object
    And User set "Year" as the 2029 to Data Object
    And User set "CPU model" as the M12CHIP to Data Object
    And User set "Price" as the 2000 to Data Object
    When Send a GET request to API endpoint instead of POST
    Then validate the status code is 405