package com.amused.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

import java.io.IOException;

public class JsonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Returns String representation of the JSON from the given Object
     * @param object
     * @return String representation of the JSON from the given Object
     * @throws Exception
     */
    public static String serialize(Object object) throws Exception {
        return objectMapper.writeValueAsString(object);
    }

    /**
     *
     * @param json
     * @param valueType
     * @return  Returns the Object representation of the JSON
     * @param <T>
     * @throws Exception
     */
    public static <T> T deserialize(String json, Class<T> valueType) throws Exception {
        return objectMapper.readValue(json, valueType);
    }

    /**
     *
     * @param jsonObject
     * @return Returns JsonNode Object representation of the JSON
     * @throws JsonProcessingException
     */
    public static JsonNode deserialize(String jsonObject) throws JsonProcessingException {
        return new ObjectMapper().readTree(jsonObject);
    }


}
