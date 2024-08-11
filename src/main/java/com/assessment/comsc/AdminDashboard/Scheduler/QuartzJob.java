package com.assessment.comsc.AdminDashboard.Scheduler;

import com.assessment.comsc.AdminDashboard.Email;
import com.assessment.comsc.AdminDashboard.EmailService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.assessment.comsc.AdminDashboard.AdminController;
import org.springframework.http.ResponseEntity;

@Configuration
public class QuartzJob implements Job {

    private EmailService emailService;

    private ApplicationContext applicationContext;


    // This method executes the email sending logic.
    @Override
    public void execute(JobExecutionContext context) {
        // if emailservice is null dont execute below
        EmailService emailService1 = (EmailService)MyIocUtils.getApplicationContext().getBean("emailService");
        Email email = new Email(
           "team13project987@gmail.com",
           "Pending Assessment(Deadline)",
           "This is an Automated Email to remind you about the pending assessments"
        );

        try {
            ResponseEntity<String> response = emailService1.sendEmail(email);
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // This method defines attributes of the quartz job.
    @Bean
    public JobDetail quartzJobDetail() {
        return JobBuilder.newJob(QuartzJob.class)
                .withIdentity("QuartzJob")
                .storeDurably()
                .build();
    }
}
