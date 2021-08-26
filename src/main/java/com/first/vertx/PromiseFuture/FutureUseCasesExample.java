package com.first.vertx.PromiseFuture;

import com.first.vertx.eventloops.EventLoopExample;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
  Once promise completes and sends a message, future can make use of this
  message and modify using operators like map.
 */
public class FutureUseCasesExample {

  private static final Logger log = LoggerFactory.getLogger(EventLoopExample.class);

  public static void main(String[] args) {
    BasicConfigurator.configure();

//    MapsOperatedOnFuture();
//    chainingMultipleFutures(Vertx.vertx());
    compositeFutures(Vertx.vertx());   //This provides a way to handle on multiple futures
  }

  private static void MapsOperatedOnFuture() {
    log.debug("start of promise");
    Promise<String> promise = Promise.promise();
    promise.complete("Success");
    log.debug("end of promise");

    Future<String> future = promise.future();
    future
      .map(resultString -> {
        log.debug("Message obtained from promise: {}", resultString);
        return new JsonObject().put("message", resultString);
      })
      .map(resultFromFirstMap -> {
        log.debug("Result obtained from First Map operator: {} of type: {}",
          resultFromFirstMap, resultFromFirstMap.getClass().getSimpleName());
        return new JsonArray().add(resultFromFirstMap);
      })
      .onSuccess(result -> {
        log.debug("Result obtained from Map operator: {} of type: {}", result, result.getClass().getSimpleName());
      })
      .onFailure(Throwable::getMessage);
  }

  private static void chainingMultipleFutures(Vertx vertx) {

    vertx.createHttpServer()
      .requestHandler(httpServerRequest -> log.debug("request: {}", httpServerRequest))
      .listen(8000)    //listen returns a Future
      .compose(firstFuture -> {    //compose returns a Future of type HttpServer
        log.debug("First future: {}", firstFuture);
        return Future.succeededFuture(firstFuture);
      })
      .compose(secondFuture -> {   //compose returns a Future of type HttpServer
        log.debug("Second future: {}", secondFuture);
        return Future.succeededFuture(secondFuture);
      })
      .onSuccess(server -> {
        log.debug("Server started on port: {}", server.actualPort());
      })
      .onFailure(error -> {
        log.error("Error caused: {}", error.getMessage());
      });
  }

  private static void compositeFutures(Vertx vertx) {

    Promise<String> firstPromise = Promise.promise();
    Promise<String> secondPromise = Promise.promise();
    Promise<String> thirdPromise = Promise.promise();

    Future<String> firstFuture = firstPromise.future();
    Future<String> secondFuture = secondPromise.future();
    Future<String> thirdFuture = thirdPromise.future();

    CompositeFuture.all(firstFuture, secondFuture, thirdFuture)    // CompositeFuture.all will be success only when all three futures will succeed otherwise it will fail
      .onSuccess(result -> {
        log.info("All futures completed: {}", result);
      })
      .onFailure(Throwable::printStackTrace);

    vertx.setTimer(10000, id -> {
      firstPromise.complete("First promise Completed");
      secondPromise.complete("Second promise Completed");
      thirdPromise.complete("Third promise Completed");
//      thirdPromise.fail("Third promise failed");
    });
  }

}
