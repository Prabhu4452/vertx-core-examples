package com.first.vertx.MappingJavaObjectsToJsonObjects;

import io.vertx.core.json.JsonObject;

public class MappingJavaObjectToJsonObjectExample {

  public static void main(String[] args) {

    Person personDetails = new Person();
    personDetails.setId(3);
    personDetails.setName("Prabs");
    personDetails.setProfession("Exploration");


    JsonObject jsonObjectMappedFromJavaObject = JsonObject.mapFrom(personDetails);
    System.out.println("Java Object converted to Json object: " + jsonObjectMappedFromJavaObject);

    Person personJavaObjectConvertedBackFromJsonObject = jsonObjectMappedFromJavaObject.mapTo(Person.class);
    System.out.println("Json object converted back to Java Object: "
      + " Name: " +personJavaObjectConvertedBackFromJsonObject.getName()
      + " Id: " +personJavaObjectConvertedBackFromJsonObject.getId()
      + " Profession: " +personJavaObjectConvertedBackFromJsonObject.getProfession());
  }
}
