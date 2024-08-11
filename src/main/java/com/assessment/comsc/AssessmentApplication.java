package com.assessment.comsc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.assessment.comsc.AdminDashboard.Scheduler.QuartzScheduler;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication
@MapperScan(basePackages = "com.assessment.comsc.mapper")
public class AssessmentApplication {
	
	@Autowired
	private QuartzScheduler quartzScheduler;

	public static void main(String[] args) {
		SpringApplication.run(AssessmentApplication.class, args);
		System.out.println("******RUN SUCCESS*******");
	}

	// Intializing Quartz Scheduler
	@Bean
    public CommandLineRunner schedulingRunner() {
        return args -> {
            System.out.println("Schedule is initiated");
            quartzScheduler.scheduleQuartzJob();
        };
    }
}
