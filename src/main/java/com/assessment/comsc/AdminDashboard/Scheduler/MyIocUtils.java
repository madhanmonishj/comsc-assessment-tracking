package com.assessment.comsc.AdminDashboard.Scheduler;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class MyIocUtils implements ApplicationContextAware {
 
    private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext){
        this.applicationContext = applicationContext;
    }
    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }
}