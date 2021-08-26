package com.first.vertx.workerthreads;

import com.first.vertx.eventloops.EventLoopExample;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Worker threads allows us to work around blocking events by providing vertx.executeBlockingEvent() function.
 */

public class WorkerExample extends AbstractVerticle {

  private static final Logger log = LoggerFactory.getLogger(EventLoopExample.class);

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    startPromise.complete();
    vertx.executeBlocking(event -> {             //this is worker thread which executes event's blocking code.
      log.debug("Executing blocking code");
      try {
        Thread.sleep(5000);          //we are sleeping event for 5 sec and then completing event.
        event.complete();
//        event.fail("Force fail");         //sometimes event may fail
      }
      catch (InterruptedException interruptedException) {
        log.error("Failed", interruptedException);
        event.fail(interruptedException);
      }
    }, result -> {                        //this is event loop thread which executes result of event.
        if (result.succeeded()) {
          log.debug("Blocking code done");
        }
        else {
          log.error("Blocking code failed due to: ", result.cause());
        }
      });
  }

  public static void main(String[] args) {
    BasicConfigurator.configure();
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new WorkerExample());
  }
}
