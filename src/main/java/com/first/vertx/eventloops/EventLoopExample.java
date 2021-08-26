package com.first.vertx.eventloops;

import io.vertx.core.*;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class EventLoopExample extends AbstractVerticle {

  private static final Logger log = LoggerFactory.getLogger(EventLoopExample.class);

  @Override
  public void start(final Promise<Void> startPromise ) throws Exception {
    log.debug("start {}", getClass().getName());
    startPromise.complete();
    Thread.sleep(5000); //blocking event
  }

  public static void main(String[] args) {
    BasicConfigurator.configure();
    Vertx vertx = Vertx.vertx(
      new VertxOptions()
      .setMaxEventLoopExecuteTime(500) //this describes the max amount of time that one event/thread should execute. Meaning, a event cannot take more than 500 ms to execute its task.
      .setMaxEventLoopExecuteTimeUnit(TimeUnit.MILLISECONDS)
      .setBlockedThreadCheckInterval(1) //for every one sec it checks if thread/event is blocked or not.
      .setBlockedThreadCheckIntervalUnit(TimeUnit.SECONDS)
      .setEventLoopPoolSize(2)
    );
    vertx.deployVerticle(EventLoopExample.class.getName(),
      new DeploymentOptions()
          .setInstances(4));
  }
}
