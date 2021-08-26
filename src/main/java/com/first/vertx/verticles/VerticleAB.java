package com.first.vertx.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VerticleAB extends AbstractVerticle {

  private static final Logger log = LoggerFactory.getLogger(MainVerticleCopy.class);

  @Override
  public void start(Promise<Void> promiseFuture) throws Exception {
    log.debug("Its a child verticle of VerticleB -> {}", getClass().getName());
    promiseFuture.complete();
  }

  @Override
  public void stop(Promise<Void> stopPromise) throws Exception {
    log.info("Stopped -> {}", getClass().getName());
    stopPromise.complete();
  }
}
