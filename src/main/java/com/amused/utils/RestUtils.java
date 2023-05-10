package com.amused.utils;

import com.amused.pojos.RequestObj;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;

public class RestUtils {

    private static Logger logger = LoggerFactory.getLogger(RestUtils.class);

    /**
     * Can use this method to send REST requests and can extend
     * the switch cases as per the needs
     *
     * @param requestObj has all the request parameters
     * @return Response object which returns as the response
     */
    public Response apiRequestSend(RequestObj requestObj) {
        Response response = null;
        switch (requestObj.getServiceType()) {
            case "GET":
                response = given().log().all()
                        .get(requestObj.getUrl())
                        .then().log().all().using().extract().response();
                break;
            case "POST":
                response = given().contentType(ContentType.JSON).body(requestObj.getJsonBody()).log().all()
                        .post(requestObj.getUrl())
                        .then().log().all().using().extract().response();
                break;
            case "PUT":
                response = given().contentType(ContentType.JSON).body(requestObj.getJsonBody()).log().all()
                        .put(requestObj.getUrl())
                        .then().log().all().using().extract().response();
                break;
            case "PATCH":
                response = given().contentType(ContentType.JSON).body(requestObj.getJsonBody()).log().all()
                        .patch(requestObj.getUrl())
                        .then().log().all().using().extract().response();
                break;
            case "DELETE":
                response = given().log().all()
                        .delete(requestObj.getUrl())
                        .then().log().all().using().extract().response();
                break;
        }

        logger.info("API service is :" + requestObj.getServiceName() + " and response code is :" + response.getStatusCode());
        return response;
    }
}
