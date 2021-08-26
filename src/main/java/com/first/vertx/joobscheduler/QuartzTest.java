package com.first.vertx.joobscheduler;

import org.quartz.*;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class QuartzTest {

  public static void main(String[] args) {

    try {
      // Grab the Scheduler instance from the Factory
      SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();

      Scheduler sched = schedFact.getScheduler();

      sched.start();

      JobDataMap jobDataMap = new JobDataMap();
      jobDataMap.put("token", "avs");
      jobDataMap.put("token1", "avs");
      jobDataMap.put("token2", "avs");
      jobDataMap.put("id", "avs");

      // define the job and tie it to our HelloJob class
      JobDetail job = JobBuilder.newJob(HelloJob.class)
        .withIdentity("job1", "group1")
        .usingJobData("1", "Prabhu")
        .usingJobData("Says", "hello")
        .usingJobData(jobDataMap)
        .build();

      // Trigger the job to run now, and then every 40 seconds
      Trigger trigger = newTrigger()
        .withIdentity("myTrigger", "group1")
        .startNow()
        .withSchedule(simpleSchedule()
          .withIntervalInSeconds(6)
          .repeatForever())
        .build();

      // Tell quartz to schedule the job using our trigger
      sched.scheduleJob(job, trigger);

//      sched.shutdown();

    } catch (SchedulerException se) {
      se.printStackTrace();
    }
  }
}
