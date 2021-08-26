package com.first.vertx.EventBus;

import com.first.vertx.eventloops.EventLoopExample;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestResponse {

  private static final Logger log = LoggerFactory.getLogger(EventLoopExample.class);

  private static final String ADDRESS = "request.address";

  public static void main(String[] args) {
    BasicConfigurator.configure();
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new RequestVerticle());
    vertx.deployVerticle(new ResponseVerticle());
  }

  static class RequestVerticle extends AbstractVerticle {
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
      EventBus eventBus = vertx.eventBus();
      String message = "Hello Prabs, Its a message from Prabhu";
      JsonObject jsonObject = new JsonObject();
      jsonObject.put("Name", "Prabs");
      jsonObject.put("Code", "Green");
      log.debug("Sending message: {} ", jsonObject);
      eventBus.<JsonObject>request(ADDRESS, jsonObject, reply -> {                   //Address is a address of the request and it is completely left to developer to define any custom address
        log.debug("Reply from response: {}", reply.result().body());
      });
    }
  }

  static class ResponseVerticle extends AbstractVerticle {

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
      EventBus eventBus = vertx.eventBus();
      eventBus.<JsonObject>consumer(ADDRESS, message -> {                //the address to which the response verticle is consuming the message
        log.debug("Received message: {}", message.body());
        message.reply(new JsonArray().add("Sending message in a JSon array").add("Hello, thanks I have received your message, Prabhu"));
      });
    }
  }
}
