package com.amused.services;

import com.amused.pojos.RequestObj;
import com.amused.utils.PropertyReader;
import com.amused.utils.RestUtils;
import io.restassured.response.Response;

public class BaseService {

    RequestObj requestObj = new RequestObj();

    public Response getListOfAllObjects() {
        requestObj.setUrl(PropertyReader.getInstance().getProperty("BASE_URI") + "objects");
        requestObj.setServiceName("getListOfAllObjects");
        requestObj.setServiceType("GET");
        return new RestUtils().apiRequestSend(requestObj);
    }

    public Response getObjectsForGivenIds(String pathParam) {
        requestObj.setUrl(PropertyReader.getInstance().getProperty("BASE_URI") + "objects?" + pathParam);
        requestObj.setServiceName("getObjectsForGivenIds");
        requestObj.setServiceType("GET");
        return new RestUtils().apiRequestSend(requestObj);
    }

    public Response getObjectForGivenId(String pathParam) {
        requestObj.setUrl(PropertyReader.getInstance().getProperty("BASE_URI") + "objects/" + pathParam);
        requestObj.setServiceName("getObjectForGivenId");
        requestObj.setServiceType("GET");
        return new RestUtils().apiRequestSend(requestObj);
    }

    public Response addObject(String requestJson) {
        requestObj.setUrl(PropertyReader.getInstance().getProperty("BASE_URI") + "objects");
        requestObj.setServiceName("addObject");
        requestObj.setJsonBody(requestJson);
        requestObj.setServiceType("POST");
        return new RestUtils().apiRequestSend(requestObj);
    }

    public Response updateObject(String requestJson, String id) {
        requestObj.setUrl(PropertyReader.getInstance().getProperty("BASE_URI") + "objects/" + id);
        requestObj.setServiceName("updateObject");
        requestObj.setJsonBody(requestJson);
        requestObj.setServiceType("PUT");
        return new RestUtils().apiRequestSend(requestObj);
    }

    public Response updatePartiallyObject(String requestJson, String id) {
        requestObj.setUrl(PropertyReader.getInstance().getProperty("BASE_URI") + "objects/" + id);
        requestObj.setServiceName("updatePartiallyObject");
        requestObj.setJsonBody(requestJson);
        requestObj.setServiceType("PATCH");
        return new RestUtils().apiRequestSend(requestObj);
    }

    public Response deleteObject(String id) {
        requestObj.setUrl(PropertyReader.getInstance().getProperty("BASE_URI") + "objects/" + id);
        requestObj.setServiceName("deleteObject");
        requestObj.setServiceType("DELETE");
        return new RestUtils().apiRequestSend(requestObj);
    }


}
