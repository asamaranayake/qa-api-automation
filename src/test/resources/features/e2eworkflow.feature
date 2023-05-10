#Keywords Summary :@api @e2e
@e2e @api
Feature: End to End API Workflow (Add, Get , Update , Patch and Delete)

  Scenario: Verifying User ADD Object
    Given User set "name" as the Apple Iphone 16 to Object
    And User set "Year" as the 2029 to Data Object
    And User set "CPU model" as the M12CHIP to Data Object
    And User set "Price" as the 2000.89 to Data Object
    Given user add the Object using api Request
    Then validate the status code is 200
    Then verify the Response with "name" as "Apple Iphone 16" in Object
    And verify the Response with "Year" as "2029" in Data Object
    And verify the Response with "Price" as "2000.89" in Data Object
    And verify the Response with "CPU model" as "M12CHIP" in Data Object

  Scenario: Verifying Added Object is returned in Get Object API
    Given user get Response for "ABOVE" Object Id
    Then validate the status code is 200
    Then verify the Response with "name" as "Apple Iphone 16" in Object
    And verify the Response with "Year" as "2029" in Data Object
    And verify the Response with "Price" as "2000.89" in Data Object
    And verify the Response with "CPU model" as "M12CHIP" in Data Object

  Scenario: Verifying the User Update the Already created Object
    Given User set "name" as the Apple Iphone 17 to Object
    And User set "Year" as the 2028 to Data Object
    And User set "CPU model" as the M12CHIe to Data Object
    And User set "Price" as the 2000.89 to Data Object
    And User set "color" as the BLUE to Data Object
    Given user update the "ABOVE" Object using api Request
    Then validate the status code is 200
    Then validate "ABOVE" object ID is not Changed after the Update Object
    Then verify the Response with "name" as "Apple Iphone 17" in Object
    And verify the Response with "Year" as "2028" in Data Object
    And verify the Response with "Price" as "2000.89" in Data Object
    And verify the Response with "CPU model" as "M12CHIe" in Data Object
    And verify the Response with "color" as "BLUE" in Data Object

  Scenario: Verifying the User partially Update the already created Object
    Given User set "name" as the Apple Iphone 19 to Object
    Given user partially update the "ABOVE" Object using api Request
    Then validate "ABOVE" object ID is not Changed after the Update Object
    Then verify the Response with "name" as "Apple Iphone 19" in Object
    And verify the Response with "Year" as "2028" in Data Object
    And verify the Response with "Price" as "2000.89" in Data Object
    And verify the Response with "CPU model" as "M12CHIe" in Data Object

  Scenario: Verifying the User DELETE the already created Object
    Given user delete the "ABOVE" Object using api Request
    Then validate the status code is 200
    Then verify the Response with "message" as "Object with id = <OBJECT_ID> has been deleted." in Object
    Given user get Response for "ABOVE" Object Id
    Then validate the status code is 404