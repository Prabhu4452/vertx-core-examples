package com.first.vertx.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VerticleA extends AbstractVerticle {

  private static final Logger log = LoggerFactory.getLogger(MainVerticleCopy.class);

  @Override
  public void start(Promise<Void> promiseFuture) throws Exception {
    log.debug("Its a child verticle of MainVerticle ->  {}", getClass().getName());

    vertx.deployVerticle(new VerticleAA(), whenDeployed -> {
      log.info("Deployed -> {}", VerticleAA.class.getName());
      vertx.undeploy(whenDeployed.result());
    });

    vertx.deployVerticle(new VerticleAB(), whenDeployed -> {
      log.info("Deployed -> {}", VerticleAB.class.getName());
    });
    promiseFuture.complete();
  }
}
