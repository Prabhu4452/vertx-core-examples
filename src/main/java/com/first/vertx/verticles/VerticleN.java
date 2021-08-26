package com.first.vertx.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VerticleN extends AbstractVerticle {

  private static final Logger log = LoggerFactory.getLogger(MainVerticleCopy.class);

  @Override
  public void start(Promise<Void> promiseFuture) throws Exception {
    log.info("Its a child verticle of MainVerticle -> {}, Thread ={}, config= {}", getClass().getName(),
      Thread.currentThread().getName(), config().toString());
    promiseFuture.complete();
  }
}
