package com.assessment.comsc.AdminDashboard.Scheduler;
import org.quartz.*;

import java.util.Date;

public class DateTime {
    private Integer hour;
    private Integer minute;
    private Integer second;
    private Integer day;
    private Integer month;
    private Integer year;

    public DateTime(int hour, int minute, int second, int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.year = year;
    }

    public Date getDate (){
        return DateBuilder.dateOf(
            this.hour,
            this.minute,
            this.second,
            this.day,
            this.month,
            this.year
        );
    }
}
