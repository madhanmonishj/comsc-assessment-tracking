package com.assessment.comsc.AdminDashboard.Scheduler;

import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuartzScheduler {

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private QuartzTrigger quartzTrigger;
    
    @Autowired
    private QuartzJob quartzJob;

    // This method maps and schedules the email quartz job with trigger and job
    public void scheduleQuartzJob() {
        try {
            JobDetail jobDetail = quartzJob.quartzJobDetail();
            Trigger trigger = quartzTrigger.quartzJobTrigger(jobDetail);

            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
