package com.first.vertx.joobscheduler;

import io.reactivex.rxjava3.core.Observable;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.HashMap;
import java.util.Map;

public class HelloJob implements Job {

  public static void main(String[] args) {

  }

  @Override
  public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
      System.out.println("Inside job scheduler");

    JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
    String value = jobDataMap.getString("1");
    String value1 = jobDataMap.getString("Says");
    String value2 = jobDataMap.getString("token");

    Map<String, String> res = new HashMap<>();
    res.put("1", "er");
    res.put("2", "lol");

    jobExecutionContext.setResult(res);

    Observable<String> h = Observable.just("h", "d");
    h.filter( val -> val.equalsIgnoreCase("h")).subscribe();

    System.out.println("Values: " + value + " " + "says"  + " " + value1);
    System.out.println("Values from another jobmap: " + value2 + " " + "\n"   );
    System.out.println("Values from obserabel: " + h.toString() + " " + "\n"   );
  }
}
