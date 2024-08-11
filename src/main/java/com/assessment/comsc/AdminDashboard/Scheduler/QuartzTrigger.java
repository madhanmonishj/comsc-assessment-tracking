package com.assessment.comsc.AdminDashboard.Scheduler;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

@Configuration
public class QuartzTrigger {
    
    // This method is the trigger in which we can set the time to trigger the job
    @Bean
    public Trigger quartzJobTrigger(@Qualifier("quartzJobDetail") JobDetail jobDetail) {
        Trigger trigger = TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity("QuartzTrigger")
                .startAt(new DateTime(14, 10    , 0, 14, 12, 2023).getDate())
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionFireNow())
                .build();

        return trigger;
    }
}

