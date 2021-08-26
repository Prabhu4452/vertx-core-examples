package com.first.vertx.javacodeexperiments;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FIleCopyTest {

  public static void main(String[] args) throws IOException {
    Path origin = Paths.get("/Users/prabhupatrot/Documents" +
      "/vertx-core/src/main/java/com/first/vertx/javacodeexperiments/file.txt");
    Path dest = Paths.get("/Users/prabhupatrot/Documents" +
      "/vertx-core/src/main/java/com/first/vertx/javacodeexperiments/copiedFiles");

    Files.copy(origin, dest, StandardCopyOption.REPLACE_EXISTING);
  }
}
