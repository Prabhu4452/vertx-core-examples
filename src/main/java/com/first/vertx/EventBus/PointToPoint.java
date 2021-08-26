package com.first.vertx.EventBus;

import com.first.vertx.eventloops.EventLoopExample;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PointToPoint {

  private static final Logger log = LoggerFactory.getLogger(EventLoopExample.class);

  public static void main(String[] args) {
    BasicConfigurator.configure();
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new Sender());
    vertx.deployVerticle(new Receiver());
  }

  static class Sender extends AbstractVerticle {

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
      startPromise.complete();
      vertx.setPeriodic(1000, id -> {        //Periodic prop sends message every one sec.
        String message = "Hello Prabs, It's message from Prabhu...";
        log.debug("Sending message: {}", message);
        vertx.eventBus().send(Sender.class.getName(), message);    //First param is address of sender and second param is message to be sent
      });
    }
  }

  static class Receiver extends AbstractVerticle {

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
      startPromise.complete();
      vertx.eventBus().<String>consumer(Sender.class.getName(), message -> {
        log.debug("Received from sender: {}", message.body());
        message.reply("Thank you Prabhu, I have received your message");
      });
    }
  }
}
