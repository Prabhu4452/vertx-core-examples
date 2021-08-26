package com.first.vertx.javacodeexperiments;

import io.vertx.core.MultiMap;

import java.util.List;
import java.util.Map;

public class multimaps {

  public static void main(String[] args) {

    MultiMap mmap = MultiMap.caseInsensitiveMultiMap();
    mmap.add("hey", "prabs");
    mmap.add("hey1", "prabs1");
    mmap.add("hey2", "prabs2");
    System.out.println("map:" + mmap);

    List<Map.Entry<String, String>> entries = mmap.entries();
    for(int i=0; i<entries.size(); i++) {
      System.out.println("map:" + i + entries.get(i));
    }

  }
}
