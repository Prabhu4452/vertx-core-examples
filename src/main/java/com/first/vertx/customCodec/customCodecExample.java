package com.first.vertx.customCodec;

import com.first.vertx.eventloops.EventLoopExample;
import io.vertx.core.*;
import io.vertx.core.eventbus.EventBus;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class customCodecExample {

  private static final Logger log = LoggerFactory.getLogger(EventLoopExample.class);

  private static final String ADDRESS = "request.address";

  public static void main(String[] args) {
    BasicConfigurator.configure();
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new PingVerticle(), errorHandler());
    vertx.deployVerticle(new PongVerticle(), errorHandler());
  }

  private static Handler<AsyncResult<String>> errorHandler() {
    return er -> {
      if (er.failed()) {
        log.error("Error caused due to: " + er.cause());
      }
    };
  }

  static class PingVerticle extends AbstractVerticle {
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
      EventBus eventBus = vertx.eventBus();
      eventBus.registerDefaultCodec(Ping.class, new LocalMessageCodec<>(Ping.class));
      Ping ping = new Ping();
      ping.setId(3);
      ping.setName("Prabs");
      eventBus.<Pong>request(ADDRESS, ping, reply -> {                   //Address is a address of the request and it is completely left to developer to define any custom address
        if (reply.failed()) {
          log.error("Error occured" + reply.cause());
        }
        log.debug("Reply from response: {}", reply.result().body().getMessage());
      });
      startPromise.complete();
    }
  }

  static class PongVerticle extends AbstractVerticle {

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
      EventBus eventBus = vertx.eventBus();
      eventBus.registerDefaultCodec(Pong.class, new LocalMessageCodec<>(Pong.class));
      eventBus.<Ping>consumer(ADDRESS, message -> {                //the address to which the response verticle is consuming the message
        log.debug("Received message: {}", message.body().getId());
        log.debug("Received message: {}", message.body().getName());
        Pong pong = new Pong();
        pong.setMessage("Hello, I have received your message");
        message.reply(pong);
      }).exceptionHandler(er -> {
        log.error("Error in PongVerticle: " + er);
      });
      startPromise.complete();
    }
  }
}

