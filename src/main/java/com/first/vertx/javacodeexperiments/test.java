package com.first.vertx.javacodeexperiments;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.Arrays;
import java.util.HashSet;

public class test {



  public static void main(String[] args) {

    HashSet<String> h1 = new HashSet<>();
    h1.add("Hi");
    h1.add("Hello");
    h1.add("Hi");


    System.out.println("hasheset: " + h1);


    String path = "https://127.0.0.1/download?token=auth.local/055d565cdabfb052f1664b74b3bbcd5f&id=rbccps.org/e096b3abef24b99383d9bd28e9b8c89cfd50be0b/example.com/test-category/test-resource-2";
    int pos = path.indexOf("id=");
    String finalpath = path.substring(pos+3);
    System.out.println("final path: " + finalpath);


    JsonObject term = new JsonObject();
    JsonObject term1 = new JsonObject();
    JsonObject[] term2 = new JsonObject[2];
    JsonObject firstterm = new JsonObject();
    JsonObject secterm = new JsonObject();
//    term.put("term", firstterm.put("data.metadata.1", "rose"));
//    term1.put("term", secterm.put("data.metadata.2", "male"));
    JsonObject downloadByQuery = new JsonObject()
      .put("query", new JsonObject().put("bool",
        new JsonObject().put("filter",
          new JsonArray())));

    for(int i=0; i<term2.length; i++) {
      System.out.println("term: " + term2[i]);
//      term2[i].put("term", "");
    }
    System.out.println("term1: " + term1);

    System.out.println("terms: " + Arrays.toString(term2));
    JsonArray jsonObject = downloadByQuery.getJsonObject("query").getJsonObject("bool").getJsonArray("filter");
    jsonObject.add(term);

    System.out.println("query:" + jsonObject.toString());
  }
}
