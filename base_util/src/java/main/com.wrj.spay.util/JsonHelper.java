package com.wrj.spay.util;

import com.google.gson.*;
import com.sun.jersey.api.client.ClientResponse;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * JsonHelper Utils
 */
public class JsonHelper {

    public static JsonObject getStringAsJsonObject(String arg) {return (JsonObject) new JsonParser().parse(arg);}

    public static String getJsonAttributeAsString(JsonObject jsonObject, String attributeName) {
        JsonElement jsonElement = jsonObject.get(attributeName);
        return jsonElement == null ? null : jsonElement.getAsString();
    }

    public static Long getJsonAttributeAsLong(JsonObject jsonObject, String attributeName) {
        JsonElement jsonElement = jsonObject.get(attributeName);
        return jsonElement == null ? null : jsonElement.getAsLong();
    }

    public static BigDecimal getJsonAttributeAsBigDecimal(JsonObject jsonObject, String attributeName) {
        JsonElement jsonElement = jsonObject.get(attributeName);
        return jsonElement == null ? null : jsonElement.getAsBigDecimal();
    }

    public Boolean getJsonAttributeAsBoolean(JsonObject jsonObject, String attributeName) {
        JsonElement jsonElement = jsonObject.get(attributeName);
        return jsonElement == null ? null : jsonElement.getAsBoolean();
    }

    public Boolean getJsonAttributeAsBooleanDefaultFalse(JsonObject jsonObject, String attributeName) {
        JsonElement jsonElement = jsonObject.get(attributeName);
        return jsonElement == null ? Boolean.FALSE : jsonElement.getAsBoolean();
    }

    public Date getDateFromJson(JsonObject jsonObject, String attName) {
        JsonElement dateJO = jsonObject.get(attName);
        String theDate = dateJO == null ? null : dateJO.getAsString();
        if (theDate != null && theDate.length() > 0 && !theDate.equalsIgnoreCase("null")) {
            return DateUtils.parseDateTime(theDate);
        }
        return null;
    }


    public JsonObject getResponseAsJsonObject(ClientResponse clientResponse) {
        String entity = clientResponse.getEntity(String.class);
        return (JsonObject) new JsonParser().parse(entity);
    }

    public String getSingleResultAsString(ClientResponse clientResponse) {
        JsonObject resultJsonObj = getResponseAsJsonObject(clientResponse);
        JsonElement element = resultJsonObj.get("result");
        return element.getAsString();
    }

    public <T> List<T> getSingleResultAsList(ClientResponse clientResponse, Class<T> clazz) {
        JsonObject resultJsonObj = getResponseAsJsonObject(clientResponse);
        String resultStr = resultJsonObj.get("result").toString();
        return parseAsList(resultStr, clazz);
    }

    public <T> List<T> parseAsList(ClientResponse clientResponse, Class<T> clazz) {
        String entity = clientResponse.getEntity(String.class);
        return parseAsList(entity, clazz);
    }

    public <T> T parse(ClientResponse clientResponse, Class<T> clazz) {
        String entity = clientResponse.getEntity(String.class);
        return new Gson().fromJson(entity, clazz);
    }

    public <T> List<T> parseAsList(String entity, Class<T> clazz) {
        List<T> results = new ArrayList<T>();
        JsonArray elements = new JsonParser().parse(entity).getAsJsonArray();
        for (JsonElement element : elements) {
            results.add(new Gson().fromJson(element, clazz));
        }
        return results;
    }

    public static String buildJsonString(Object... objects) {
        JsonObject jsonObject = new JsonObject();
        if (objects.length % 2 != 0) {
            throw new RuntimeException("the parameter should be paired.");
        }
        for (int i = 0; i < objects.length; i += 2) {
            String key = objects[i].toString();
            Object value = objects[i + 1];
            if (value == null) {
                jsonObject.addProperty(key, "");
            } else if (value instanceof Number) {
                jsonObject.addProperty(key, (Number) value);
            } else if (value instanceof Boolean) {
                jsonObject.addProperty(key, (Boolean) value);
            } else if (value instanceof Date) {
                jsonObject.addProperty(key, DateUtils.formatDateTime((Date) value));
            } else {
                jsonObject.addProperty(key, value.toString());
            }
        }
        return jsonObject.toString();
    }

    public static void main(String[] args) {
        String entity = "{\"id\":12,\"num\":\"11\"}";
        JsonObject jsonObject = (JsonObject) new JsonParser().parse(entity);

        System.out.println(new JsonHelper().getJsonAttributeAsString(jsonObject, "idx"));
        System.out.println(new JsonHelper().getJsonAttributeAsString(jsonObject, "id"));
        System.out.println(new JsonHelper().getJsonAttributeAsLong(jsonObject, "num"));

        System.out.println(jsonObject.get("id").getAsString());
        System.out.println(jsonObject.get("id").getAsInt());
        System.out.println(jsonObject.get("num").getAsString());
        System.out.println(jsonObject.get("num").getAsInt());

        jsonObject = new JsonObject();
        jsonObject.addProperty("id", 123L);
        jsonObject.addProperty("num", "111");
        System.out.println(jsonObject.toString());

        System.out.println(buildJsonString("id", 123L, "num", true, "test", null, "name", "jiaoluxi", "date", new Date()));
    }

}
