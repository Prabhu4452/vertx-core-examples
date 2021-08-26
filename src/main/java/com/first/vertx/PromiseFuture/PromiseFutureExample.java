package com.first.vertx.PromiseFuture;

import com.first.vertx.eventloops.EventLoopExample;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PromiseFutureExample {

  /*
    Promise- Used to write an eventual event.
    Future- Used to read the value from Promise when it is available
   */

  private static final Logger log = LoggerFactory.getLogger(EventLoopExample.class);

  public static void main(String[] args) {
    BasicConfigurator.configure();
    Vertx vertx = Vertx.vertx();

    SimplePromiseSuccess(vertx);
    SimplePromiseFailure(vertx);
    SimpleFutureSuccess(vertx);
    SimpleFutureFailure(vertx);

  }

  private static void SimplePromiseSuccess(Vertx vertx) {
    log.debug("Start");
    Promise<String> promise = Promise.promise();      //here promise type is String and it can also have Json, JsonArray, void as data types
    vertx.setTimer(1000, id -> {         //after one sec, promise completes with "Success" message
      promise.complete("Success!!");
      log.debug("Success");
    });
    log.debug("End");
  }

  private static void SimplePromiseFailure(Vertx vertx) {
    log.debug("Start");
    Promise<String> promise = Promise.promise();
    vertx.setTimer(1000, id -> {         //after one sec, promise completes with "Success" message
      promise.fail("Failed!!");
      log.debug("Failed");
    });
    log.debug("End");
  }

  private static void SimpleFutureSuccess(Vertx vertx) {
    log.debug("Start");
    Promise<String> promise = Promise.promise();
    vertx.setTimer(1000, id -> {         //after one sec, promise completes with "Success" message
      promise.complete("Success!!");
      log.debug("Timer done");
    });
    log.debug("End");

    Future<String> future = promise.future();
    future.onSuccess(result -> {             //When promise.complete() is done, then immediately future.onSuccess() is called.
      log.info("result: {}", result);
    })
    .onFailure(error -> {
      log.error("Error: {}", error.getMessage());
    });

  }

  private static void SimpleFutureFailure(Vertx vertx) {
    log.debug("Start");
    Promise<String> promise = Promise.promise();
    vertx.setTimer(1000, id -> {         //after one sec, promise fails with Runtime exception
      promise.fail(new RuntimeException("Failed during runtime"));
      log.debug("Timer done");
    });
    log.debug("End");

    Future<String> future = promise.future();
    future.onSuccess(result -> {
      log.info("result: {}", result);
    })
      .onFailure(error -> {              //When promise.fail() is done, then immediately future.onFailure() is called.
        log.error("Error: {}", error.getMessage());
      });

  }
}
