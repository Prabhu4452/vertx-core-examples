package com.first.vertx.JsonObjects;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.HashMap;
import java.util.Map;

public class JsonObjectExample {

  public static void main(String[] args) {

    JsonObject jsonObject = new JsonObject();
    jsonObject.put("id", 369).put("id1", 696);
    jsonObject.put("Code", "Tesla");
    jsonObject.put("Dimension", "Beyond human perception");
    System.out.println("Jsonobject response: "  + jsonObject);

    JsonObject jsonObject2 = new JsonObject();
    jsonObject2.put("id", 369).put("id1", 696);
    jsonObject2.put("Code", "Tesla");
    jsonObject2.put("Dimension", new JsonObject().put("Message", "Beyond human perception").put("Message2", "Beyond human perception man"));
    System.out.println("Jsonobject2 response: "  + jsonObject2);

    jsonObject.mergeIn(jsonObject2);   //Now jsonObject will become jsonObject2
    System.out.println("JsonObject is merged into JsonObject2" + jsonObject); //jsonObject has jsonObject2 entries

    String sample;
    sample = "{\"consumer\":\"consumer@iisc.ac.in\",\"expiry\":\"2021-05-05T01:07:12.796Z\",\"request\":[{\"id\":\"rbccps.org/e096b3abef24b99383d9bd28e9b8c89cfd50be0b/example.com/test-category/c8Khdfp2keWc5LxBqyvAInlHZX3wSxRi\",\"apis\":[\"/*\"],\"body\":null,\"scopes\":[\"read\",\"write\"],\"methods\":[\"*\"],\"environments\":[\"*\"]},{\"id\":\"rbccps.org/e096b3abef24b99383d9bd28e9b8c89cfd50be0b/example.com/test-category/Fjc6TXI3rB3xu1Rk6hreNavOrxNVZkQJ\",\"apis\":[\"/*\"],\"body\":null,\"scopes\":[\"read\",\"write\"],\"methods\":[\"*\"],\"environments\":[\"*\"]},{\"id\":\"rbccps.org/e096b3abef24b99383d9bd28e9b8c89cfd50be0b/example.com/test-category/1Z8DuUpGSY8180gYaxTKRHDygfTNVglR\",\"apis\":[\"/*\"],\"body\":null,\"scopes\":[\"read\",\"write\"],\"methods\":[\"*\"],\"environments\":[\"*\"]},{\"id\":\"rbccps.org/e096b3abef24b99383d9bd28e9b8c89cfd50be0b/example.com/test-category/mAEuun1nuuAcH7hv3k33Ua6f7aRwJt9x\",\"apis\":[\"/*\"],\"body\":null,\"scopes\":[\"read\",\"write\"],\"methods\":[\"*\"],\"environments\":[\"*\"]},{\"id\":\"rbccps.org/e096b3abef24b99383d9bd28e9b8c89cfd50be0b/example.com/test-category/fbbgPdQhEbqOfAyVE1HMzTea7MYC4snR\",\"apis\":[\"/*\"],\"body\":null,\"scopes\":[\"read\",\"write\"],\"methods\":[\"*\"],\"environments\":[\"*\"]},{\"id\":\"rbccps.org/e096b3abef24b99383d9bd28e9b8c89cfd50be0b/example.com/test-category/xrtzXXrA3zcjZpsNtSw0c9W9rFEM2F47.public\",\"apis\":[\"/*\"],\"body\":null,\"scopes\":[\"read\",\"write\"],\"methods\":[\"*\"],\"environments\":[\"*\"]},{\"id\":\"rbccps.org/e096b3abef24b99383d9bd28e9b8c89cfd50be0b/example.com/test-category/c8Khdfp2keWc5LxBqyvAInlHZX3wSxRi\",\"apis\":[\"/*\"],\"body\":null,\"scopes\":[\"read\",\"write\"],\"methods\":[\"*\"],\"environments\":[\"*\"]},{\"id\":\"rbccps.org/e096b3abef24b99383d9bd28e9b8c89cfd50be0b/example.com/test-category/1Z8DuUpGSY8180gYaxTKRHDygfTNVglR\",\"apis\":[\"/*\"],\"body\":null,\"scopes\":[\"write\"],\"methods\":[\"*\"],\"environments\":[\"*\"]},{\"id\":\"rbccps.org/e096b3abef24b99383d9bd28e9b8c89cfd50be0b/example.com/test-category/Fjc6TXI3rB3xu1Rk6hreNavOrxNVZkQJ\",\"apis\":[\"/*\"],\"body\":null,\"scopes\":[\"read\",\"write\"],\"methods\":[\"*\"],\"environments\":[\"*\"]},{\"id\":\"rbccps.org/e096b3abef24b99383d9bd28e9b8c89cfd50be0b/example.com/test-category/1Z8DuUpGSY8180gYaxTKRHDygfTNVglR\",\"apis\":[\"/*\"],\"body\":null,\"scopes\":[\"read\",\"write\"],\"methods\":[\"*\"],\"environments\":[\"*\"]},{\"id\":\"rbccps.org/e096b3abef24b99383d9bd28e9b8c89cfd50be0b/example.com/test-category/mAEuun1nuuAcH7hv3k33Ua6f7aRwJt9x\",\"apis\":[\"/*\"],\"body\":null,\"scopes\":[\"read\",\"write\"],\"methods\":[\"*\"],\"environments\":[\"*\"]}],\"consumer-certificate-class\":2}\n";
    JsonObject jsonObjectGotFromString = new JsonObject(sample);
    System.out.println("jsonObjectGotFromString: "+ jsonObjectGotFromString);

    JsonArray jsonObjectGotFromStringAndCOnvertedToArray = new JsonObject(sample).getJsonArray("expiry");
    System.out.println("jsonObjectGotFromStringAndCOnvertedToArray: "+ jsonObjectGotFromStringAndCOnvertedToArray);


    toJsonArrayFromJsonObject(jsonObjectGotFromString);


    JsonObjectExample jsonObjectExample = new JsonObjectExample();
    JsonArray jsonArrayFromJsonObject = jsonObjectExample.jsonArrayFromJsonObject(jsonObject);
    System.out.println( jsonArrayFromJsonObject);

    System.out.println("Json object: " + jsonObject.encode());

    final Map<String, Object> map = new HashMap<>();
    map.put("id", 369);
    map.put("Code", "Tesla");
    map.put("Dimension", "Beyond human perception");
    JsonObject jsonObject1 = new JsonObject(map);

    System.out.println("Json object from map: " + jsonObject1.encode());
  }

  private static void toJsonArrayFromJsonObject(JsonObject jsonObject2) {
    JsonArray jsonArray = jsonObject2.getJsonArray("Dimension");
    System.out.println("Jsonobject's key converted into JsonArray" + jsonArray);
  }

  public JsonArray jsonArrayFromJsonObject(JsonObject jsonObject) {
    JsonArray convertedjsonArray = new JsonObject().getJsonArray("jsonObject");
    return convertedjsonArray;
  }
}
