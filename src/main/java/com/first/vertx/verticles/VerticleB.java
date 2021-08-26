package com.first.vertx.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VerticleB extends AbstractVerticle {

  private static final Logger log = LoggerFactory.getLogger(MainVerticleCopy.class);

  @Override
  public void start(Promise<Void> promiseFuture) throws Exception {
    log.debug("Its a chile verticle of MainVerticle -> {} ", getClass().getName());
    promiseFuture.complete();
  }
}
