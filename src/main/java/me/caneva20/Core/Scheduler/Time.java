package me.caneva20.Core.Scheduler;

import me.caneva20.Core.Util.Util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Time {
    private int hour;
    private int minute;

    private boolean executed;

    public Time(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public Time(String timeStr) {
        Time time = Util.convertToCNVTime(timeStr);

        this.hour = time != null ? time.getHour() : 0;
        this.minute = time != null ? time.getMinute() : 0;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public boolean isExecuted() {
        return executed;
    }

    public void execute () {
        executed = true;
    }

    public void reset () {
        executed = false;
    }

    public boolean canExecute (int tolerance) {
        if (executed) {
            return false;
        }

        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);

        int hour = calendar.get(Calendar.HOUR);
        int min = calendar.get(Calendar.MINUTE);

        int executeTime = this.hour * 60 + this.minute;
        int now = hour * 60 + min;

        return now >= executeTime - tolerance && now <= executeTime + tolerance;
    }
}