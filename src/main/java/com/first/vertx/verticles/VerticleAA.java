package com.first.vertx.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VerticleAA extends AbstractVerticle {

  private static final Logger log = LoggerFactory.getLogger(MainVerticleCopy.class);

  @Override
  public void start(Promise<Void> promiseFuture) throws Exception {
    log.debug("Its a child verticle of VerticleA -> {} ", getClass().getName());
    vertx.createHttpServer().requestHandler(req -> {
      req.response()
        .putHeader("content-type", "text/plain")
        .end("Hello Prabs from VerticleAA!");
    }).listen(8081, http -> {
      if (http.succeeded()) {
        log.info("HTTP server started on port 8081 for verticle -> {}", getClass().getName());
      } else {
        promiseFuture.fail(http.cause());
        log.error("HTTP server failed to start");
      }
    });
  }

  @Override
  public void stop(Promise<Void> stopPromise) throws Exception {
    log.info("Stopped -> {}", getClass().getName());
    stopPromise.complete();
  }
}
