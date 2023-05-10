package com.amused.testSteps;

import java.util.HashMap;
import java.util.Map;

import com.amused.services.BaseService;
import com.amused.utils.JsonUtil;
import com.amused.utils.PropertyReader;
import com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class testStepDef {

    Map<String, Object> data = new HashMap<>();
    Map<String, Object> requestBodyObj = new HashMap<>();
    BaseService baseService = new BaseService();
    Response apiResponse;

    private static Logger logger = LoggerFactory.getLogger(testStepDef.class);

    @And("User set {string} as the {} to Object")
    public void userAddAsTheToObject(String jsonAttribute, String jsonValue) {
        try {
            requestBodyObj.put(jsonAttribute, jsonValue);
        } catch (Exception e) {
            Assert.fail("userAddAsTheToObject Step is failed due to exception of " + e);
        }
    }

    @And("User set {string} as the {} to Data Object")
    public void userAddAsTheToDataObject(String jsonAttribute, String jsonValue) {
        try {
            data.put(jsonAttribute, jsonValue);
        } catch (Exception e) {
            Assert.fail("userAddAsTheToDataObject Step is failed due to exception of " + e);
        }
    }

    @Given("user add the Object using api Request")
    public void userInvokeTheAddObjectAPI() {
        try {

            if (data != null && !data.isEmpty()) {
                requestBodyObj.put("data", data);
            } else {
                requestBodyObj.remove("data", data);
            }
            String jsonBody = JsonUtil.serialize(requestBodyObj);
            logger.info("Prepared Json Object >> " + jsonBody);

            apiResponse = baseService.addObject(jsonBody);
            Assert.assertNotNull(apiResponse, "No API response was Received");
            Assert.assertNotNull(JsonUtil.deserialize(apiResponse.asString()).get("id").textValue(), "No ID was Created");
            Assert.assertNotNull(JsonUtil.deserialize(apiResponse.asString()).get("createdAt").textValue(), "No createdAt was Created");

            String objectID = JsonUtil.deserialize(apiResponse.asString()).get("id").textValue();
            logger.info("Created objectID >> " + objectID);
            PropertyReader.getInstance().setProp("objectID", objectID);

            String createdAt = JsonUtil.deserialize(apiResponse.asString()).get("createdAt").textValue();
            logger.info("Created createdAt >> " + createdAt);
            PropertyReader.getInstance().setProp("createdAt", createdAt);

            requestBodyObj.clear();
            data.clear();


        } catch (Exception e) {
            Assert.fail("userInvokeTheAddObjectAPI Step is failed due to exception of " + e);
        }
    }

    @Given("user SEND ADD OBJECT POST request")
    public void userInvokeThePOSTAddObjectAPI() {
        try {
            String jsonBody = "";
            if (data != null && requestBodyObj!=null) {
                requestBodyObj.put("data", data);
                jsonBody = JsonUtil.serialize(requestBodyObj);
                logger.info("Prepared Json Object >> " + jsonBody);
                requestBodyObj.clear();
                data.clear();
            } else if(requestBodyObj!=null) {
                requestBodyObj.remove("data", data);
                jsonBody = JsonUtil.serialize(requestBodyObj);
                logger.info("Prepared Json Object >> " + jsonBody);
                requestBodyObj.clear();
            }
            apiResponse = baseService.addObject(jsonBody);
            Assert.assertNotNull(apiResponse, "No API response was Received");

        } catch (Exception e) {
            Assert.fail("userInvokeTheAddObjectAPI Step is failed due to exception of " + e);
        }
    }

    @Given("Set JSON Body as EMPTY")
    public void setJSONBodyAsEmpty() {
        try{
            requestBodyObj =null;
            data = null;
        }catch (Exception e) {
            Assert.fail("setJSONBodyAsEmpty Step is failed due to exception of " + e);
        }
    }

    @Given("user update the {string} Object using api Request")
    public void userInvokeTheUpdateObjectAPI(String objectID) {
        try {
            if (objectID.equalsIgnoreCase("ABOVE"))
                objectID = PropertyReader.getInstance().getProperty("objectID");

            if (!data.isEmpty()) {
                requestBodyObj.put("data", data);
            } else {
                requestBodyObj.remove("data", data);
            }
            String jsonBody = JsonUtil.serialize(requestBodyObj);
            logger.info("Prepared Json Object >> " + jsonBody);
            apiResponse = baseService.updateObject(jsonBody, objectID);
            Assert.assertNotNull(apiResponse, "No API response was Received");
            Assert.assertNotNull(JsonUtil.deserialize(apiResponse.asString()).get("id").textValue(), "No ID was Found");
            Assert.assertNotNull(JsonUtil.deserialize(apiResponse.asString()).get("updatedAt").textValue(), "No updatedAt was Created");

            String updatedAt = JsonUtil.deserialize(apiResponse.asString()).get("updatedAt").textValue();
            logger.info("updatedAt updatedAt >> " + updatedAt);
            PropertyReader.getInstance().setProp("updatedAt", updatedAt);

            requestBodyObj.clear();
            data.clear();


        } catch (Exception e) {
            Assert.fail("setNameToObject Step is failed due to exception of " + e);
        }
    }

    @Given("user partially update the {string} Object using api Request")
    public void userInvokeTheUpdatePartiallyAPI(String objectID) {
        try {
            if (objectID.equalsIgnoreCase("ABOVE"))
                objectID = PropertyReader.getInstance().getProperty("objectID");

            if (!data.isEmpty()) {
                requestBodyObj.put("data", data);
            } else {
                requestBodyObj.remove("data", data);
            }

            String jsonBody = JsonUtil.serialize(requestBodyObj);
            logger.info("Prepared Json Object >> " + jsonBody);
            apiResponse = baseService.updatePartiallyObject(jsonBody, objectID);
            Assert.assertNotNull(apiResponse, "No API response was Received");
            Assert.assertNotNull(JsonUtil.deserialize(apiResponse.asString()).get("id").textValue(), "No ID was Found");
            Assert.assertNotNull(JsonUtil.deserialize(apiResponse.asString()).get("updatedAt").textValue(), "No updatedAt was Created");

            String updatedAt = JsonUtil.deserialize(apiResponse.asString()).get("updatedAt").textValue();
            logger.info("updatedAt updatedAt >> " + updatedAt);
            PropertyReader.getInstance().setProp("updatedAt", updatedAt);

            requestBodyObj.clear();
            data.clear();


        } catch (Exception e) {
            Assert.fail("setNameToObject Step is failed due to exception of " + e);
        }
    }


    @Then("validate the status code is {int}")
    public void validateTheStatusCodeIs(int expectedStatusCode) {
        try {
            Assert.assertEquals(apiResponse.getStatusCode(), expectedStatusCode);
        } catch (Exception e) {
            Assert.fail("validateTheStatusCodeIs Step is failed due to exception of " + e);
        }
    }

    @When("set the {string} as Object ID ")
    public void extractTheObjectIDFromTheResponse(String objectID) {
        try {
            PropertyReader.getInstance().setProp("objectID", objectID);
        } catch (Exception e) {
            Assert.fail("extractTheObjectIDFromTheResponse Step is failed due to exception of " + e);
        }
    }

    @Given("user get Response for {string} Object Id")
    public void userInvokeTheGET_OBJECTAPI(String objectID) {
        try {
            if (objectID.equalsIgnoreCase("ABOVE")) {
                objectID = PropertyReader.getInstance().getProperty("objectID");
            } else if (objectID.equalsIgnoreCase("EMPTY")) {
                objectID = "";
            } else if (objectID.equalsIgnoreCase("INVALID_OBJECT_ID")) {
                objectID = "null";
            }
            apiResponse = baseService.getObjectForGivenId(objectID);
            Assert.assertNotNull(apiResponse, "No API response was Received");
        } catch (Exception e) {
            Assert.fail("userInvokeTheGET_OBJECTAPI Step is failed due to exception of " + e);
        }
    }

    @Then("Verify the Response Data Object with {string} as {string} in {string} data format")
    public void verifyTheResponseWithAs(String jsonAttribute, String expectedValue, String dataType) {
        try {
            Object actualValue = apiResponse.getBody().jsonPath().get("data." + jsonAttribute);
            Assert.assertTrue(actualValue.getClass().getTypeName().contains(dataType), "Actual Value's data type is not match with Expected Value Data type in Response");
            Assert.assertTrue(actualValue.toString().equals(expectedValue), "Actual Value is not match with Expected Value in Response");
        } catch (Exception e) {
            Assert.fail("validateTheResponse Step is failed due to exception of " + e);
        }
    }

    @Then("Verify the Response Object with {string} as {string} in {string} data format")
    public void verifyTheResponseWithAsInObject(String jsonAttribute, String expectedValue, String dataType) {
        try {
            if (expectedValue.equalsIgnoreCase("ABOVE"))
                expectedValue = PropertyReader.getInstance().getProperty("objectID");

            Object actualValue = apiResponse.getBody().jsonPath().get(jsonAttribute);
            Assert.assertTrue(actualValue.getClass().getTypeName().contains(dataType), "Actual Value's data type is not match with Expected Value Data type in Response");
            Assert.assertTrue(actualValue.toString().equals(expectedValue), "Actual Value is not match with Expected Value in Response");
        } catch (Exception e) {
            Assert.fail("verifyTheResponseWithAsInObject Step is failed due to exception of " + e);
        }
    }

    @Then("validate {string} object ID is not Changed after the Update Object")
    public void validateObjectIDIsNotChangedAfterTheUpdateObject(String expectedObjectID) {
        try {
            if (expectedObjectID.equalsIgnoreCase("ABOVE"))
                expectedObjectID = PropertyReader.getInstance().getProperty("objectID");

            Assert.assertNotNull(JsonUtil.deserialize(apiResponse.asString()).get("id").textValue(), "No ID was Found");
            String actualID = JsonUtil.deserialize(apiResponse.asString()).get("id").textValue();
            Assert.assertEquals(actualID, expectedObjectID, "Actual ID is not match with Expected ID in Response");
        } catch (Exception e) {
            Assert.fail("validateObjectIDIsNotChangedAfterTheUpdateObject Step is failed due to exception of " + e);
        }
    }

    @Then("verify the Response with {string} as {} in Object")
    public void verifyTheResponseWithAsInObject(String jsonAttribute, String expectedValue) {
        try {
            Object actualValue = JsonUtil.deserialize(apiResponse.asString()).get(jsonAttribute);

            if (expectedValue.contains("<OBJECT_ID>")) {
                String expectValue = expectedValue.replace("<OBJECT_ID>", PropertyReader.getInstance().getProperty("objectID"));
                Assert.assertEquals(actualValue.toString(), expectValue, "Actual Value is not match with Expected Value in Response");
            } else {
                Assert.assertEquals(actualValue.toString(), expectedValue, "Actual Value is not match with Expected Value in Response");
            }


        } catch (Exception e) {
            Assert.fail("validateTheResponse Step is failed due to exception of " + e);
        }
    }

    @And("verify the Response with {string} as {} in Data Object")
    public void verifyTheResponseWithAsInDataObject(String jsonAttribute, String expectedValue) {
        try {
            Object actualValue = JsonUtil.deserialize(apiResponse.asString()).get("data").get(jsonAttribute);
            Assert.assertEquals(actualValue.toString(), expectedValue, "Actual Value is not match with Expected Value in Response");
        } catch (Exception e) {
            Assert.fail("validateTheResponse Step is failed due to exception of " + e);
        }
    }

    @Then("validate if response header availability")
    public void validateIfResponseHavingHeader() {
        try {
            Assert.assertTrue(apiResponse.getHeaders().exist(), "response header is not available");
        } catch (Exception e) {
            Assert.fail("validateTheResponse Step is failed due to exception of " + e);
        }
    }

    @And("Validate the Error Response with {string} error message")
    public void validateTheErrorResponse(String errorMessage) {
        try {
            Assert.assertNotNull(apiResponse.asString());
            JsonNode actualValue = JsonUtil.deserialize(apiResponse.asString()).get("error");
            Assert.assertTrue(actualValue.textValue().contains(errorMessage));
        } catch (Exception e) {
            Assert.fail("validateTheErrorResponse Step is failed due to exception of " + e);
        }
    }

    @Given("Check the {string} for get Object API")
    public void checkTheSQLInjectionForGetObjectAPI(String attackName) {
        try {
            switch (attackName) {
                case "SQL_INJECTION":
                    given()
                            .param("7", "'; DROP TABLE users; --").log().all()
                            .when()
                            .get(PropertyReader.getInstance().getProperty("BASE_URI") + "/objects/7")
                            .then().log().all()
                            .statusCode(400);
                    break;
                case "XSS":
                    given()
                            .param("7", "<script>alert('XSS');</script>").log().all()
                            .when()
                            .get(PropertyReader.getInstance().getProperty("BASE_URI") + "/objects/7")
                            .then().log().all()
                            .statusCode(400);
                    break;
                case "CSRF":
                    given()
                            .header("X-Requested-With", "XMLHttpRequest")
                            .cookie("sessionID", "1234567890")
                            .when()
                            .get(PropertyReader.getInstance().getProperty("BASE_URI") + "/objects/7")
                            .then()
                            .statusCode(403);
                    break;
            }
        } catch (Exception e) {
            Assert.fail("checkTheSQLInjectionForGetObjectAPI Step is failed due to exception of " + e.getMessage());
        }
    }

    @Given("user delete the {string} Object using api Request")
    public void userDeleteTheObjectUsingApiRequest(String objectID) {
        try {
            if (objectID.equalsIgnoreCase("ABOVE"))
                objectID = PropertyReader.getInstance().getProperty("objectID");
            apiResponse = baseService.deleteObject(objectID);
            Assert.assertNotNull(apiResponse, "No API response was Received");
        } catch (Exception e) {
            Assert.fail("userDeleteTheObjectUsingApiRequest Step is failed due to exception of " + e.getMessage());
        }
    }


    @Given("Send a GET request to API endpoint instead of POST")
    public void sendAGETRequestToAPIEndpointInsteadOfPOST() {
        try {
            apiResponse = given().contentType(ContentType.JSON)
                    .body(JsonUtil.serialize(requestBodyObj))
                    .get(PropertyReader.getInstance().getProperty("BASE_URI") + "/objects")
                    .then().log().all().using().extract().response();
        } catch (Exception e) {
            Assert.fail("sendAGETRequestToAPIEndpointInsteadOfPOST Step is failed due to exception of " + e.getMessage());
        }

    }

    @Given("User set {string} as the {string} to Object with {string} Data type")
    public void userSetAsTheToObjectWithDataType(String jsonField, String jsonValue, String dataType) {
        try {
            if (!jsonValue.equalsIgnoreCase("EMPTY")) {
                switch (dataType) {
                    case "string":
                        requestBodyObj.put(jsonField, jsonValue);
                        break;
                    case "boolean":
                        requestBodyObj.put(jsonField, Boolean.getBoolean(jsonValue));
                        break;
                    case "integer":
                        requestBodyObj.put(jsonField, Integer.parseInt(jsonValue));
                        break;
                    case "float":
                        requestBodyObj.put(jsonField, Float.parseFloat(jsonValue));
                        break;
                    case "null":
                        requestBodyObj.put(jsonField, null);
                        break;
                }
            } else {
                requestBodyObj = null;
            }
        } catch (Exception e) {
            Assert.fail("userSetAsTheToObjectWithDataType Step is failed due to exception of " + e.getMessage());
        }
    }

    @And("User set {string} as the {string} to Data Object with {string} Data type")
    public void userSetAsTheToDataObjectWithDataType(String jsonField, String jsonValue, String dataType) {
        try {
            if (!jsonValue.equalsIgnoreCase("EMPTY")) {
                switch (dataType) {
                    case "string":
                        data.put(jsonField, jsonValue);
                        break;
                    case "boolean":
                        data.put(jsonField, Boolean.getBoolean(jsonValue));
                        break;
                    case "integer":
                        data.put(jsonField, Integer.parseInt(jsonValue));
                        break;
                    case "float":
                        data.put(jsonField, Float.parseFloat(jsonValue));
                        break;
                    case "null":
                        data.put(jsonField, null);
                        break;
                }
            } else {
                data = null;
            }
        } catch (Exception e) {
            Assert.fail("userSetAsTheToDataObjectWithDataType Step is failed due to exception of " + e.getMessage());
        }
    }
}
