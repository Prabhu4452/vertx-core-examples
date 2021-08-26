package com.first.vertx.JsonArray;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

public class JsonArrayExample {

  public static void main(String[] args) {

    JsonArray jsonArray = new JsonArray();
    jsonArray.add(69);
    jsonArray.add("Code");
    jsonArray.add("Vital");

    System.out.println("Json array: " + jsonArray.encode());

    /*
       Adding Json objects to Json array example
     */

    JsonArray jsonArray1 = new JsonArray();
    jsonArray1.add(new JsonObject().put("id", 99).put("Name", "Prabs"));
    jsonArray1.add(new JsonObject().put("code", "Green"));
    jsonArray1.add(new JsonObject().put("Dimension", "Matrix"));
    jsonArray1.add("Positivity");

    System.out.println("Json array having Json Object arg: " + jsonArray1);

  }
}
