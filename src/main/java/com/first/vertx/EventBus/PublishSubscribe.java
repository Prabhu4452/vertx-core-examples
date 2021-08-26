package com.first.vertx.EventBus;

import com.first.vertx.eventloops.EventLoopExample;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PublishSubscribe {

  private static final Logger log = LoggerFactory.getLogger(EventLoopExample.class);

  public static void main(String[] args) {
    BasicConfigurator.configure();
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new Publish());
    vertx.deployVerticle(new Subscriber1());
    vertx.deployVerticle(new Subscriber2());
  }

  public static class Publish extends AbstractVerticle {

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
      startPromise.complete();
      vertx.setPeriodic(10000, id -> {
        vertx.eventBus().publish(Publish.class.getName(), "Publishing the message for everyone out there as, Hello Prabs, It's message from Prabhu...");
      });
    }
  }

  public static class Subscriber1 extends AbstractVerticle {

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
      startPromise.complete();
      vertx.eventBus().consumer(Publish.class.getName(), message -> {
        log.debug("Received from Publisher: {}, Subscriber Name: {}", message.body(), Subscriber1.class.getName());
      });
    }
  }

  public static class Subscriber2 extends AbstractVerticle {

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
      startPromise.complete();
      vertx.eventBus().consumer(Publish.class.getName(), message -> {
        log.debug("Received from Publisher: {}, Subscriber Name: {}", message.body(), Subscriber2.class.getName());
      });
    }
  }
}
